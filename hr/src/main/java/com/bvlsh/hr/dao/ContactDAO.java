package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Contact;

@Repository
@SuppressWarnings("unchecked")
public class ContactDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	
	
	public List<Contact> getEmployeeContacts(String nid) {
		return em.createQuery("FROM Contact ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}
	
}
