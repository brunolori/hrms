package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Document;

@Repository
@SuppressWarnings("unchecked")
public class DocumentDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	
	
	
	public List<Document> getEmployeeDocuments(String nid) {
		return em.createQuery("FROM Document ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}
	
	
}
