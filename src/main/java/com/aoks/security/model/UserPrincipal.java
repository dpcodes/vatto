package com.aoks.security.model;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br 
 *
 */
@Entity
@Table(name="UserPrincipal", schema="security")
public abstract class UserPrincipal implements Principal {

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SecuritySequence")
	@TableGenerator(name="SecuritySequence", table="SecuritySequence", schema="security", 
			pkColumnName="cTable", pkColumnValue="UserPrincipalSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	
	@ManyToOne
	@JoinColumn(name="cUser")
	private User user;
	
	@Column(name="cName")
	private String name;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
