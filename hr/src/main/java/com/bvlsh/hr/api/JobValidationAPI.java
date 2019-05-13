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
import com.bvlsh.hr.dto.JobValidationDTO;
import com.bvlsh.hr.forms.JobValidationForm;
import com.bvlsh.hr.forms.JobValidationSx;
import com.bvlsh.hr.services.JobValidationService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/jobValidation")
public class JobValidationAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired JobValidationService  jobValidationService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerJobValidation(@RequestHeader(value="Authorization") String token, @RequestBody JobValidationForm form)
	{
			String uname = tokenService.getUsername(token);
			
			JobValidationDTO dto = new Assembler().toDto(jobValidationService.registerJobValidation(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyJobValidation", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyJobValidation(@RequestHeader(value="Authorization") String token, @RequestBody JobValidationForm form)
	{
			String uname = tokenService.getUsername(token);
			
			JobValidationDTO dto = new Assembler().toDto(jobValidationService.modifyJobValidation(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/deleteJobValidation/{jobValidationId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteJobValidation(@RequestHeader(value="Authorization") String token, @PathVariable Integer jobValidationId)
	{
		String uname = tokenService.getUsername(token);
				
		jobValidationService.deleteJobValidation(jobValidationId, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchJobValidations", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchJobValidations(@RequestHeader(value="Authorization") String token,@RequestBody JobValidationSx sx)
	{
		String uname = tokenService.getUsername(token);
		List<Integer> deptIds = tokenService.getDeptIds(token);
				
		List<JobValidationDTO> list = new Assembler().jobValidationListToDto(jobValidationService.searchJobValidations(sx, deptIds, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getEmployeeValidations/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeValidations(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<JobValidationDTO> list = new Assembler().jobValidationListToDto(jobValidationService.getEmployeeValidations(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	


}
