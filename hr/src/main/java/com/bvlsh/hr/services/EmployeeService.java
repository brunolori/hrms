package com.bvlsh.hr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.EmployeeDAO;

@Service
public class EmployeeService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired EmployeeDAO employeeDAO;
	
	

}
