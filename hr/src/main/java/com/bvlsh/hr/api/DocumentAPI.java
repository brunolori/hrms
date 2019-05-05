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
import com.bvlsh.hr.dto.DocumentDTO;
import com.bvlsh.hr.forms.DocumentForm;
import com.bvlsh.hr.services.DocumentService;
import com.bvlsh.hr.services.TokenService;



@RestController
@RequestMapping("/api/document")
public class DocumentAPI {

	
	@Autowired TokenService  tokenService;
	@Autowired DocumentService documentService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerDocument(@RequestHeader(value="Authorization") String token, @RequestBody DocumentForm form)
	{
			String uname = tokenService.getUsername(token);
			
			DocumentDTO dto = new Assembler().toDto(documentService.registerDocument(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyDocument(@RequestHeader(value="Authorization") String token, @RequestBody DocumentForm form)
	{
			String uname = tokenService.getUsername(token);
			
			DocumentDTO dto = new Assembler().toDto(documentService.modifyDocument(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteDocument(@RequestHeader(value="Authorization") String token, @PathVariable Integer id)
	{
		String uname = tokenService.getUsername(token);
				
	    documentService.deleteDocument(id, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@RequestMapping(value="/getEmployeeDocuments/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeDocuments(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<DocumentDTO> list = new Assembler().documentListToDto(documentService.getEmployeeDocuments(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/media/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getDocumentMedia(@PathVariable Integer id)
	{
		//String uname = tokenService.getUsername(token);
				
	    String media = documentService.getDocumentMedia(id);
		
		return new ResponseEntity<>(media, HttpStatus.OK);
		
	}
	
	
	
	
}
