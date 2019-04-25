package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.GradeDAO;
import com.bvlsh.hr.entities.EmployeeGrade;
import com.bvlsh.hr.forms.GradeForm;
import com.bvlsh.hr.forms.GradeSx;

@Service
public class GradeService {
	
	@Autowired GradeDAO gradeDAO;
	
	public List<EmployeeGrade> searchGrades(GradeSx sx, String uname) {
		return gradeDAO.searchGrades(sx);
	}
	
	public EmployeeGrade registerGrade(GradeForm form, String uname) {return null;}
	public EmployeeGrade modifyGrade(GradeForm form, String uname) {return null;}
	public void deleteGrade(Integer gradeId, String uname) {}

}
