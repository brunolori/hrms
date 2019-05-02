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
import com.bvlsh.hr.dto.EducationDTO;
import com.bvlsh.hr.forms.EducationForm;
import com.bvlsh.hr.forms.EducationSx;
import com.bvlsh.hr.services.EducationService;
import com.bvlsh.hr.services.TokenService;


@RestController
@RequestMapping("/api/education")
public class EducationAPI {
	
		
		@Autowired TokenService  tokenService;
		@Autowired EducationService educationService;
		
		
		@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
		public ResponseEntity<?> registerEducation(@RequestHeader(value="Authorization") String token, @RequestBody EducationForm form)
		{
				String uname = tokenService.getUsername(token);
				
				EducationDTO dto = new Assembler().toDto(educationService.registerEducation(form, uname));
				
				return new ResponseEntity<>(dto,HttpStatus.OK);		
		}
		
		
		@RequestMapping(value="/modifyEducation", method=RequestMethod.POST, produces={"application/json"})
		public ResponseEntity<?> modifyEducation(@RequestHeader(value="Authorization") String token, @RequestBody EducationForm form)
		{
				String uname = tokenService.getUsername(token);
				
				EducationDTO dto = new Assembler().toDto(educationService.modifyEducation(form, uname));
				
				return new ResponseEntity<>(dto,HttpStatus.OK);		
		}
		
		
		@RequestMapping(value="/deleteEducation/{educationId}", method=RequestMethod.GET, produces={"application/json"})
		public ResponseEntity<?> deleteEducation(@RequestHeader(value="Authorization") String token, @PathVariable Integer educationId)
		{
			String uname = tokenService.getUsername(token);
					
		    educationService.deleteEducation(educationId, uname);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		
		
		@RequestMapping(value="/searchEducations", method=RequestMethod.POST, produces={"application/json"})
		public ResponseEntity<?> searchEducations(@RequestHeader(value="Authorization") String token,@RequestBody EducationSx sx)
		{
			String uname = tokenService.getUsername(token);
					
			List<EducationDTO> list = new Assembler().educationListToDto(educationService.searchEducations(sx, uname));
			
			if(list == null || list.isEmpty())
			{
				return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(list,HttpStatus.OK);
			
		}

		@RequestMapping(value="/getEmployeeEducations/{nid}", method=RequestMethod.GET, produces={"application/json"})
		public ResponseEntity<?> getEmployeeEducations(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
		{
			String uname = tokenService.getUsername(token);
					
			List<EducationDTO> list = new Assembler().educationListToDto(educationService.getEmployeeEducations(nid, uname));
			
			if(list == null || list.isEmpty())
			{
				return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(list,HttpStatus.OK);
			
		}
		
		
		
}
