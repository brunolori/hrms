package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.Bank;
import com.bvlsh.hr.entities.ContactType;
import com.bvlsh.hr.entities.DepartmentCategory;
import com.bvlsh.hr.entities.EducationType;
import com.bvlsh.hr.entities.ForeignLanguage;
import com.bvlsh.hr.entities.Grade;
import com.bvlsh.hr.entities.Institution;
import com.bvlsh.hr.entities.PaymentCategory;
import com.bvlsh.hr.entities.Position;
import com.bvlsh.hr.entities.ProvisionType;
import com.bvlsh.hr.entities.Role;
import com.bvlsh.hr.entities.State;
import com.bvlsh.hr.entities.StudyField;
import com.bvlsh.hr.entities.TrainingType;
import com.bvlsh.hr.entities.ValidationType;

@Service
public class HelperService {
	
	
	@Autowired CrudDAO crudDAO;
	
	public List<ProvisionType> loadProvisionTypes() {

		return crudDAO.loadProvisionTypes();
	}

	
	public List<TrainingType> loadTrainingTypes(){
		
		return crudDAO.loadTrainingTypes();
	}
	
	public List<ValidationType> loadValidationTypes(){
		
		return crudDAO.loadValidationTypes();
	}
	
	public List<ForeignLanguage> loadForeignLanguages(){
		
		return crudDAO.loadForeignLanguages();
	}
	
	public List<StudyField> loadStudyFields(){
		
		return crudDAO.loadStudyFields();
	}
	
	public List<EducationType> loadEducationTypes(){
		
		return crudDAO.loadEducationTypes();
	}

	
	public List<Institution> loadInstitutions(){
		
		return crudDAO.loadInstitutions();
	}
	
    public List<ContactType> loadContactTypes(){
		
		return crudDAO.loadContactTypes();
	}
    
    public List<Position> loadPositions(){
		
		return crudDAO.loadPositions();
	}
    
   public List<Bank> loadBanks(){
		
		return crudDAO.loadBanks();
	}
   
   
   public List<PaymentCategory> loadPaymentCategories(){
		
		return crudDAO.loadPaymentCategories();
	}
   
   
   public List<DepartmentCategory> loadDepartmentCategories(){
	   
	   return crudDAO.loadDepartmentCategories();
   }
   
   public List<State> loadStates(){
	   
	   return crudDAO.loadStates();
   }
   
   public List<Role> loadRoles(){
	   
	   return crudDAO.loadRoles();
   }


public List<Grade> loadGrades() {
	return crudDAO.loadGrades();
}
   
	
	

}
