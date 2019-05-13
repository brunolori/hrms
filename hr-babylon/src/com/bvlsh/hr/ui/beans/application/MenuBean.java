package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.bvlsh.hr.ui.utils.Util;




@ManagedBean
@RequestScoped
public class MenuBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{navBean}")
	NavBean nav;
	

	
	public NavBean getNav() {
		return nav;
	}
	
	public void setNav(NavBean nav) {
		this.nav = nav;
	}

	
	
	public void navigate(String page, String role)
	{
		nav.navigate(page);
		Util.redirect(role+"/main");
	}
	

}
