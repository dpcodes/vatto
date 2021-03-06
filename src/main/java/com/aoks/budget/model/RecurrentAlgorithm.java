package com.aoks.budget.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.aoks.utils.date.DateUtils;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class RecurrentAlgorithm implements TransactionAlgorithm {

	@Override
	public List<FlowTransaction> generateTransactions(FlowItem flowItem) {
		
		if (flowItem == null)
			throw new IllegalArgumentException();
		
		List<FlowTransaction> ret = new ArrayList<FlowTransaction>();
		
		RecurrentInfo info = (RecurrentInfo) flowItem.getInfo();
		
		Calendar start = info.getStart();
		Calendar end = info.getEnd();
		Periodicity periodicity = info.getPeriodicity();

		Calendar tmp = DateUtils.clone(start);
		
		int number = 0;
		while (tmp.before(end)) {
			++number;
			
			FlowTransaction transaction = new FlowTransaction();
			transaction.setNumber(number);
			
			Calendar occ = DateUtils.clone(tmp);
			
			transaction.setOcurrence(new GregorianCalendar(occ.get(Calendar.YEAR), occ.get(Calendar.MONTH), info.getMaturityDay()));
			transaction.setAmount(flowItem.getAmount());
			transaction.setAccount(flowItem.getOrigin());
			transaction.setDescription(flowItem.getDescription());
			
			transaction.setIdentity("" + periodicity.getUnit() + "_" + number);
			
			transaction.setStatus(FlowStatus.OPEN);
			transaction.setCredit(flowItem.isCredit());
			
			transaction.setCategory(flowItem.getCategory());
			transaction.setSubCategory(flowItem.getSubCategory());
			transaction.setPayee(flowItem.getPayee());
			
			tmp.add(periodicity.getUnit(), periodicity.getQuantity());
			
			ret.add(transaction);
			
		}
		
		return ret;
	}

	
}
