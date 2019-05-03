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
import com.bvlsh.hr.dto.ContactDTO;
import com.bvlsh.hr.forms.ContactForm;
import com.bvlsh.hr.services.ContactService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/contact")
public class ContactAPI {

	
	@Autowired TokenService  tokenService;
	@Autowired ContactService contactService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerContact(@RequestHeader(value="Authorization") String token, @RequestBody ContactForm form)
	{
			String uname = tokenService.getUsername(token);
			
			ContactDTO dto = new Assembler().toDto(contactService.registerContact(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyContact(@RequestHeader(value="Authorization") String token, @RequestBody ContactForm form)
	{
			String uname = tokenService.getUsername(token);
			
			ContactDTO dto = new Assembler().toDto(contactService.modifyContact(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteContact(@RequestHeader(value="Authorization") String token, @PathVariable Integer id)
	{
		String uname = tokenService.getUsername(token);
				
	    contactService.deleteContact(id, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@RequestMapping(value="/getEmployeeContacts/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeContacts(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<ContactDTO> list = new Assembler().contactListToDto(contactService.getEmployeeContacts(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
}
