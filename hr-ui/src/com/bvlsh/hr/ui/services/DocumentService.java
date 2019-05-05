package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.DocumentClient;
import com.bvlsh.hr.ui.dto.DocumentDTO;
import com.bvlsh.hr.ui.forms.DocumentForm;
import com.bvlsh.hr.ui.forms.MediaDTO;

public class DocumentService {

	public List<DocumentDTO> getEmployeeDocuments(String nid) {
		return new DocumentClient().getEmployeeDocuments(nid);
	}

	public DocumentDTO registerDocument(DocumentForm form) {
		return new DocumentClient().registerDocument(form);
	}

	public DocumentDTO modifyDocument(DocumentForm form) {
		return new DocumentClient().modifyDocument(form);
	}

	public void deleteDocument(Integer id) {
		new DocumentClient().deleteDocument(id);
	}

	public MediaDTO getDocumentMedia(Integer docId)
	{
		return new DocumentClient().getDocumentMedia(docId);
	}
	
}
