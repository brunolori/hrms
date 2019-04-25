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
import com.bvlsh.hr.dto.EmployeeGradeDTO;
import com.bvlsh.hr.forms.GradeForm;
import com.bvlsh.hr.forms.GradeSx;
import com.bvlsh.hr.services.GradeService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/grade")
public class GradeAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired GradeService gradeService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerGrade(@RequestHeader(value="Authorization") String token, @RequestBody GradeForm form)
	{
			String uname = tokenService.getUsername(token);
			
	       EmployeeGradeDTO dto = new Assembler().toDto(gradeService.registerGrade(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyGrade", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyGrade(@RequestHeader(value="Authorization") String token, @RequestBody GradeForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeGradeDTO dto = new Assembler().toDto(gradeService.modifyGrade(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/deleteGrade/{gradeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteGrade(@RequestHeader(value="Authorization") String token, @PathVariable Integer gradeId)
	{
		String uname = tokenService.getUsername(token);
				
	    gradeService.deleteGrade(gradeId, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchGrades", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchTrainings(@RequestHeader(value="Authorization") String token,@RequestBody GradeSx sx)
	{
		String uname = tokenService.getUsername(token);
				
		List<EmployeeGradeDTO> list = new Assembler().employeeGradeListToDto(gradeService.searchGrades(sx, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

	

}
