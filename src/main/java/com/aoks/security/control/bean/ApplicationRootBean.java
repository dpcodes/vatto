package com.aoks.security.control.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@XmlRootElement(name="root")
public class ApplicationRootBean {

	@XmlAttribute
	private String name;
	
	@XmlElement(name="permission")
	private List<PermissionBean> permissions;
	
	@XmlElement(name="role")
	private List<RoleBean> roles;
	
	
	@XmlTransient
	public List<PermissionBean> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBean> permissions) {
		this.permissions = permissions;
	}
	
	
	@XmlTransient
	public List<RoleBean> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	
	
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
