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
import com.bvlsh.hr.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;
import com.bvlsh.hr.services.AdministrativeProvisionService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/provision")
public class AdministrativeProvisionAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired AdministrativeProvisionService provisionService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerEmployee(@RequestHeader(value="Authorization") String token, @RequestBody AdministrativeProvisionForm form)
	{
			String uname = tokenService.getUsername(token);
			
			AdministrativeProvisionDTO dto = new Assembler().toDto(provisionService.registerProvision(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyProvision", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyProvision(@RequestHeader(value="Authorization") String token, @RequestBody AdministrativeProvisionForm form)
	{
			String uname = tokenService.getUsername(token);
			
			AdministrativeProvisionDTO dto = new Assembler().toDto(provisionService.modifyProvision(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/delete/{provisionId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteProvision(@RequestHeader(value="Authorization") String token, @PathVariable Integer provisionId)
	{
		String uname = tokenService.getUsername(token);
				
	    provisionService.deleteProvision(provisionId, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchProvisions", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchProvisions(@RequestHeader(value="Authorization") String token,@RequestBody AdministrativeProvisionSx sx)
	{
		String uname = tokenService.getUsername(token);
		List<Integer> deptIds = tokenService.getDeptIds(token);
		
		List<AdministrativeProvisionDTO> list = new Assembler().administrativePositionListToDto(provisionService.searchProvisions(sx, deptIds, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getEmployeeProvisions/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeProvisions(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<AdministrativeProvisionDTO> list = new Assembler().administrativePositionListToDto(provisionService.getEmployeeProvisions(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	

}
