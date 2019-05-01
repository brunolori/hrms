package com.bvlsh.hr.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.EmployeeDTO;
import com.bvlsh.hr.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.forms.EmployeeForm;
import com.bvlsh.hr.forms.EmployeeSx;
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
	
	@RequestMapping(value="/updateEmployment", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> updateEmployment(@RequestHeader(value="Authorization") String token, @RequestBody EmployeeForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeHistoryDTO dto = new Assembler().toDto(employeeService.updateEmployment(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/changeEmployeePosition", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> changeEmployeePosition(@RequestHeader(value="Authorization") String token, @RequestBody EmployeeForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeDTO dto = new Assembler().toDto(employeeService.changeEmployeePosition(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	

	@RequestMapping(value="/updateEmployeeGeneralities", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> updateEmployeeGeneralities(@RequestHeader(value="Authorization") String token, @RequestBody EmployeeForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeDTO dto = new Assembler().toDto(employeeService.updateEmployeeGeneralities(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/searchEmployee", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchEmployee(@RequestHeader(value="Authorization") String token,@RequestBody EmployeeSx sx)
	{
		String uname = tokenService.getUsername(token);
				
		List<EmployeeDTO> list = new Assembler().employeeListToDto(employeeService.searchEmployee(sx, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getEmployeeHistory/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeHistory(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<EmployeeHistoryDTO> list = new Assembler().employeeHistoryListToDto(employeeService.getEmployeeHistory(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeByNid(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		EmployeeDTO dto  = new Assembler().toDto(employeeService.getEmployeeByNid(nid, uname));		
		
		
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
	}
	
}
