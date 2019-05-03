package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.ForeignLanguageDAO;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeForeignLanguage;
import com.bvlsh.hr.entities.ForeignLanguage;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.ForeignLanguageForm;
import com.bvlsh.hr.forms.ForeignLanguageSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class ForeignLanguageService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired ForeignLanguageDAO foreignLanguageDAO;

	public List<EmployeeForeignLanguage> searchForeignLanguages(ForeignLanguageSx sx, String uname) {

		return foreignLanguageDAO.searchForeignLanguages(sx);

	}

	
	@Transactional
	public EmployeeForeignLanguage registerForeignLanguage(ForeignLanguageForm form, String uname) {

		
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		
		if(form.getForeignLanguageId() == null)
		{
			throw new ValidationException("Zgjidhni gjuhen");
		}
		
		if(!StringUtil.isValid(form.getLevel()))
		{
			throw new ValidationException("Plotesoni nivelin");
		}
		
		
		EmployeeForeignLanguage f = new EmployeeForeignLanguage();
		f.setCreateTime(Calendar.getInstance().getTime());
		f.setCreateUser(uname);
		f.setUpdateTime(Calendar.getInstance().getTime());
		f.setUpdateUser(uname);
		f.setStatus(IStatus.ACTIVE);
		f.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		f.setForeignLanguage(crudDAO.findById(ForeignLanguage.class, form.getForeignLanguageId()));
		f.setLevel(form.getLevel());
		f.setDescription(form.getDescription());
		
		
		return crudDAO.create(f);
				
		
	}

	@Transactional
	public EmployeeForeignLanguage modifyForeignLanguage(ForeignLanguageForm form, String uname) {

	
		if(form.getId() == null)
		{
			throw new ValidationException("Gjuha e papercaktuar");
		}
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		
		if(form.getForeignLanguageId() == null)
		{
			throw new ValidationException("Zgjidhni gjuhen");
		}
		
		if(!StringUtil.isValid(form.getLevel()))
		{
			throw new ValidationException("Plotesoni nivelin");
		}
		
		
		EmployeeForeignLanguage f = crudDAO.findById(EmployeeForeignLanguage.class, form.getId());
		f.setUpdateTime(Calendar.getInstance().getTime());
		f.setUpdateUser(uname);
		f.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		f.setForeignLanguage(crudDAO.findById(ForeignLanguage.class, form.getForeignLanguageId()));
		f.setLevel(form.getLevel());
		f.setDescription(form.getDescription());
		
		
		return crudDAO.update(f);
	
	}

	
	@Transactional
	public void deleteForeignLanguage(Integer foreignLanguageId, String uname) {
		EmployeeForeignLanguage fl = crudDAO.findById(EmployeeForeignLanguage.class, foreignLanguageId);
		fl.setUpdateTime(Calendar.getInstance().getTime());
		fl.setUpdateUser(uname);
		fl.setStatus(IStatus.NOT_ACTIVE);
		
		crudDAO.update(fl);
	}

	public List<EmployeeForeignLanguage> getEmployeeLanguages(String nid, String uname) {
		return foreignLanguageDAO.getEmployeeLanguages(nid);
	}

}
