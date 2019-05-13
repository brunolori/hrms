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
	
	public List<EmployeeGrade> searchGrades(GradeSx sx, List<Integer> deptIds, String uname) {
		return gradeDAO.searchGrades(sx, deptIds);
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
		
		List<EmployeeGrade> grades = gradeDAO.getEmployeeGrades(form.getPersonNid());
		if(grades != null && !grades.isEmpty())
		{
			if(form.getGradeId() == grades.get(0).getGrade().getId())
			{
				throw new ValidationException("Punonjesi ka aktualisht graden: "+grades.get(0).getGrade().getTag());
			}
		}
		
		Grade grade = crudDAO.findById(Grade.class, form.getGradeId());
		Employee e = crudDAO.findById(Employee.class, form.getPersonNid());
		e.setGrade(grade);
		e = crudDAO.update(e);
		
		EmployeeGrade g = new EmployeeGrade();
		g.setEmployee(e);
		g.setEndDate(form.getEndDate());
		g.setGrade(grade);
		g.setStartDate(form.getStartDate());
		g.setStatus(IStatus.ACTIVE);
		
		return crudDAO.create(g);
	
	}
	
	@Transactional
	public EmployeeGrade modifyGrade(GradeForm form, String uname) {

		if(form.getId() == null)
		{
			throw new ValidationException("Percaktoni graden qe do modifikoni");
		}
		
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
		
		Grade grade = crudDAO.findById(Grade.class, form.getGradeId());
		List<EmployeeGrade> grades = gradeDAO.getEmployeeGrades(form.getPersonNid());
		if(grades != null && !grades.isEmpty())
		{
			if(form.getGradeId() == grades.get(0).getGrade().getId())
			{
				Employee e = crudDAO.findById(Employee.class, form.getPersonNid());
				e.setGrade(grade);
				e = crudDAO.update(e);
			}
		}		
		
		EmployeeGrade g = crudDAO.findById(EmployeeGrade.class, form.getId());
		g.setEndDate(form.getEndDate());
		g.setGrade(grade);
		g.setStartDate(form.getStartDate());
		
		return crudDAO.update(g);
	
	}
	
	@Transactional
	public void deleteGrade(Integer gradeId, String uname) {
		
		
		EmployeeGrade g = crudDAO.findById(EmployeeGrade.class, gradeId);
		g.setStatus(IStatus.NOT_ACTIVE);
		g = crudDAO.update(g);
		
		List<EmployeeGrade> grades = gradeDAO.getEmployeeGrades(g.getEmployee().getNid());
		if(grades != null && !grades.isEmpty())
		{
			if(g.getId() == grades.get(0).getId()) // nqs po fshine graden e fundit
			{
				Employee e = g.getEmployee();
				e.setGrade(null);
				crudDAO.update(e);
			}
		}
		
		
	}

	public List<EmployeeGrade> getEmployeeGrades(String nid, String uname) {
		return gradeDAO.getEmployeeGrades(nid);
	}

}
