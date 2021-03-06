package com.aoks.sales.integrations.chainit.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="Product", schema="sales")
public class Product implements GenericModel {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="ProductSequence")
	@TableGenerator(name="ProductSequence", table="ProductSequence", schema="sales", 
			pkColumnName="cTable", pkColumnValue="ProductSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cCode")
	private String code;
	
	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	@Column(name="cPrice")
	private BigDecimal price;
	
	@Column(name="cDiscount")
	private BigDecimal discount;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public long getId() 									{ return id; }
	@Override public void setId(long id)							{ this.id = id; }

	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	public String getCode() 						{ return code; }
	public void setCode(String code) 				{ this.code = code; }
	
	public String getName() 						{ return name; }
	public void setName(String name) 				{ this.name = name; }
	
	public String getDescription() 					{ return description; }
	public void setDescription(String description)	{ this.description = description; }
	
	public BigDecimal getPrice() 					{ return price; }
	public void setPrice(BigDecimal price) 			{ this.price = price; }
	
	public BigDecimal getDiscount() 				{ return discount; }
	public void setDiscount(BigDecimal discount) 	{ this.discount = discount; }
		
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Product)) return false;
        Product that = (Product) obj;

        if (this.getId() > 0 && this.getId() == that.getId())
        	return true;

        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        if (getId() > 0)
        	result = result * 31 + (int)(getId()^(getId()>>>32));
        return result;
    }
	
}
