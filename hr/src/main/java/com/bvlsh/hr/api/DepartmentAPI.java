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
import com.bvlsh.hr.dto.DepartmentDTO;
import com.bvlsh.hr.dto.DepartmentPositionDTO;
import com.bvlsh.hr.forms.DepartmentForm;
import com.bvlsh.hr.forms.DepartmentPositionForm;
import com.bvlsh.hr.services.DepartmentService;
import com.bvlsh.hr.services.TokenService;


@RestController
@RequestMapping("/api/department")
public class DepartmentAPI {
	
	
	@Autowired DepartmentService departmentService;
	@Autowired TokenService tokenService;
	
	
	@RequestMapping(value="/getRootDepartment", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getRootDepartment(@RequestHeader(value="Authorization") String token)
	{
		String uname = tokenService.getUsername(token);
		
		DepartmentDTO d = new Assembler().toDto(departmentService.getRootDepartment(uname));
				
		return new ResponseEntity<>(d,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listDepartments(@RequestHeader(value="Authorization") String token, @PathVariable Integer id)
	{
		String uname = tokenService.getUsername(token);
		
		DepartmentDTO d = new Assembler().toDto(departmentService.getDepartmentById(id,uname));
		
		return new ResponseEntity<>(d, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listDepartments(@RequestHeader(value="Authorization") String token)
	{
		String uname = tokenService.getUsername(token);
		List<Integer> deptIds = tokenService.getDeptIds(token);
		
		List<DepartmentDTO> list = new Assembler().departmentListToDto(departmentService.listDepartments(uname, deptIds));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getChildDepartments/{deptId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getChildDepartments(@RequestHeader(value="Authorization") String token, @PathVariable Integer deptId)
	{
		tokenService.getUsername(token);
		
		List<DepartmentDTO> list = new Assembler().departmentListToDto(departmentService.getChildDepartments(deptId));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getDepartmentPositions/{deptId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getDepartmentPositions(@RequestHeader(value="Authorization") String token, @PathVariable Integer deptId)
	{
		tokenService.getUsername(token);
		
		List<DepartmentPositionDTO> list = new Assembler().departmentPositionListToDto(departmentService.getDepartmentPositions(deptId));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	
	
	@RequestMapping(value="/registerDepartment", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerDepartment(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentDTO dto = new Assembler().toDto(departmentService.registerDepartment(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/modifyDepartment", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyDepartment(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentDTO dto = new Assembler().toDto(departmentService.modifyDepartment(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/registerDepartmentPosition", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerDepartmentPosition(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentPositionForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentPositionDTO dto = new Assembler().toDto(departmentService.registerDepartmentPosition(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/modifyDepartmentPosition", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyDepartmentPosition(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentPositionForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentPositionDTO dto = new Assembler().toDto(departmentService.modifyDepartmentPosition(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	
	
	

}