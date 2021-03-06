package com.aoks.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="GroupInfo", schema="security")
public class GroupInfo {
	
	@Id
	@Column(name="cName")
	private String name;
	
	@ManyToMany
	@JoinTable(name="GroupInfo_LoginInfo", schema="security",
			joinColumns=@JoinColumn(name="cGroup"),
			inverseJoinColumns=@JoinColumn(name="cUser"))
	private List<LoginInfo> users = new ArrayList<LoginInfo>();


	public List<LoginInfo> getUsers()			{ return users; }
	public void setUsers(List<LoginInfo> users) { this.users = users; }
	
	public String getName() 			{ return name; }
	public void setName(String name)	{ this.name = name; }
	
}
