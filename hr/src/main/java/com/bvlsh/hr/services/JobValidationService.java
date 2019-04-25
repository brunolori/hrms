package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.JobValidationDAO;
import com.bvlsh.hr.entities.JobValidation;
import com.bvlsh.hr.forms.JobValidationForm;
import com.bvlsh.hr.forms.JobValidationSx;

@Service
public class JobValidationService {
	
	@Autowired JobValidationDAO jobValidationDAO;
	
	
	public List<JobValidation> searchJobValidations(JobValidationSx sx, String uname) {
		return jobValidationDAO.searchJobValidations(sx);
	}
	
	
	public JobValidation registerJobValidation(JobValidationForm form, String uname) {return null;}
	public JobValidation modifyJobValidation(JobValidationForm form, String uname) {return null;}
	public void deleteJobValidation(Integer jobValidationId, String uname) {}

}
