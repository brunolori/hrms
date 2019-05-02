package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.JobValidationDAO;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.JobValidation;
import com.bvlsh.hr.entities.ValidationType;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.JobValidationForm;
import com.bvlsh.hr.forms.JobValidationSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class JobValidationService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired JobValidationDAO jobValidationDAO;
	
	
	public List<JobValidation> searchJobValidations(JobValidationSx sx, String uname) {
		return jobValidationDAO.searchJobValidations(sx);
	}
	
	@Transactional
	public JobValidation registerJobValidation(JobValidationForm form, String uname) {

		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Percaktoni punonjesin");
		}
		
		if(form.getValidationTypeId() == null)
		{
			throw new ValidationException("Percaktoni tipin e vleresimit");
		}
		
		if(form.getValidationDate() == null)
		{
			throw new ValidationException("Percaktoni daten e vleresimit");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Percaktoni daten e fillimit");
		}
		
		if(form.getEndDate() == null)
		{
			throw new ValidationException("Percaktoni daten e mbarimit");
		}
	
		if(!StringUtil.isValid(form.getSignedBy()))
		{
			throw new ValidationException("Percaktoni se cili ka firmosur");
		}
		
		
		JobValidation v = new JobValidation();
		v.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		v.setCreateTime(Calendar.getInstance().getTime());
		v.setCreateUser(uname);
		v.setEndDate(form.getEndDate());
		v.setSignedBy(form.getSignedBy());
		v.setStartDate(form.getStartDate());
		v.setStatus(IStatus.ACTIVE);
		v.setUpdateTime(Calendar.getInstance().getTime());
		v.setUpdateUser(uname);
		v.setValidationDate(form.getValidationDate());
		v.setValidationType(crudDAO.findById(ValidationType.class, form.getValidationTypeId()));
		
		return crudDAO.create(v);
		
	
	}
	
	@Transactional
	public JobValidation modifyJobValidation(JobValidationForm form, String uname) {

		if(form.getId() == null)
		{
			throw new ValidationException("Vleresimi i papercaktuar");
		}
		
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Percaktoni punonjesin");
		}
		
		if(form.getValidationTypeId() == null)
		{
			throw new ValidationException("Percaktoni tipin e vleresimit");
		}
		
		if(form.getValidationDate() == null)
		{
			throw new ValidationException("Percaktoni daten e vleresimit");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Percaktoni daten e fillimit");
		}
		
		if(form.getEndDate() == null)
		{
			throw new ValidationException("Percaktoni daten e mbarimit");
		}
	
		if(!StringUtil.isValid(form.getSignedBy()))
		{
			throw new ValidationException("Percaktoni se cili ka firmosur");
		}
		
		
		JobValidation v = crudDAO.findById(JobValidation.class, form.getId());
		v.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		v.setEndDate(form.getEndDate());
		v.setSignedBy(form.getSignedBy());
		v.setStartDate(form.getStartDate());
		v.setUpdateTime(Calendar.getInstance().getTime());
		v.setUpdateUser(uname);
		v.setValidationDate(form.getValidationDate());
		v.setValidationType(crudDAO.findById(ValidationType.class, form.getValidationTypeId()));
		
		return crudDAO.update(v);
	
	}
	
	@Transactional
	public void deleteJobValidation(Integer jobValidationId, String uname) {
		
		JobValidation v = crudDAO.findById(JobValidation.class, jobValidationId);
		v.setUpdateTime(Calendar.getInstance().getTime());
		v.setUpdateUser(uname);
		v.setStatus(IStatus.NOT_ACTIVE);
		
		crudDAO.update(v);
	}


	public List<JobValidation> getEmployeeValidations(String nid, String uname) {
		return jobValidationDAO.getEmployeeValidations(nid);
	}

}
