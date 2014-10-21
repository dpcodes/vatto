package com.aoks.enterprise.control.bean;

import java.util.List;

import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.CostCenter;
import com.aoks.utils.webmvc.GenericFactory;

public class CostCenterBean extends EnterpriseEntityBean<CostCenter> {

	private static final long serialVersionUID = 1L;
	
	private CostCenter model;
	
	private IndividualPartnerBean responsible;
	private CompanyBean company;
	
	
	public CostCenterBean() {
		super(null);
	}

	
	public IndividualPartnerBean getResponsible() {
		return responsible;
	}
	public void setResponsible(IndividualPartnerBean responsible) {
		this.responsible = responsible;
	}
	public String getResponsibleDescription(){
		return (responsible != null ? responsible.getName() : "Não informado");
	}
	
	
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public String getCompanyDescription(){
		return (company != null ? company.getName() : "Não informado");
	}
	

	@Override
	public CostCenter build(GenericFactory<CostCenter> factory) {
		return factory.create(this);
	}

	@Override
	public CostCenter getModel() {
		return model;
	}

	@Override
	public CostCenterBean load(CostCenter model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		
		List<? extends EnterpriseBehavior> parents = model.getBehavior().getParent("costDimension", EnterpriseEntityType.COMPANY);
		if (parents != null && parents.size() > 0)
			this.company = new CompanyBean().load((Company)parents.get(0).getEntity(), true);
		
		return this;
		
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CostCenterBean)) return false;
		CostCenterBean that = (CostCenterBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if (this.name == null ? that.name == null : this.name.equals(that.name))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else 
			result = result * 31 + (name != null ? name.hashCode() : 0);
		return result;
	}
	
	
}
