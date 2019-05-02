package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.EducationDAO;
import com.bvlsh.hr.entities.Education;
import com.bvlsh.hr.entities.EducationType;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.Institution;
import com.bvlsh.hr.entities.StudyField;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.EducationForm;
import com.bvlsh.hr.forms.EducationSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class EducationService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired EducationDAO educationDAO;

	
	public List<Education> searchEducations(EducationSx sx, String uname) {
		return educationDAO.searchEducations(sx);
	}

	@Transactional
	public Education registerEducation(EducationForm form, String uname) {

		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		
		if(form.getEducationTypeId() == null)
		{
			throw new ValidationException("Zgjidhni tipin e edukimit");
		}
	
		Education e = new Education();
		e.setCreateTime(Calendar.getInstance().getTime());
		e.setCreateUser(uname);
		e.setUpdateTime(Calendar.getInstance().getTime());
		e.setUpdateUser(uname);
		e.setDescription(form.getDescription());
		e.setEducationType(crudDAO.findById(EducationType.class, form.getEducationTypeId()));
		e.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		e.setIssueDate(form.getIssueDate());
		e.setStatus(IStatus.ACTIVE);
		if(form.getInstitutionId() != null)
		{
			e.setInstitution(crudDAO.findById(Institution.class, form.getInstitutionId()));
		}
		if(form.getStudyFieldId() != null)
		{
			e.setStudyField(crudDAO.findById(StudyField.class, form.getStudyFieldId()));
		}
		
		return crudDAO.create(e);
	
	}

	@Transactional
	public Education modifyEducation(EducationForm form, String uname) {

		
		if(form.getId() == null)
		{
			throw new ValidationException("Edukimi i papercaktuar");
		}
		
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		
		if(form.getEducationTypeId() == null)
		{
			throw new ValidationException("Zgjidhni tipin e edukimit");
		}
	
		Education e = crudDAO.findById(Education.class, form.getId());
		e.setUpdateTime(Calendar.getInstance().getTime());
		e.setUpdateUser(uname);
		e.setDescription(form.getDescription());
		e.setEducationType(crudDAO.findById(EducationType.class, form.getEducationTypeId()));
		e.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		e.setIssueDate(form.getIssueDate());
		if(form.getInstitutionId() != null)
		{
			e.setInstitution(crudDAO.findById(Institution.class, form.getInstitutionId()));
		}
		if(form.getStudyFieldId() != null)
		{
			e.setStudyField(crudDAO.findById(StudyField.class, form.getStudyFieldId()));
		}
		
		return crudDAO.update(e);
	
	}

	@Transactional
	public void deleteEducation(Integer educationId, String uname) {
		
		Education e = crudDAO.findById(Education.class, educationId);
		e.setUpdateTime(Calendar.getInstance().getTime());
		e.setUpdateUser(uname);
		e.setStatus(IStatus.NOT_ACTIVE);
		
		crudDAO.update(e);
	}

	public List<Education> getEmployeeEducations(String nid, String uname) {
		return educationDAO.getEmployeeEducations(nid);
	}

}
