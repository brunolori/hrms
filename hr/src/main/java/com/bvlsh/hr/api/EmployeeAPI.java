package com.bvlsh.hr.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.EmployeeDTO;
import com.bvlsh.hr.forms.EmployeeForm;
import com.bvlsh.hr.services.EmployeeService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeAPI {
	
	
	@Autowired TokenService  tokenService;
	@Autowired EmployeeService employeeService;
	
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerEmployee(@RequestHeader(value="Authorization") String token, @RequestBody EmployeeForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeDTO dto = new Assembler().toDto(employeeService.registerEmployee(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}

}
