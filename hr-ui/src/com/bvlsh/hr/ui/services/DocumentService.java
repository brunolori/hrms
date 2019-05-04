package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.DocumentClient;
import com.bvlsh.hr.ui.dto.DocumentDTO;
import com.bvlsh.hr.ui.forms.DocumentForm;

public class DocumentService {

	public List<DocumentDTO> getEmployeeDocuments(String nid) {
		return new DocumentClient().getEmployeeDocuments(nid);
	}

	public DocumentDTO registerDocument(DocumentForm documentForm) {
		return null;
	}

	public DocumentDTO modifyDocument(DocumentForm documentForm) {
		return null;
	}

	public void deleteDocument(Integer id) {
		
	}

}
