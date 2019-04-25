package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.EducationDAO;
import com.bvlsh.hr.entities.Education;
import com.bvlsh.hr.forms.EducationForm;
import com.bvlsh.hr.forms.EducationSx;

@Service
public class EducationService {

	@Autowired EducationDAO educationDAO;

	public List<Education> searchEducations(EducationSx sx, String uname) {
		return educationDAO.searchEducations(sx);
	}

	public Education registerEducation(EducationForm form, String uname) {return null;}

	public Education modifyEducation(EducationForm form, String uname) {return null;}

	public void deleteEducation(Integer educationId, String uname) {}

}
