package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;

import com.bvlsh.hr.ui.dto.BankDTO;
import com.bvlsh.hr.ui.dto.ContactTypeDTO;
import com.bvlsh.hr.ui.dto.DepartmentCategoryDTO;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.dto.EducationTypeDTO;
import com.bvlsh.hr.ui.dto.ForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.GradeDTO;
import com.bvlsh.hr.ui.dto.InstitutionDTO;
import com.bvlsh.hr.ui.dto.ValidationTypeDTO;
import com.bvlsh.hr.ui.dto.PaymentCategoryDTO;
import com.bvlsh.hr.ui.dto.PositionDTO;
import com.bvlsh.hr.ui.dto.ProvisionTypeDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
import com.bvlsh.hr.ui.dto.StudyFieldDTO;
import com.bvlsh.hr.ui.dto.TrainingTypeDTO;
import com.bvlsh.hr.ui.services.DepartmentService;
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
	List<DepartmentCategoryDTO> departmentCategories;
	List<GradeDTO> grades;
	
	
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
		this.departmentCategories = new HelperService().loadDepartmentCategories();
		this.states = new HelperService().loadStates();
		this.grades = new HelperService().loadGrades();
		
	}
	
	public List<DepartmentDTO> listDepartments()
	{
		return new DepartmentService().getDepartments();
	}
	
	public List<DepartmentPositionDTO> listDepartmentPositions(Integer deptId)
	{
		if(deptId == null) return null;
		return new DepartmentService().getDepartmentPositions(deptId);
	}
	
	public List<StateDTO> filterState(String query)
	{
		Stream<StateDTO> s = states.stream()
				.filter(
						st -> st.getName().toUpperCase()
						.startsWith(query.trim().toUpperCase()));
		return s.collect(Collectors.toList());
	}
	
	

}	
