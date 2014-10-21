package com.aoks.security.control;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.aoks.portalmanager.model.security.Application;
import com.aoks.portalmanager.model.security.ApplicationPermission;
import com.aoks.portalmanager.model.security.ApplicationRole;
import com.aoks.security.control.bean.ApplicationRootBean;
import com.aoks.security.control.bean.PermissionBean;
import com.aoks.security.control.bean.RoleBean;
import com.aoks.utils.webmvc.PersistenceWrapper;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Startup
@Singleton
public class XmlLoaderService implements LoaderService{

	@Inject
	private PersistenceWrapper wrapper;
	
	private JAXBContext context;
	
	public XmlLoaderService() {
		
		try {
			context = JAXBContext.newInstance(ApplicationRootBean.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	public void read(File file){

		try {
			Unmarshaller un = context.createUnmarshaller();
			ApplicationRootBean root = (ApplicationRootBean) un.unmarshal(file);
			createModelFromBean(root);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	public void read(String content){
		
		try {
			Unmarshaller un = context.createUnmarshaller();
			ApplicationRootBean root = (ApplicationRootBean) un.unmarshal(new StringReader(content));
			createModelFromBean(root);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	private void createModelFromBean(ApplicationRootBean root){
		
		Application app = new Application();
		app.setName(root.getName());
		
		List<PermissionBean> permissions = root.getPermissions();
		
		Map<String, ApplicationPermission> cache = new HashMap<String, ApplicationPermission>();
		
		for (PermissionBean permissionBean : permissions) {
			ApplicationPermission permission = new ApplicationPermission();
			permission.setName(permissionBean.getName());
			List<String> actions = permissionBean.getActions();
			if (actions != null)
				for (String action : actions) {
					permission.addAction(action);
				}
			app.addPermission(permission);
			cache.put(permission.getName(), permission);
		}
		
		wrapper.saveOrUpdate(app);
		
		List<RoleBean> roles = root.getRoles();
		for (RoleBean roleBean : roles) {
			ApplicationRole role = new ApplicationRole();
			role.setName(roleBean.getName());
			List<PermissionBean> permissionsInRole = roleBean.getPermissions();
			for (PermissionBean permissionBean : permissionsInRole) {
				if (!cache.containsKey(permissionBean.getName()))
						throw new IllegalArgumentException("RoleBean with unregistered permissions");
				ApplicationPermission permission = cache.get(permissionBean.getName());
				role.addPermission(permission);
			}
			app.addRole(role);
		}
		
		wrapper.saveOrUpdate(app);
		
	}
	
	@PostConstruct
	void init(){
		
		String fileRep = 
			"<root>" +
			"	<permission name='manageUser'/>" +
			"	<permission name='manageContact'/>" +
			"   <permission name='writeContent'/>" +
			"   <permission name='readContent'/>" +
			"		<permission name='reviseContent'/>" +
			"	<role name='AUTHOR'>" +
			"		<permission name='writeContent'/>" +
			"		<permission name='readContent'/>" +
			"	</role>" +
			"	<role name='REVISER'>" +
			"		<permission name='readContent'/>" +
			"		<permission name='reviseContent'/>" +
			"	</role>" +
			"</root>";
		
		this.read(fileRep);
		
	}
	
}
