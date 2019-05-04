package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.BankAccount;
import com.bvlsh.hr.forms.BankAccountSx;

@Repository
@SuppressWarnings("unchecked")
public class BankDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	public List<BankAccount> searchBankAccounts(BankAccountSx sx)
	{
		return null;
	}
	
	
	public List<BankAccount> getEmployeeBankAccounts(String nid) {
		return em.createQuery("FROM BankAccount ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}
	
	
}
