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
import com.bvlsh.hr.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.forms.ForeignLanguageForm;
import com.bvlsh.hr.forms.ForeignLanguageSx;
import com.bvlsh.hr.services.ForeignLanguageService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/foreignLanguage")
public class ForeignLanguageAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired ForeignLanguageService  foreignLanguageService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerForeignLanguage(@RequestHeader(value="Authorization") String token, @RequestBody ForeignLanguageForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeForeignLanguageDTO dto = new Assembler().toDto(foreignLanguageService.registerForeignLanguage(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyForeignLanguage", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyForeignLanguage(@RequestHeader(value="Authorization") String token, @RequestBody ForeignLanguageForm form)
	{
			String uname = tokenService.getUsername(token);
			
			EmployeeForeignLanguageDTO dto = new Assembler().toDto(foreignLanguageService.modifyForeignLanguage(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/deleteForeignLanguage/{foreignLanguageId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteForeignLanguage(@RequestHeader(value="Authorization") String token, @PathVariable Integer foreignLanguageId)
	{
		String uname = tokenService.getUsername(token);
				
		foreignLanguageService.deleteForeignLanguage(foreignLanguageId, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchForeignLanguages", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchForeignLanguages(@RequestHeader(value="Authorization") String token,@RequestBody ForeignLanguageSx sx)
	{
		String uname = tokenService.getUsername(token);
				
		List<EmployeeForeignLanguageDTO> list = new Assembler().employeeForeignLanguageListToDto(foreignLanguageService.searchForeignLanguages(sx, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

}
