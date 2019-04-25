package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.ForeignLanguageDAO;
import com.bvlsh.hr.entities.EmployeeForeignLanguage;
import com.bvlsh.hr.forms.ForeignLanguageForm;
import com.bvlsh.hr.forms.ForeignLanguageSx;

@Service
public class ForeignLanguageService {

	@Autowired ForeignLanguageDAO foreignLanguageDAO;

	public List<EmployeeForeignLanguage> searchForeignLanguages(ForeignLanguageSx sx, String uname) {

		return foreignLanguageDAO.searchForeignLanguages(sx);

	}

	public EmployeeForeignLanguage registerForeignLanguage(ForeignLanguageForm form, String uname) {return null;}

	public EmployeeForeignLanguage modifyForeignLanguage(ForeignLanguageForm form, String uname) {return null;}

	public EmployeeForeignLanguage deleteForeignLanguage(Integer foreignLanguageId, String uname) {return null;}

}
