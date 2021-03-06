package com.aoks.budget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="InstalmentsInfo", schema="budget")
public class InstalmentsInfo extends FlowItemInfo {
	
	private static final long serialVersionUID = 1L;

	@Column(name="cInstalments")
	private int instalments;
	
	public int getInstalments() {
		return instalments;
	}
	public void setInstalments(int instalments) {
		this.instalments = instalments;
	}
	
	
}
