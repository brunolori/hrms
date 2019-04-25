package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.bvlsh.hr.ui.dto.BankDTO;
import com.bvlsh.hr.ui.dto.ContactTypeDTO;
import com.bvlsh.hr.ui.dto.EducationTypeDTO;
import com.bvlsh.hr.ui.dto.ForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.InstitutionDTO;
import com.bvlsh.hr.ui.dto.ValidationTypeDTO;
import com.bvlsh.hr.ui.dto.PaymentCategoryDTO;
import com.bvlsh.hr.ui.dto.PositionDTO;
import com.bvlsh.hr.ui.dto.ProvisionTypeDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
import com.bvlsh.hr.ui.dto.StudyFieldDTO;
import com.bvlsh.hr.ui.dto.TrainingTypeDTO;
import com.bvlsh.hr.ui.services.HelperService;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;


@ManagedBean
@ApplicationScoped
@Getter @Setter
public class CacheBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	List<PositionDTO> positions;
	List<PaymentCategoryDTO> paymentCategories;
	List<InstitutionDTO> institutions;
	List<EducationTypeDTO> educationTypes;
	List<StudyFieldDTO> studyFields;
	List<BankDTO> banks;
	List<ContactTypeDTO> contactTypes;
	List<ProvisionTypeDTO> provisionTypes;
	List<ForeignLanguageDTO> foreignLanguages;
	List<TrainingTypeDTO> trainingTypes;
	List<ValidationTypeDTO> validationTypes;
	List<StateDTO> states;
	
	
	@PostConstruct
	public void load()
	{
		this.positions = new HelperService().loadPositions();
		this.paymentCategories = new HelperService().loadPaymentCategories();
		this.institutions = new HelperService().loadInstitutions();
		this.educationTypes = new HelperService().loadEducationTypes();
		this.studyFields = new HelperService().loadStudyFields();
		this.banks = new HelperService().loadBanks();
		this.contactTypes = new HelperService().loadContactTypes();
		this.provisionTypes = new HelperService().loadProvisionTypes();
		this.foreignLanguages = new HelperService().loadForeignLanguages();
		this.trainingTypes = new HelperService().loadTrainingTypes();
		this.validationTypes = new HelperService().loadValidationTypes();
		
	}
	
	
	
	public List<StateDTO> filterState(String query)
	{
		return null;
	}
	
	
	
	/*
	public List<MunicipalityDTO> loadMunicipalities(Integer regionId, boolean fill)
	{
		if(regionId == null )
		{
			return fill?this.municipalities:null;
		}
		
		List<MunicipalityDTO> list = new ArrayList<>();
		for(MunicipalityDTO m : municipalities)
		{
			if(m.getRegion().getId() == regionId)
			{
				list.add(m);
			}
		}
		
		return list;
		
	}
		*/
	

}	
