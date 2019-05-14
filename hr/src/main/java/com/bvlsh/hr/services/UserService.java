package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.UserDAO;
import com.bvlsh.hr.entities.Department;
import com.bvlsh.hr.entities.Role;
import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.PasswordForm;
import com.bvlsh.hr.forms.UserForm;
import com.bvlsh.hr.models.Principal;
import com.bvlsh.hr.models.UserToken;
import com.bvlsh.hr.utils.StringUtil;


@Service
public class UserService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired TokenService tokenService;
	@Autowired UserDAO userDAO;
	
	
	
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
			throw new ValidationException("Fjalëkalimi duhet të jetë të paktën 6 karaktere!");
		}
		
		
		user.setSecret(form.getNewPassword());
		
		crudDAO.update(user);
		
		
	}
	
	@Transactional
	public User registerUser(UserForm form, String uname) {

		if(!StringUtil.isValid(form.getUsername()))
		{
			throw new ValidationException("Plotësoni Username!");
		}
		if(!StringUtil.isValid(form.getSecret()))
		{
			throw new ValidationException("Plotësoni Passwordin!");
		}
		
		if(form.getRootDepartmentId() == null)
		{
			throw new ValidationException("Zgjidhni drejtorine përkatëse");
		}
		
		if(form.getRoleCode() == null)
		{
			throw new ValidationException("Zgjidhni rolin përkatëse");
		}
	
		User ex = crudDAO.findById(User.class, form.getUsername());
		if(ex != null)
		{
			throw new ValidationException("Perdoruesi '" + ex.getUsername() + "' ekziston ne sistem. Struktura: "
		                               + ex.getRootDepartment().getName() + ", STATUS==>" + (ex.getStatus()==1?"AKTIV":"JO AKTIV"));
		}
		
		User u = new User();
        u.setUsername(form.getUsername());
        u.setSecret(form.getSecret());
		u.setStatus(IStatus.ACTIVE);
		if(form.getRootDepartmentId() != null)
		{
			u.setRootDepartment(crudDAO.findById(Department.class, form.getRootDepartmentId()));
		}
		
		u.setRole(crudDAO.findById(Role.class, form.getRoleCode()));
		
		return crudDAO.create(u);
	
	}
	
	@Transactional
	public User modifyUser(UserForm form, String uname) {

		if(!StringUtil.isValid(form.getUsername()))
		{
			throw new ValidationException("Plotësoni Username!");
		}
		if(!StringUtil.isValid(form.getSecret()))
		{
			throw new ValidationException("Plotësoni Passwordin!");
		}
		
		if(form.getRootDepartmentId() == null)
		{
			throw new ValidationException("Zgjidhni drejtorine përkatëse");
		}
		
		if(form.getRoleCode() == null)
		{
			throw new ValidationException("Zgjidhni rolin përkatëse");
		}
	
	
		User u = crudDAO.findById(User.class, form.getUsername());
        //u.setUsername(form.getUsername());
        u.setSecret(form.getSecret());
		u.setStatus(IStatus.ACTIVE);
		if(form.getRootDepartmentId() != null)
		{
			u.setRootDepartment(crudDAO.findById(Department.class, form.getRootDepartmentId()));
		}
		
		u.setRole(crudDAO.findById(Role.class, form.getRoleCode()));
		
		return crudDAO.update(u);
	
	}
	
	
	public List<User> searchUsers(List<Integer> deptIds, String uname) {
		return userDAO.searchUsers(deptIds);
	}
	
}
