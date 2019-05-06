package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.BankAccount;
import com.bvlsh.hr.forms.BankAccountSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class BankDAO {

	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("rawtypes")
	public List<BankAccount> searchBankAccounts(BankAccountSx sx)
	{
		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM BankAccount ba WHERE ba.status=:st ";

		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND ba.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND ba.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(ba.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(ba.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND ba.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND ba.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if (StringUtil.isValid(sx.getIban())) {
			sql += "AND ba.iban=:iban ";
			params.put("iban", sx.getIban().toUpperCase().replace(" ", ""));
		}
		
		
		if (sx.getBankId() != null) {
			sql += "AND ba.bank.id=:pt_id ";
			params.put("pt_id", sx.getBankId());
		}

		

		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
	
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry) it.next();
			q.setParameter((String) pair.getKey(), pair.getValue());
			it.remove();
		}

		if (sx.getFirstResult() != null) {
			q.setFirstResult(sx.getFirstResult());
		}

		if (sx.getMaxResult() != null) {
			q.setMaxResults(sx.getMaxResult());
		}

		return q.getResultList();
	}
	
	
	public List<BankAccount> getEmployeeBankAccounts(String nid) {
		return em.createQuery("FROM BankAccount ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}
	
	
}
