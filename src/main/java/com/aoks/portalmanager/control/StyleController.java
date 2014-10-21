package com.aoks.portalmanager.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("styleController")
@SessionScoped
public class StyleController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String theme="cupertino";
	
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
}
