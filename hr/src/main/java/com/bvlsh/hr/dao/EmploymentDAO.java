package com.bvlsh.hr.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EmploymentDAO {
	
	@PersistenceContext
	EntityManager em;
	


}
