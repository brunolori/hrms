package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.ContactClient;
import com.bvlsh.hr.ui.dto.ContactDTO;
import com.bvlsh.hr.ui.forms.ContactForm;

public class ContactService {

	public List<ContactDTO> getEmployeeContacts(String nid) {
		return new ContactClient().getEmployeeContacts(nid);
	}

	public ContactDTO registerContact(ContactForm form) {
		return new ContactClient().registerContact(form);
		
	}

	public ContactDTO modifyContact(ContactForm form) {
		return new ContactClient().modifyContact(form);
	}

	public void deleteContact(Integer id) {
		new ContactClient().deleteContact(id);
	}

}
