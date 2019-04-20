package com.bvlsh.hr.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.entities.Employee;

@Repository
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;

	public Employee findEmployeeByNid(String nid) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
