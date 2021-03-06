package com.aoks.budget.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.enterprise.model.entities.Party;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.GenericModel;

/**
 * An abstract class for all flow account classes. 
 * Provides the basic functionality that is common to all of them.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="FlowItem", schema="budget")
public class FlowItem implements GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BudgetSequence")
	@TableGenerator(name="BudgetSequence", table="BudgetSequence", schema="budget", 
			pkColumnName="cTable", pkColumnValue="FlowItemSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	@Column(name="cAmount")
	private BigDecimal amount;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name="cInfo")
	private FlowItemInfo info;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cType")
	private FlowItemType type;
	
	@ManyToOne
	@JoinColumn(name="cOriginAccount")
	private OperationAccount origin;
	
	@ManyToOne
	@JoinColumn(name="cTargetAccount")
	private OperationAccount target;
	
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<FlowTransaction> transactions;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cStatus")
	private FlowStatus status;

	@Column(name="cCredit")
	private boolean credit;
	
	@Column(name="cTransfer")
	private boolean transfer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cCategory")
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cSubCategory")
	private Category subCategory;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cPayee")
	private Party payee;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="audityEntity")
	private AuditEntity auditEntity;
	
	@Transient
	private TransactionAlgorithm algorithm;
	
	
	public FlowItem() {
		transactions = new ArrayList<FlowTransaction>();
	}
	
	public List<FlowTransaction> generateTransactions(){
		
		if (type != null)
			algorithm = type.getAlgorithm();

		// new transactions generated
		List<FlowTransaction> txs = algorithm.generateTransactions(this);
		
		for (FlowTransaction myTx : transactions) {
			if (myTx.isClosed()){
				for (FlowTransaction tx : txs) {
					if (tx.isIdenticalTo(myTx))
						tx.copy(myTx);
				}
			}
		}
		
		for (FlowTransaction ft : txs) {
			ft.setItem(this);
		}
		
		transactions.clear();
		transactions.addAll(txs);
		
		return transactions;
	}
	
	
	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	public String getName() 		 			{ return name; }
	public void setName(String name) 			{ this.name = name; }
	
	public Category getCategory() 				{ return category; }
	public void setCategory(Category category)	{ this.category = category; }
	
	public Category getSubCategory() 					{ return subCategory; }
	public void setSubCategory(Category subCategory)	{ this.subCategory = subCategory; }
	
	public Party getPayee() 			{ return payee; }
	public void setPayee(Party payee)	{ this.payee = payee; }
	
	public String getDescription() 					{ return description; }
	public void setDescription(String description)	{ this.description = description; }
	
	public FlowItemInfo getInfo() {
		if (info == null && type != null)
			info = type.getInfo();
		return info;
	}
	public void setInfo(FlowItemInfo info) {
		this.info = info;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	public TransactionAlgorithm getAlgorithm() {
		if (algorithm == null && type != null)
			algorithm = type.getAlgorithm();
		return algorithm;
	}
	public void setAlgorithm(TransactionAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	
	public OperationAccount getOrigin() {
		return origin;
	}
	public void setOrigin(OperationAccount origin) {
		this.origin = origin;
	}
	
	
	public OperationAccount getTarget() {
		return target;
	}
	public void setTarget(OperationAccount target) {
		this.target = target;
	}
	
	public FlowItemType getType() {
		return type;
	}
	public void setType(FlowItemType type) {
		this.type = type;
	}

	
	public List<FlowTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<FlowTransaction> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
	}
	
	
	public boolean isTransfer() {
		if (!transfer)
			transfer = target != null;
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	
	public boolean isCredit() 					{ return credit; }
	public void setCredit(boolean credit) 		{ this.credit = credit; }
	
	public FlowStatus getStatus() 				{ return status; }
	public void setStatus(FlowStatus status)	{ this.status = status; }
	
}
