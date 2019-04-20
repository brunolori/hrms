package com.bvlsh.hr.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;
	


}
