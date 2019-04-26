package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.GradeClient;
import com.bvlsh.hr.ui.dto.EmployeeGradeDTO;
import com.bvlsh.hr.ui.forms.GradeForm;
import com.bvlsh.hr.ui.forms.GradeSx;

public class GradeService 
{
	public EmployeeGradeDTO registerGrade(GradeForm form) {
		return new GradeClient().registerGrade(form);
	}

	public EmployeeGradeDTO modifyGrade(GradeForm form) {
		return new GradeClient().modifyGrade(form);
	}

	public EmployeeGradeDTO deleteGrade(Integer gradeId) {
		return new GradeClient().deleteGrade(gradeId);
	}

	public List<EmployeeGradeDTO> searchGrades(GradeSx sx) {
		return new GradeClient().searchGrades(sx);
	}

}
