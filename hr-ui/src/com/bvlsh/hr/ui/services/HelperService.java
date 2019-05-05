package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.HelperClient;
import com.bvlsh.hr.ui.dto.BankDTO;
import com.bvlsh.hr.ui.dto.ContactTypeDTO;
import com.bvlsh.hr.ui.dto.DepartmentCategoryDTO;
import com.bvlsh.hr.ui.dto.EducationTypeDTO;
import com.bvlsh.hr.ui.dto.ForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.GradeDTO;
import com.bvlsh.hr.ui.dto.InstitutionDTO;
import com.bvlsh.hr.ui.dto.JobEndingReasonDTO;
import com.bvlsh.hr.ui.dto.PaymentCategoryDTO;
import com.bvlsh.hr.ui.dto.PositionDTO;
import com.bvlsh.hr.ui.dto.ProvisionTypeDTO;
import com.bvlsh.hr.ui.dto.RoleDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
import com.bvlsh.hr.ui.dto.StudyFieldDTO;
import com.bvlsh.hr.ui.dto.TrainingTypeDTO;
import com.bvlsh.hr.ui.dto.ValidationTypeDTO;

public class HelperService {

	public List<ProvisionTypeDTO> loadProvisionTypes() {
		return new HelperClient().loadProvisionTypes();
	}

	public List<JobEndingReasonDTO> loadJobEndingReasons()
	{
		return new HelperClient().loadJobEndingReasons();
	}
	
	public List<TrainingTypeDTO> loadTrainingTypes() {
		return new HelperClient().loadTrainingTypes();
	}

	public List<ValidationTypeDTO> loadValidationTypes() {
		return new HelperClient().loadValidationTypes();
	}

	public List<ForeignLanguageDTO> loadForeignLanguages() {
		return new HelperClient().loadForeignLanguages();
	}

	public List<StudyFieldDTO> loadStudyFields() {
		return new HelperClient().loadStudyFields();
	}

	public List<EducationTypeDTO> loadEducationTypes() {
		return new HelperClient().loadEducationTypes();
	}

	public List<InstitutionDTO> loadInstitutions() {
		return new HelperClient().loadInstitutions();
	}

	public List<ContactTypeDTO> loadContactTypes() {
		return new HelperClient().loadContactTypes();
	}

	public List<PositionDTO> loadPositions() {
		return new HelperClient().loadPositions();
	}

	public List<BankDTO> loadBanks() {
		return new HelperClient().loadBanks();
	}

	public List<PaymentCategoryDTO> loadPaymentCategories() {
		return new HelperClient().loadPaymentCategories();
	}

	public List<DepartmentCategoryDTO> loadDepartmentCategories() {
		return new HelperClient().loadDepartmentCategories();
	}

	public List<StateDTO> loadStates() {
		return new HelperClient().loadStates();
	}

	public List<RoleDTO> loadRoles() {
		return new HelperClient().loadRoles();
	}

	public List<GradeDTO> loadGrades() {
		return new HelperClient().loadGrades();
	}

}