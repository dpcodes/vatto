package com.aoks.banking.operation.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="CashAccount", schema="banking")
public class CashAccount extends OperationAccount {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The date in which that account started its operation <b>in the system</b>,
	 * to contrast with the date of the account opening in the Bank.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="cStart")
	protected Calendar start;

	
	/**
	 * The start balance for this account.
	 */
	@Column(name="cStartBalance")
	protected BigDecimal startBalance;
	
	
	public Calendar getStart() {
		return start;
	}
	public void setStart(Calendar start) {
		this.start = start;
	}
	
	
	public BigDecimal getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}
}
