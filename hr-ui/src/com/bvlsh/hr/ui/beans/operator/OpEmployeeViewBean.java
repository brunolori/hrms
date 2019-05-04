package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.ui.dto.BankAccountDTO;
import com.bvlsh.hr.ui.dto.ContactDTO;
import com.bvlsh.hr.ui.dto.DocumentDTO;
import com.bvlsh.hr.ui.dto.EducationDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.EmployeeGradeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.dto.JobValidationDTO;
import com.bvlsh.hr.ui.dto.TrainingDTO;
import com.bvlsh.hr.ui.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.ui.forms.BankAccountForm;
import com.bvlsh.hr.ui.forms.ContactForm;
import com.bvlsh.hr.ui.forms.DocumentForm;
import com.bvlsh.hr.ui.forms.EducationForm;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.forms.ForeignLanguageForm;
import com.bvlsh.hr.ui.forms.GradeForm;
import com.bvlsh.hr.ui.forms.JobValidationForm;
import com.bvlsh.hr.ui.forms.TrainingForm;
import com.bvlsh.hr.ui.services.AdministrativeProvisionService;
import com.bvlsh.hr.ui.services.BankService;
import com.bvlsh.hr.ui.services.ContactService;
import com.bvlsh.hr.ui.services.DocumentService;
import com.bvlsh.hr.ui.services.EducationService;
import com.bvlsh.hr.ui.services.EmployeeService;
import com.bvlsh.hr.ui.services.ForeignLanguageService;
import com.bvlsh.hr.ui.services.GradeService;
import com.bvlsh.hr.ui.services.JobValidationService;
import com.bvlsh.hr.ui.services.TrainingService;
import com.bvlsh.hr.ui.utils.CalculatorUtil;
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
	List<ContactDTO> contacts;
	ContactForm contactForm;
	List<TrainingDTO> trainings;
	TrainingForm trainingForm;
	List<DocumentDTO> documents;
	DocumentForm documentForm;
	List<BankAccountDTO> bankAccounts;
	BankAccountForm bankAccountForm;
	
	public void init() {
				
		String nid = nav.getParam("nid");
		this.employee = new EmployeeService().getEmployeeByNid(nid);
		initProvisions();
		initEmployment();
		initGrades();
		initValidations();
		initEducations();
		initLanguages();
		initContacts();
		initTrainings();
		initBankAccounts();
		initDocuments();
	}
	
	
	public void updateGeneralitiesFromNCR()
	{
		try {
		EmployeeDTO emp = new EmployeeService().updateEmployeeFromNCR(this.employee.getNid());
		if(emp != null)
		{
			this.employee = emp;
			Messages.throwFacesMessage("Te dhenat u rifreskuan",1);
			
		}
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
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
	
	public void initContacts()
	{
		this.contactForm = new ContactForm();
		this.contacts = new ContactService().getEmployeeContacts(this.employee.getNid());
	}
	
	public void saveContact()
	{
		try {
			this.contactForm.setPersonNid(this.employee.getNid());
			new ContactService().registerContact(this.contactForm);
			initContacts();
			Messages.throwFacesMessage("Kontakti u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyContact()
	{
		try {
			this.contactForm.setPersonNid(this.employee.getNid());
			new ContactService().modifyContact(this.contactForm);
			initContacts();
			Messages.throwFacesMessage("Kontakti u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteContact(ContactDTO dto)
	{
		try {
			new ContactService().deleteContact(dto.getId());
			initContacts();
			Messages.throwFacesMessage("Kontakti u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onContactSelect(ContactDTO dto)
	{
		this.contactForm.setId(dto.getId());
		this.contactForm.setPersonNid(dto.getEmployee().getNid());
		this.contactForm.setContactTypeId(dto.getContactType().getId());
		this.contactForm.setValue(dto.getValue());
	}
	
	public void initTrainings()
	{
		this.trainingForm = new TrainingForm();
		this.trainings = new TrainingService().getEmployeeTrainings(this.employee.getNid());
	}
	
	public void saveTraining()
	{
		try {
			this.trainingForm.setPersonNid(this.employee.getNid());
			new TrainingService().registerTraining(this.trainingForm);
			initTrainings();
			Messages.throwFacesMessage("Trajnimi/Kualifikimi u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyTraining()
	{
		try {
			this.trainingForm.setPersonNid(this.employee.getNid());
			new TrainingService().modifyTraining(this.trainingForm);
			initTrainings();
			Messages.throwFacesMessage("Trajnimi/Kualifikimi u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteTraining(TrainingDTO dto)
	{
		try {
			new TrainingService().deleteTraining(dto.getId());
			initTrainings();
			Messages.throwFacesMessage("Trajnimi/Kualifikimi u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onTrainingSelect(TrainingDTO dto)
	{
		this.trainingForm.setId(dto.getId());
		this.trainingForm.setPersonNid(dto.getEmployee().getNid());
		this.trainingForm.setCompleted(dto.isCompleted());
		this.trainingForm.setDescription(dto.getDescription());
		if(dto.getInstitution() != null)
		{
		   this.trainingForm.setInstitutionId(dto.getInstitution().getId());
		}
		this.trainingForm.setResult(dto.getResult());
		this.trainingForm.setTrainingDate(dto.getTrainingDate());
		this.trainingForm.setTrainingTypeId(dto.getTrainingType().getId());
	}
	
	public void initBankAccounts()
	{
		this.bankAccountForm = new BankAccountForm();
		this.bankAccounts = new BankService().getEmployeeBankAccounts(this.employee.getNid());
	}
	
	public void saveBankAccount()
	{
		try {
			this.bankAccountForm.setPersonNid(this.employee.getNid());
			new BankService().registerBankAccount(this.bankAccountForm);
			initBankAccounts();
			Messages.throwFacesMessage("Llogaria bankare u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyBankAccount()
	{
		try {
			this.bankAccountForm.setPersonNid(this.employee.getNid());
			new BankService().modifyBankAccount(this.bankAccountForm);
			initBankAccounts();
			Messages.throwFacesMessage("Llogaria bankare u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteBankAccount(BankAccountDTO dto)
	{
		try {
			new BankService().deleteBankAccount(dto.getId());
			initBankAccounts();
			Messages.throwFacesMessage("Llogaria bankare u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onBankAccountSelect(BankAccountDTO dto)
	{
		this.bankAccountForm.setId(dto.getId());
		this.bankAccountForm.setPersonNid(dto.getEmployee().getNid());
		this.bankAccountForm.setBankId(dto.getBank().getId());
		this.bankAccountForm.setIban(dto.getIban());
	}
	
	public void initDocuments()
	{
		this.documentForm = new DocumentForm();
		this.documents = new DocumentService().getEmployeeDocuments(this.employee.getNid());
	}
	
	public void saveDocument()
	{
		try {
			this.documentForm.setPersonNid(this.employee.getNid());
			new DocumentService().registerDocument(this.documentForm);
			initDocuments();
			Messages.throwFacesMessage("Documenti u regjistrua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modifyDocument()
	{
		try {
			this.documentForm.setPersonNid(this.employee.getNid());
			new DocumentService().modifyDocument(this.documentForm);
			initDocuments();
			Messages.throwFacesMessage("Documenti u ndryshua!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void deleteDocument(DocumentDTO dto)
	{
		try {
			new DocumentService().deleteDocument(dto.getId());
			initDocuments();
			Messages.throwFacesMessage("Kontakti u fshi!", 1);
		}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void onDocumentSelect(DocumentDTO dto)
	{
		this.documentForm.setId(dto.getId());
		this.documentForm.setPersonNid(dto.getEmployee().getNid());
		this.documentForm.setDescription(dto.getDescription());
		this.documentForm.setDocumentDate(dto.getDocumentDate());
		this.documentForm.setDocumentName(dto.getDocumentName());
		this.documentForm.setPath(dto.getPath());
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        
        this.documentForm.setData(CalculatorUtil.encodeBASE64(event.getFile().getContents()));
    }

}
