package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.ContactDAO;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.Contact;
import com.bvlsh.hr.entities.ContactType;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.ContactForm;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class ContactService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired ContactDAO contactDAO;
	

	@Transactional
	public Contact registerContact(ContactForm form, String uname) {

		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(!StringUtil.isValid(form.getValue()))
		{
			throw new ValidationException("Plotesoni kontaktin");
		}
		if(form.getContactTypeId() == null)
		{
			throw new ValidationException("Zgjidhni tipin e kontaktit");
		}
		
		Contact c = new Contact();
		c.setContactType(crudDAO.findById(ContactType.class, form.getContactTypeId()));
		c.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		c.setValue(form.getValue());
		c.setStatus(IStatus.ACTIVE);
		c.setCreateTime(Calendar.getInstance().getTime());
		c.setCreateUser(uname);
		c.setUpdateTime(Calendar.getInstance().getTime());
		c.setUpdateUser(uname);
		
		return crudDAO.create(c);
		
	}

	
	@Transactional
	public Contact modifyContact(ContactForm form, String uname) {
		
		if(form.getId() == null)
		{
			throw new ValidationException("Kontakti i papercaktuar");
		}
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(!StringUtil.isValid(form.getValue()))
		{
			throw new ValidationException("Plotesoni kontaktin");
		}
		if(form.getContactTypeId() == null)
		{
			throw new ValidationException("Zgjidhni tipin e kontaktit");
		}
		
		Contact c = crudDAO.findById(Contact.class, form.getId());
		c.setContactType(crudDAO.findById(ContactType.class, form.getContactTypeId()));
		c.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		c.setValue(form.getValue());
		c.setUpdateTime(Calendar.getInstance().getTime());
		c.setUpdateUser(uname);
		
		return crudDAO.update(c);
	}

	
	@Transactional
	public void deleteContact(Integer id, String uname) {
		
		Contact c = crudDAO.findById(Contact.class, id);
		c.setStatus(IStatus.NOT_ACTIVE);
		c.setUpdateTime(Calendar.getInstance().getTime());
		c.setUpdateUser(uname);
		
		crudDAO.update(c);
		
	}

	
	public List<Contact> getEmployeeContacts(String nid, String uname) {
		return contactDAO.getEmployeeContacts(nid);
	}

}
