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
import com.bvlsh.hr.dto.TrainingDTO;
import com.bvlsh.hr.forms.TrainingForm;
import com.bvlsh.hr.forms.TrainingSx;
import com.bvlsh.hr.services.TokenService;
import com.bvlsh.hr.services.TrainingService;

@RestController
@RequestMapping("/api/training")
public class TrainingAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired TrainingService trainingService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerTraining(@RequestHeader(value="Authorization") String token, @RequestBody TrainingForm form)
	{
			String uname = tokenService.getUsername(token);
			
	       TrainingDTO dto = new Assembler().toDto(trainingService.registerTraining(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyTraining", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyTraining(@RequestHeader(value="Authorization") String token, @RequestBody TrainingForm form)
	{
			String uname = tokenService.getUsername(token);
			
			TrainingDTO dto = new Assembler().toDto(trainingService.modifyTraining(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/deleteTraining/{trainingId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteTraining(@RequestHeader(value="Authorization") String token, @PathVariable Integer trainingId)
	{
		String uname = tokenService.getUsername(token);
				
	    trainingService.deleteTraining(trainingId, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchTrainings", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchTrainings(@RequestHeader(value="Authorization") String token,@RequestBody TrainingSx sx)
	{
		String uname = tokenService.getUsername(token);
				
		List<TrainingDTO> list = new Assembler().trainingListToDto(trainingService.searchTrainings(sx, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

}
