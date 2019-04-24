package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.bvlsh.hr.ui.forms.PasswordForm;
import com.bvlsh.hr.ui.models.Principal;
import com.bvlsh.hr.ui.models.UserToken;
import com.bvlsh.hr.ui.services.UserService;
import com.bvlsh.hr.ui.utils.Messages;
import com.bvlsh.hr.ui.utils.StringUtil;
import com.bvlsh.hr.ui.utils.Util;

import lombok.Getter;
import lombok.Setter;






@ManagedBean
@SessionScoped
@Getter @Setter
public class LoginBean implements Serializable {


	private static final long serialVersionUID = 1L;
	

	@ManagedProperty(value="#{navBean}")
	NavBean nav;
	
	String username;
	String password;
	
	UserToken userToken;
	
	PasswordForm form;
	
	
	
	
	@PostConstruct
	public void load()
	{
		this.form = new PasswordForm();
	}
	
	
	
	
	public void login() {
		
		
		if(!StringUtil.isValid(username))
		{
			Messages.throwFacesMessage("Plotëso Përdoruesin", 3);
			return;
		}
		
		if(!StringUtil.isValid(password))
		{
			Messages.throwFacesMessage("Plotëso Fjalëkalimin", 3);
			return;
		}
		
		Principal principal = new Principal();
		principal.setUsername(username);
		principal.setPassword(password);
		principal.setBrowser(Util.getBrowser());
		principal.setIp(Util.getAuthenticatedUserIp());
		
		try {
			
			this.userToken = new UserService().login(principal);
			
			nav.navigate("home");
			Util.redirect("pages/main");			

		}catch(Exception a) {
			Messages.throwFacesMessage(a);
		}
		
		
		
	}
	

	 public String logout() {
		try {
		            HttpSession session = Util.getSession();
		            if (session != null) {
		                session.invalidate();
		            }
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		return "/login.xhtml?faces-redirect=true";
	}

		
	public void changePassword()
	{
		try {
			new UserService().changePassword(form);
			this.form = new PasswordForm();
			Messages.throwFacesMessage("Fjalekalimi u ndryshua me sukses",1);
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	
	public void clearPasswordForm()
	{
		this.form = new PasswordForm();
	}
	
	

}
