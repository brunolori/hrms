package com.bvlsh.hr.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.PasswordForm;
import com.bvlsh.hr.models.Principal;
import com.bvlsh.hr.models.UserToken;
import com.bvlsh.hr.utils.StringUtil;


@Service
public class UserService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired TokenService tokenService;
	
	
	
	@Transactional
	public UserToken login(Principal principal)
	{
		String username = principal.getUsername();
		String password = principal.getPassword();
		
		
		User u = crudDAO.findById(User.class, username);
		
		if(u == null) throw new ValidationException("Perdoruesi nuk ekziston");
		/*
		if(u.getStatus() != IStatus.ACTIVE) 
		{
			throw new ValidationException("Perdoruesi nuk eshte aktiv");
		}
		*/
		if(!password.equals(u.getSecret()))
		{
			throw new ValidationException("Fjalekalimi i gabuar");
		}
		
		
		
		String token = tokenService.generateToken(u);
		/*
		Login login = new Login();
		login.setBrowser(principal.getBrowser());
		login.setIp(principal.getIp());
		login.setLoginTime(Calendar.getInstance().getTime());
		login.setToken(token);
		login.setUsername(u.getUsername());
		
		userDAO.create(login);
		*/
		return new UserToken(new Assembler().toDto(u),token);
		
	}
	
	@Transactional
	public void changePassword(PasswordForm form, String uname)
	{
		
		//User u = crudDAO.findById(User.class, uname);
		
		User user = crudDAO.findById(User.class, form.getUsername());
		
		if(!user.getSecret().equals(form.getOldPassword()))
		{
			throw new ValidationException("Fjalekalimi i vjeter eshte i pasakte");
		}
		
		if(!StringUtil.isValid(form.getNewPassword()) || form.getNewPassword().length() < 6)
		{
			throw new ValidationException("Fjalekalimi duhet te jete te pakten 6 karaktere");
		}
		
		
		user.setSecret(form.getNewPassword());
		
		crudDAO.update(user);
		
		
	}
	
	
}
