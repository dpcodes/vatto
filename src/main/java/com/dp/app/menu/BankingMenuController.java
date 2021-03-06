package com.dp.app.menu;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("bankingMenuController")
@SessionScoped
public class BankingMenuController extends ApplicationMenuController implements Serializable{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		loadModule("BANKING-WRAPPER");
		load("BANK-UNIT");
	}

}
