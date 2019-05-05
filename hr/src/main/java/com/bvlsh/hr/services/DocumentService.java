package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.DocumentDAO;
import com.bvlsh.hr.entities.Document;
import com.bvlsh.hr.entities.DocumentMedia;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.DocumentForm;
import com.bvlsh.hr.utils.CalculatorUtil;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class DocumentService {

	@Autowired CrudDAO crudDAO;
	@Autowired DocumentDAO documentDAO;

	
	@Transactional
	public Document registerDocument(DocumentForm form, String uname) {
		
		if (!StringUtil.isValid(form.getPersonNid())) {
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		
		if (!StringUtil.isValid(form.getDocumentName())) {
			throw new ValidationException("Plotësoni emrin e dokumentit");
		}
		
		if (!StringUtil.isValid(form.getData())) {
			throw new ValidationException("Ngarko dokumentin");
		}

		Document d = new Document();
		d.setCreateTime(Calendar.getInstance().getTime());
		d.setCreateUser(uname);
		d.setUpdateTime(Calendar.getInstance().getTime());
		d.setUpdateUser(uname);
		d.setStatus(IStatus.ACTIVE);
		d.setDescription(form.getDescription());
		d.setDocumentDate(form.getDocumentDate());
		d.setDocumentName(form.getDocumentName());
		d.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		
		d = crudDAO.create(d);
		
		DocumentMedia m = new DocumentMedia();
		m.setContent(CalculatorUtil.decodeBASE64(form.getData()));
		m.setDocument(d);
		
		crudDAO.create(m);
		
		return d;
		
	}
	
	@Transactional
    public Document modifyDocument(DocumentForm form, String uname) {
		
	   if (form.getId() == null) {
			throw new ValidationException("Dokumenti i papercaktuar");
		}
		
		if (!StringUtil.isValid(form.getDocumentName())) {
			throw new ValidationException("Plotësoni emrin e dokumentit");
		}
		
		Document d = crudDAO.findById(Document.class, form.getId());
		d.setUpdateTime(Calendar.getInstance().getTime());
		d.setUpdateUser(uname);
		d.setDescription(form.getDescription());
		d.setDocumentDate(form.getDocumentDate());
		d.setDocumentName(form.getDocumentName());
		d.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		d = crudDAO.update(d);
		
		if (StringUtil.isValid(form.getData())) {
			DocumentMedia m = new DocumentMedia();
			m.setContent(CalculatorUtil.decodeBASE64(form.getData()));
			m.setDocument(d);
			
			crudDAO.create(m);
		}
				
		return d;
		
	}

	@Transactional
	public void deleteDocument(Integer id, String uname) {
		
		Document d = crudDAO.findById(Document.class, id);
		d.setUpdateTime(Calendar.getInstance().getTime());
		d.setUpdateUser(uname);
		d.setStatus(IStatus.NOT_ACTIVE);
				
		crudDAO.update(d);
	}
	
	public List<Document> getEmployeeDocuments(String nid, String uname) {
		return documentDAO.getEmployeeDocuments(nid);
	}
	
	public String getDocumentMedia(Integer docId)
	{
		return documentDAO.getDocumentMedia(docId);
	}
	
	
	
	
}
