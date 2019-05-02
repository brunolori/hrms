package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.GradeDAO;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeGrade;
import com.bvlsh.hr.entities.Grade;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.GradeForm;
import com.bvlsh.hr.forms.GradeSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class GradeService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired GradeDAO gradeDAO;
	
	public List<EmployeeGrade> searchGrades(GradeSx sx, String uname) {
		return gradeDAO.searchGrades(sx);
	}
	
	@Transactional
	public EmployeeGrade registerGrade(GradeForm form, String uname) {

		
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Percaktoni punonjesin");
		}
		
		if(form.getGradeId() == null)
		{
			throw new ValidationException("Percaktoni graden");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Percaktoni daten e fillimit");
		}
		
	EmployeeGrade g = new EmployeeGrade();
	g.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
	g.setEndDate(form.getEndDate());
	g.setGrade(crudDAO.findById(Grade.class, form.getGradeId()));
	g.setStartDate(form.getStartDate());
	g.setStatus(IStatus.ACTIVE);
	
	return crudDAO.create(g);
	
	}
	
	@Transactional
	public EmployeeGrade modifyGrade(GradeForm form, String uname) {

		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Percaktoni punonjesin");
		}
		
		if(form.getGradeId() == null)
		{
			throw new ValidationException("Percaktoni graden");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Percaktoni daten e fillimit");
		}
		
	EmployeeGrade g = crudDAO.findById(EmployeeGrade.class, form.getId());
	g.setEndDate(form.getEndDate());
	g.setGrade(crudDAO.findById(Grade.class, form.getGradeId()));
	g.setStartDate(form.getStartDate());
	
	return crudDAO.update(g);
	
	}
	
	@Transactional
	public void deleteGrade(Integer gradeId, String uname) {
		EmployeeGrade g = crudDAO.findById(EmployeeGrade.class, gradeId);
		g.setStatus(IStatus.NOT_ACTIVE);
		crudDAO.update(g);
	}

	public List<EmployeeGrade> getEmployeeGrades(String nid, String uname) {
		return gradeDAO.getEmployeeGrades(nid);
	}

}
