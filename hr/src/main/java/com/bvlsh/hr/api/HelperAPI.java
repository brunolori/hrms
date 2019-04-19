package com.bvlsh.hr.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.ProvisionTypeDTO;
import com.bvlsh.hr.dto.TrainingTypeDTO;
import com.bvlsh.hr.services.HelperService;

@RestController
@RequestMapping("/api/helper")
public class HelperAPI {
	
   @Autowired HelperService helperService;
	
	
	@RequestMapping(value="/list/provisionTypes", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadProvisionTypes()
	{
				
		List<ProvisionTypeDTO> list = new Assembler().provisionTypeListToDto(helperService.loadProvisionTypes());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/trainingTypes", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadTrainingTypes()
	{
				
		List<TrainingTypeDTO> list = new Assembler().trainingTypeListToDto(helperService.loadTrainingTypes());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	

}
