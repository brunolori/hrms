package com.bvlsh.hr.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.BankDTO;
import com.bvlsh.hr.dto.ContactTypeDTO;
import com.bvlsh.hr.dto.DepartmentCategoryDTO;
import com.bvlsh.hr.dto.EducationTypeDTO;
import com.bvlsh.hr.dto.ForeignLanguageDTO;
import com.bvlsh.hr.dto.InstitutionDTO;
import com.bvlsh.hr.dto.PaymentCategoryDTO;
import com.bvlsh.hr.dto.PositionDTO;
import com.bvlsh.hr.dto.ProvisionTypeDTO;
import com.bvlsh.hr.dto.RoleDTO;
import com.bvlsh.hr.dto.StateDTO;
import com.bvlsh.hr.dto.StudyFieldDTO;
import com.bvlsh.hr.dto.TrainingTypeDTO;
import com.bvlsh.hr.dto.ValidationTypeDTO;
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
	
	@RequestMapping(value="/list/validationTypes", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadValidationTypes()
	{
				
		List<ValidationTypeDTO> list = new Assembler().validationTypeListToDto(helperService.loadValidationTypes());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/foreignLanguages", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadForeignLanguages()
	{
				
		List<ForeignLanguageDTO> list = new Assembler().foreignLanguageListToDto(helperService.loadForeignLanguages());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/studyFields", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadStudyFields()
	{
				
		List<StudyFieldDTO> list = new Assembler().studyFieldListToDto(helperService.loadStudyFields());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/educationTypes", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadEducationTypes()
	{
				
		List<EducationTypeDTO> list = new Assembler().educationTypeListToDto(helperService.loadEducationTypes());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/institutions", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadInstitutions()
	{
				
		List<InstitutionDTO> list = new Assembler().institutionListToDto(helperService.loadInstitutions());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/contactTypes", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadContactTypes()
	{
				
		List<ContactTypeDTO> list = new Assembler().contactTypeListToDto(helperService.loadContactTypes());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/positions", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadPositions()
	{
				
		List<PositionDTO> list = new Assembler().positionListToDto(helperService.loadPositions());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/banks", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadBanks()
	{
				
		List<BankDTO> list = new Assembler().bankListToDto(helperService.loadBanks());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/paymentCategories", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadPaymentCategories()
	{
				
		List<PaymentCategoryDTO> list = new Assembler().paymentCategoryListToDto(helperService.loadPaymentCategories());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}	
	
	@RequestMapping(value="/list/departmentCategories", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadDepartmentCategories()
	{
				
		List<DepartmentCategoryDTO> list = new Assembler().departmentCategoryListToDto(helperService.loadDepartmentCategories());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/states", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadStates()
	{
				
		List<StateDTO> list = new Assembler().stateListToDto(helperService.loadStates());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/list/roles", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> loadRoles()
	{
				
		List<RoleDTO> list = new Assembler().roleListToDto(helperService.loadRoles());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}

}
