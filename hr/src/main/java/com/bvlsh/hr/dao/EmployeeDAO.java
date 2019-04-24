package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeHistory;
import com.bvlsh.hr.forms.EmployeeSx;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;

	public Employee findEmployeeByNid(String nid) {
		
		return em.find(Employee.class, nid);
		
	}

	
	public EmployeeHistory getEmployeeLastEmployment(String nid) {
		List<EmployeeHistory> history = em.createQuery("FROM EmployeeHistory eh WHERE eh.sttus=:st AND eh.employee.nid=:nid ORDER BY eh.startDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.toUpperCase().replace(" ", ""))
				.getResultList();
		
		if(history != null && !history.isEmpty())
		{
			return history.get(0);
		}
		
		return null;
	}

	public List<Employee> searchEmployee(EmployeeSx sx) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EmployeeHistory> getEmployeeHistory(String nid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
