package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.JobValidationClient;
import com.bvlsh.hr.ui.dto.JobValidationDTO;
import com.bvlsh.hr.ui.forms.JobValidationForm;
import com.bvlsh.hr.ui.forms.JobValidationSx;

public class JobValidationService {

	public JobValidationDTO registerJobValidation(JobValidationForm form) {
		return new JobValidationClient().registerJobValidation(form);
	}

	public JobValidationDTO modifyJobValidation(JobValidationForm form) {
		return new JobValidationClient().modifyJobValidation(form);
	}

	public JobValidationDTO deleteJobValidation(Integer jobValidationId) {
		return new JobValidationClient().deleteJobValidation(jobValidationId);
	}

	public List<JobValidationDTO> searchJobValidations(JobValidationSx sx) {
		return new JobValidationClient().searchJobValidations(sx);
	}

	public List<JobValidationDTO> getEmployeeValidations(String nid) {
		return new JobValidationClient().getEmployeeValidations(nid);
	}

}
