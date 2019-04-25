package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.EducationClient;
import com.bvlsh.hr.ui.dto.EducationDTO;
import com.bvlsh.hr.ui.forms.EducationForm;
import com.bvlsh.hr.ui.forms.EducationSx;

public class EducationService {

	public EducationDTO registerJobValidation(EducationForm form) {
		return new EducationClient().registerEducationn(form);
	}

	public EducationDTO modifyEducation(EducationForm form) {
		return new EducationClient().modifyEducation(form);
	}

	public EducationDTO deleteEducation(Integer educationId) {
		return new EducationClient().deleteEducation(educationId);
	}

	public List<EducationDTO> searchEducations(EducationSx sx) {
		return new EducationClient().searchEducations(sx);
	}

}
