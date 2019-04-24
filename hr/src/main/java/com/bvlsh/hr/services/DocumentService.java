package com.bvlsh.hr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.Document;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.DocumentForm;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class DocumentService {

	@Autowired CrudDAO crudDAO;

	public Document registerDocument(DocumentForm form, String uname) {
		
		if (form == null) {
			throw new ValidationException("Forma e pa plotësuar");
		}
		if (!StringUtil.isValid(form.getDocumentName())) {
			throw new ValidationException("Plotësoni Emrin e Dokumentit !");
		}

		if (!StringUtil.isValid(form.getDescription())) {
			throw new ValidationException("Plotësoni Përshkrimin !");
		}
		
		
			return null;
		
	}
}
