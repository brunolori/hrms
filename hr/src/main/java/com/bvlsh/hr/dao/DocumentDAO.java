package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Document;
import com.bvlsh.hr.utils.CalculatorUtil;

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





	public String getDocumentMedia(Integer docId) {
		List<byte[]> list = em.createQuery("SELECT ap.content FROM DocumentMedia ap WHERE ap.document.id = :id ORDER BY ap.id DESC ")
				.setParameter("id", docId)
				.getResultList();
		
		if(list != null && !list.isEmpty())
		{
			return CalculatorUtil.encodeBASE64(list.get(0));
		}
		
		return null;
		
	}
	
	
}
