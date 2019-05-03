package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.ui.dto.EducationDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.EmployeeGradeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.dto.JobValidationDTO;
import com.bvlsh.hr.ui.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.ui.forms.EducationForm;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.forms.ForeignLanguageForm;
import com.bvlsh.hr.ui.forms.GradeForm;
import com.bvlsh.hr.ui.forms.JobValidationForm;
import com.bvlsh.hr.ui.services.AdministrativeProvisionService;
import com.bvlsh.hr.ui.services.EducationService;
import com.bvlsh.hr.ui.services.EmployeeService;
import com.bvlsh.hr.ui.services.ForeignLanguageService;
import com.bvlsh.hr.ui.services.GradeService;
import com.bvlsh.hr.ui.services.JobValidationService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpEmployeeViewBean implements Serializable {
	
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	Integer departmentId;
	
	EmployeeDTO employee;
	List<EmployeeHistoryDTO> employment;
	EmployeeForm employeeForm;
	List<AdministrativeProvisionDTO> provisions;
	AdministrativeProvisionForm provisionForm;
	List<EmployeeGradeDTO> grades;
	GradeForm gradeForm;
	List<JobValidationDTO> validations;
	JobValidationForm validationForm;
	List<EducationDTO> educations;
	EducationForm educationForm;
	List<EmployeeForeignLanguageDTO> languages;
	ForeignLanguageForm languageForm;
	
	
	
	
	
	
	public void init() {
				
		String nid = nav.getParam("nid");
		this.employee = new EmployeeService().getEmployeeByNid(nid);
		initProvisions();
		initEmployment();
		initGrades();
		initValidations();
		initEducations();
		initLanguages();
	}
	
	
	public void initProvisions()
	{
		this.provisionForm = new AdministrativeProvisionForm();
		this.provisions = new AdministrativeProvisionService().getEmployeeProvisions(this.employee.getNid());
	}
	
	public void saveProvision()
	{
		try {
			this.provisionForm.setPersonNid(this.employee.getNid());
			new AdministrativeProvisionService().registerAdministrativeProvision(this.provisionForm);
			initProvisions();
			Messages.throwFacesMessage("Masa administrative u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyProvision()
	{
		try {
			this.provisionForm.setPersonNid(this.employee.getNid());
			new AdministrativeProvisionService().modifyProvision(this.provisionForm);
			initProvisions();
			Messages.throwFacesMessage("Masa administrative u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteProvision(AdministrativeProvisionDTO dto)
	{
		try {
			new AdministrativeProvisionService().deleteProvision(dto.getId());
			initProvisions();
			Messages.throwFacesMessage("Masa administrative u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onProvisionSelect(AdministrativeProvisionDTO dto)
	{
		this.provisionForm.setId(dto.getId());
		this.provisionForm.setActDate(dto.getActDate());
		this.provisionForm.setActive(dto.isActive());
		this.provisionForm.setActNo(dto.getActNo());
		this.provisionForm.setPersonNid(dto.getEmployee().getNid());
		this.provisionForm.setProvisionTypeId(dto.getProvisionType().getId());
		this.provisionForm.setReason(dto.getReason());
		this.provisionForm.setStartDate(dto.getStartDate());
		this.provisionForm.setValidityInMonths(dto.getValidityInMonths());
	}
	
	public void initEmployment()
	{
		this.employeeForm = new EmployeeForm();
		this.departmentId = null;
		this.employment = new EmployeeService().getEmployeeHistory(this.employee.getNid());
	}
	
	
	public void saveEmployment()
	{
		try {
			this.employeeForm.setNid(this.employee.getNid());
			new EmployeeService().changeEmployeePosition(employeeForm);
			this.employee = new EmployeeService().getEmployeeByNid(this.employee.getNid());
			initEmployment();
			Messages.throwFacesMessage("Emërimi u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyEmployment()
	{
		try {
			this.employeeForm.setNid(this.employee.getNid());
			new EmployeeService().updateEmployment(employeeForm);
			this.employee = new EmployeeService().getEmployeeByNid(this.employee.getNid());
			initEmployment();
			Messages.throwFacesMessage("Emërimi u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	
	public void onEmployeeSelect(EmployeeHistoryDTO dto)
	{
		this.departmentId = dto.getDepartmentPosition().getDepartment().getId();
		this.employeeForm.setEmploymentId(dto.getId());
		this.employeeForm.setNid(this.employee.getNid());
		this.employeeForm.setDepartmentPositionId(dto.getDepartmentPosition().getId());
		this.employeeForm.setEndDate(dto.getEndDate());
		this.employeeForm.setStartDate(dto.getStartDate());
		this.employeeForm.setPaymentCategoryId(dto.getPaymentCategory().getId());

	}
	
	
	public void initGrades()
	{
		this.gradeForm = new GradeForm();
		this.grades = new GradeService().getEmployeeGrades(this.employee.getNid());
	}
	
	public void saveGrade()
	{
		try {
			this.gradeForm.setPersonNid(this.employee.getNid());
			new GradeService().registerGrade(this.gradeForm);
			initGrades();
			Messages.throwFacesMessage("Grada u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyGrade()
	{
		try {
			this.gradeForm.setPersonNid(this.employee.getNid());
			new GradeService().modifyGrade(this.gradeForm);
			initGrades();
			Messages.throwFacesMessage("Grada u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteGrade(EmployeeGradeDTO dto)
	{
		try {
			new GradeService().deleteGrade(dto.getId());
			initGrades();
			Messages.throwFacesMessage("Grada u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onGradeSelect(EmployeeGradeDTO dto)
	{
		this.gradeForm.setId(dto.getId());
		this.gradeForm.setPersonNid(dto.getEmployee().getNid());
		this.gradeForm.setEndDate(dto.getEndDate());
		this.gradeForm.setStartDate(dto.getStartDate());
		this.gradeForm.setGradeId(dto.getGrade().getId());
	}
	
	
	public void initValidations()
	{
		this.validationForm = new JobValidationForm();
		this.validations = new JobValidationService().getEmployeeValidations(this.employee.getNid());
	}
	
	public void saveValidation()
	{
		try {
			this.validationForm.setPersonNid(this.employee.getNid());
			new JobValidationService().registerJobValidation(this.validationForm);
			initValidations();
			Messages.throwFacesMessage("Vlerësimi u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyValidation()
	{
		try {
			this.validationForm.setPersonNid(this.employee.getNid());
			new JobValidationService().modifyJobValidation(this.validationForm);
			initValidations();
			Messages.throwFacesMessage("Vlerësimi u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteValidation(JobValidationDTO dto)
	{
		try {
			new JobValidationService().deleteJobValidation(dto.getId());
			initValidations();
			Messages.throwFacesMessage("Vlerësimi u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onValidationSelect(JobValidationDTO dto)
	{
		this.validationForm.setId(dto.getId());
		this.validationForm.setPersonNid(dto.getEmployee().getNid());
		this.validationForm.setStartDate(dto.getStartDate());
		this.validationForm.setEndDate(dto.getEndDate());
		this.validationForm.setSignedBy(dto.getSignedBy());
		this.validationForm.setValidationDate(dto.getValidationDate());
		this.validationForm.setValidationTypeId(dto.getValidationType().getId());
	}
	
	
	public void initEducations()
	{
		this.educationForm = new EducationForm();
		this.educations = new EducationService().getEmployeeEducations(this.employee.getNid());
	}
	
	public void saveEducation()
	{
		try {
			this.educationForm.setPersonNid(this.employee.getNid());
			new EducationService().registerEducation(this.educationForm);
			initEducations();
			Messages.throwFacesMessage("Edukimi u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyEducation()
	{
		try {
			this.educationForm.setPersonNid(this.employee.getNid());
			new EducationService().modifyEducation(this.educationForm);
			initEducations();
			Messages.throwFacesMessage("Edukimi u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteEducation(EducationDTO dto)
	{
		try {
			new EducationService().deleteEducation(dto.getId());
			initEducations();
			Messages.throwFacesMessage("Edukimi u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onEducationSelect(EducationDTO dto)
	{
		this.educationForm.setId(dto.getId());
		this.educationForm.setDescription(dto.getDescription());
		this.educationForm.setEducationTypeId(dto.getEducationType().getId());
		this.educationForm.setPersonNid(dto.getEmployee().getNid());
		this.educationForm.setIssueDate(dto.getIssueDate());
		
		if(dto.getInstitution() != null)
		{
		   this.educationForm.setInstitutionId(dto.getInstitution().getId());
		}
		
		if(dto.getStudyField() != null)
		{
		   this.educationForm.setStudyFieldId(dto.getStudyField().getId());
		}
		
	}
	
	public void initLanguages()
	{
		this.languageForm = new ForeignLanguageForm();
		this.languages = new ForeignLanguageService().getEmployeeLanguages(this.employee.getNid());
	}
	
	public void saveLanguage()
	{
		try {
			this.languageForm.setPersonNid(this.employee.getNid());
			new ForeignLanguageService().registerForeignLanguage(this.languageForm);
			initLanguages();
			Messages.throwFacesMessage("Gjuha e huaj u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyLanguage()
	{
		try {
			this.languageForm.setPersonNid(this.employee.getNid());
			new ForeignLanguageService().modifyForeignLanguage(this.languageForm);
			initLanguages();
			Messages.throwFacesMessage("Gjuha e huaj u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteLanguage(EmployeeForeignLanguageDTO dto)
	{
		try {
			new ForeignLanguageService().deleteForeignLanguage(dto.getId());
			initLanguages();
			Messages.throwFacesMessage("Gjuha e huaj u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onLanguageSelect(EmployeeForeignLanguageDTO dto)
	{
		this.languageForm.setId(dto.getId());
		this.languageForm.setDescription(dto.getDescription());
		this.languageForm.setForeignLanguageId(dto.getForeignLanguage().getId());
		this.languageForm.setLevel(dto.getLevel());
		this.languageForm.setPersonNid(dto.getEmployee().getNid());
	}
	
	
	
	

}
