package com.aoks.banking.bank.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.banking.bank.control.bean.BankBean;
import com.aoks.utils.webmvc.ModelConverter;


@FacesConverter("bankConverter")
public class BankBeanConverter extends ModelConverter<BankBean> {

	private static Map<Long, BankBean> mapped = new HashMap<Long, BankBean>();

	@Override
	protected Map<Long, BankBean> mapped() {
		return mapped;
	}

}
