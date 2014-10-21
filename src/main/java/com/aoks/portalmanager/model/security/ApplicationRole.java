package com.aoks.portalmanager.model.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="ApplicationRole", schema="portalManager")
public class ApplicationRole {

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SecuritySequence")
	@TableGenerator(name="SecuritySequence", table="SecuritySequence", schema="portalManager", 
			pkColumnName="cTable", pkColumnValue="ApplicationRoleSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cName")
	private String name;
	
	@OneToMany
	@JoinColumn(name="cRole")
	private Set<ApplicationPermission> permissions = new HashSet<ApplicationPermission>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void addPermission(ApplicationPermission permission){
		permissions.add(permission);
	}
	public Set<ApplicationPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<ApplicationPermission> permissions) {
		this.permissions = permissions;
	}
	
}
