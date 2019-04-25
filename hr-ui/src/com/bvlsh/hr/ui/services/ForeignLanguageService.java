package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.ForeignLanguageClient;
import com.bvlsh.hr.ui.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.ui.forms.ForeignLanguageForm;
import com.bvlsh.hr.ui.forms.ForeignLanguageSx;

public class ForeignLanguageService {

	public EmployeeForeignLanguageDTO registerForeignLanguage(ForeignLanguageForm form) {
		return new ForeignLanguageClient().registerForeignLanguage(form);
	}

	public EmployeeForeignLanguageDTO modifyForeignLanguage(ForeignLanguageForm form) {
		return new ForeignLanguageClient().modifyForeignLanguage(form);
	}

	public EmployeeForeignLanguageDTO deleteForeignLanguage(Integer foreignLanguageId) {
		return new ForeignLanguageClient().deleteForeignLanguage(foreignLanguageId);
	}

	public List<EmployeeForeignLanguageDTO> searchForeignLanguages(ForeignLanguageSx sx) {
		return new ForeignLanguageClient().searchForeignLanguages(sx);
	}

}
