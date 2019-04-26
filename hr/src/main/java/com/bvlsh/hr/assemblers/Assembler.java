package com.bvlsh.hr.assemblers;

import java.util.ArrayList;
import java.util.List;
import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.dto.BankAccountDTO;
import com.bvlsh.hr.dto.BankDTO;
import com.bvlsh.hr.dto.ContactDTO;
import com.bvlsh.hr.dto.ContactTypeDTO;
import com.bvlsh.hr.dto.DepartmentCategoryDTO;
import com.bvlsh.hr.dto.DepartmentDTO;
import com.bvlsh.hr.dto.DepartmentPositionDTO;
import com.bvlsh.hr.dto.DocumentDTO;
import com.bvlsh.hr.dto.EducationDTO;
import com.bvlsh.hr.dto.EducationTypeDTO;
import com.bvlsh.hr.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.dto.ForeignLanguageDTO;
import com.bvlsh.hr.dto.GradeDTO;
import com.bvlsh.hr.dto.InstitutionDTO;
import com.bvlsh.hr.dto.JobValidationDTO;
import com.bvlsh.hr.dto.PaymentCategoryDTO;
import com.bvlsh.hr.dto.EmployeeDTO;
import com.bvlsh.hr.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.dto.EmployeeGradeDTO;
import com.bvlsh.hr.dto.PositionDTO;
import com.bvlsh.hr.dto.ProvisionTypeDTO;
import com.bvlsh.hr.dto.RoleDTO;
import com.bvlsh.hr.dto.StateDTO;
import com.bvlsh.hr.dto.StudyFieldDTO;
import com.bvlsh.hr.dto.TrainingDTO;
import com.bvlsh.hr.dto.TrainingTypeDTO;
import com.bvlsh.hr.dto.UserDTO;
import com.bvlsh.hr.dto.ValidationTypeDTO;
import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.entities.Bank;
import com.bvlsh.hr.entities.BankAccount;
import com.bvlsh.hr.entities.Contact;
import com.bvlsh.hr.entities.ContactType;
import com.bvlsh.hr.entities.Department;
import com.bvlsh.hr.entities.DepartmentCategory;
import com.bvlsh.hr.entities.DepartmentPosition;
import com.bvlsh.hr.entities.Document;
import com.bvlsh.hr.entities.Education;
import com.bvlsh.hr.entities.EducationType;
import com.bvlsh.hr.entities.EmployeeHistory;
import com.bvlsh.hr.entities.ForeignLanguage;
import com.bvlsh.hr.entities.Grade;
import com.bvlsh.hr.entities.Institution;
import com.bvlsh.hr.entities.JobValidation;
import com.bvlsh.hr.entities.PaymentCategory;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeForeignLanguage;
import com.bvlsh.hr.entities.EmployeeGrade;
import com.bvlsh.hr.entities.Position;
import com.bvlsh.hr.entities.ProvisionType;
import com.bvlsh.hr.entities.Role;
import com.bvlsh.hr.entities.State;
import com.bvlsh.hr.entities.StudyField;
import com.bvlsh.hr.entities.Training;
import com.bvlsh.hr.entities.TrainingType;
import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.entities.ValidationType;

public class Assembler {

	public AdministrativeProvisionDTO toDto(AdministrativeProvision e) {

		if (e == null)
			return null;

		AdministrativeProvisionDTO dto = new AdministrativeProvisionDTO();
		dto.setId(e.getId());
		dto.setActNo(e.getActNo());
		dto.setActDate(e.getActDate());
		dto.setStartDate(e.getStartDate());
		dto.setReason(e.getReason());
		dto.setActive((e.getActive() != null) && (e.getActive() == IStatus.ACTIVE));
		dto.setValidityInMonths(e.getValidityInMonths());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setEmployee(toDto(e.getEmployee()));
		dto.setProvisionType(toDto(e.getProvisionType()));

		return dto;

	}

	public List<AdministrativeProvisionDTO> administrativePositionListToDto(List<AdministrativeProvision> admPosition) {

		if (admPosition == null || admPosition.isEmpty())
			return null;

		List<AdministrativeProvisionDTO> list = new ArrayList<>();

		for (AdministrativeProvision ap : admPosition) {
			list.add(toDto(ap));
		}

		return list;

	}

	public BankAccountDTO toDto(BankAccount e) {

		if (e == null)
			return null;

		BankAccountDTO dto = new BankAccountDTO();
		dto.setId(e.getId());
		dto.setIban(e.getIban());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setBank(toDto(e.getBank()));
        dto.setEmployee(toDto(e.getEmployee()));
		return dto;

	}

	public List<BankAccountDTO> bankAccountListToDto(List<BankAccount> admPosition) {

		if (admPosition == null || admPosition.isEmpty())
			return null;

		List<BankAccountDTO> list = new ArrayList<>();

		for (BankAccount ba : admPosition) {
			list.add(toDto(ba));
		}

		return list;

	}
	
	
	public BankDTO toDto(Bank e) {

		if (e == null) return null;

		BankDTO dto = new BankDTO();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));

		return dto;

	}

	public List<BankDTO> bankListToDto(List<Bank> bank) {

		if (bank == null || bank.isEmpty()) return null;

		List<BankDTO> list = new ArrayList<>();

		for (Bank b : bank) {
			list.add(toDto(b));
		}

		return list;

	}
	
	
	
	public ContactDTO toDto(Contact e) {

		if (e == null) return null;

		ContactDTO dto = new ContactDTO();
		dto.setId(e.getId());
		dto.setValue(e.getValue());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setContactType(toDto(e.getContactType()));
		dto.setEmployee(toDto(e.getEmployee()));

		return dto;

	}

	public List<ContactDTO> contactListToDto(List<Contact> contact) {

		if (contact == null || contact.isEmpty()) return null;

		List<ContactDTO> list = new ArrayList<>();

		for (Contact c : contact) {
			list.add(toDto(c));
		}

		return list;

	}
	
	
	public ContactTypeDTO toDto(ContactType e) {

		if (e == null) return null;

		ContactTypeDTO dto = new ContactTypeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));

		return dto;

	}

	public List<ContactTypeDTO> contactTypeListToDto(List<ContactType> contactType) {

		if (contactType == null || contactType.isEmpty()) return null;

		List<ContactTypeDTO> list = new ArrayList<>();

		for (ContactType ct : contactType) {
			list.add(toDto(ct));
		}

		return list;

	}
	
	
	public DepartmentCategoryDTO toDto(DepartmentCategory e) {

		if (e == null) return null;

		DepartmentCategoryDTO dto = new DepartmentCategoryDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));

		return dto;

	}

	public List<DepartmentCategoryDTO> departmentCategoryListToDto(List<DepartmentCategory> deptCategory) {

		if (deptCategory == null || deptCategory.isEmpty()) return null;

		List<DepartmentCategoryDTO> list = new ArrayList<>();

		for (DepartmentCategory dc : deptCategory) {
			list.add(toDto(dc));
		}

		return list;

	}
	
	public DepartmentDTO toDto(Department e) {

		if (e == null) return null;

		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setPositionsNo(e.getPositionsNo());
		dto.setExpanded((e.getExpanded() != null) && (e.getExpanded() == IStatus.ACTIVE));
		dto.setColor(e.getColor());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setCategory(toDto(e.getCategory()));
		dto.setParent(toDto(e.getParent()));

		return dto;

	}

	public List<DepartmentDTO> departmentListToDto(List<Department> department) {

		if (department == null || department.isEmpty()) return null;

		List<DepartmentDTO> list = new ArrayList<>();

		for (Department d : department) {
			list.add(toDto(d));
		}

		return list;

	}
	
	public DepartmentPositionDTO toDto(DepartmentPosition e) {
		if (e == null)
			return null;

		DepartmentPositionDTO dto = new DepartmentPositionDTO();
		dto.setPaymentCategory(toDto(e.getPaymentCategory()));
		dto.setName(e.getName());
		dto.setCreateTime(e.getCreateTime());
		dto.setCreateUser(e.getCreateUser());
		dto.setCurrentEmployee(toDto(e.getCurrentEmployee()));
		dto.setDepartment(toDto(e.getDepartment()));
		dto.setPosition(toDto(e.getPosition()));
		dto.setUpdateTime(e.getUpdateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setId(e.getId());

		return dto;
	}

	public List<DepartmentPositionDTO> departmentPositionListToDto(List<DepartmentPosition> deptPosition) {
		if (deptPosition == null || deptPosition.isEmpty())
			return null;

		List<DepartmentPositionDTO> list = new ArrayList<>();

		for (DepartmentPosition dp : deptPosition) {
			list.add(toDto(dp));
		}

		return list;

	}
	
	public DocumentDTO toDto(Document e) {

		if (e == null) return null;

		DocumentDTO dto = new DocumentDTO();
		dto.setId(e.getId());
		dto.setDocumentName(e.getDocumentName());
		dto.setDocumentDate(e.getDocumentDate());
		dto.setDescription(e.getDescription());
		dto.setPath(e.getPath());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setEmployee(toDto(e.getEmployee()));

		return dto;

	}

	public List<DepartmentDTO> documentListToDto(List<Department> document) {

		if (document == null || document.isEmpty()) return null;

		List<DepartmentDTO> list = new ArrayList<>();

		for (Department doc : document) {
			list.add(toDto(doc));
		}

		return list;

	}
	
	
	public EducationDTO toDto(Education e) {

		if (e == null) return null;

		EducationDTO dto = new EducationDTO();
		dto.setId(e.getId());
		dto.setIssueDate(e.getIssueDate());
		dto.setDescription(e.getDescription());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setEducationType(toDto(e.getEducationType()));
		dto.setInstitution(toDto(e.getInstitution()));
		dto.setEmployee(toDto(e.getEmployee()));
		dto.setStudyField(toDto(e.getStudyField()));

		return dto;

	}

	public List<EducationDTO> educationListToDto(List<Education> education) {

		if (education == null || education.isEmpty()) return null;

		List<EducationDTO> list = new ArrayList<>();

		for (Education e : education) {
			list.add(toDto(e));
		}

		return list;

	}
	
	
	public EducationTypeDTO toDto(EducationType e) {

		if (e == null) return null;

		EducationTypeDTO dto = new EducationTypeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		return dto;

	}

	public List<EducationTypeDTO> educationTypeListToDto(List<EducationType> educationType) {

		if (educationType == null || educationType.isEmpty()) return null;

		List<EducationTypeDTO> list = new ArrayList<>();

		for (EducationType et : educationType) {
			list.add(toDto(et));
		}

		return list;

	}
	
	
	public EmployeeHistoryDTO toDto(EmployeeHistory e) {

		if (e == null) return null;

		EmployeeHistoryDTO dto = new EmployeeHistoryDTO();
		dto.setId(e.getId());
		dto.setStartDate(e.getStartDate());
		dto.setEndDate(e.getEndDate());
		dto.setEmployeeNo(e.getEmployeeNo());
		dto.setDossierNo(e.getDossierNo());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setDepartmentPosition(toDto(e.getDepartmentPosition()));
		dto.setPaymentCategory(toDto(e.getPaymentCategory()));
		dto.setEmployee(toDto(e.getEmployee()));

		return dto;

	}

	public List<EmployeeHistoryDTO> employeeHistoryListToDto(List<EmployeeHistory> employeeHistory) {

		if (employeeHistory == null || employeeHistory.isEmpty()) return null;

		List<EmployeeHistoryDTO> list = new ArrayList<>();

		for (EmployeeHistory em : employeeHistory) {
			list.add(toDto(em));
		}

		return list;

	}
	
	
	public ForeignLanguageDTO toDto(ForeignLanguage e) {
		if (e == null) return null;

		ForeignLanguageDTO dto = new ForeignLanguageDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));

		return dto;
		
	}

	public List<ForeignLanguageDTO> foreignLanguageListToDto(List<ForeignLanguage> foreingLanguage) {

		if (foreingLanguage == null || foreingLanguage.isEmpty()) return null;

		List<ForeignLanguageDTO> list = new ArrayList<>();

		for (ForeignLanguage fl : foreingLanguage) {
			list.add(toDto(fl));
		}

		return list;

	}
	
	

	public GradeDTO toDto(Grade e) {
		if (e == null) return null;

		GradeDTO dto = new GradeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setLevel(e.getLevel());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setDepartmentCategory(toDto(e.getDepartmentCategory()));

		return dto;
		
	}

	public List<GradeDTO> gradeListToDto(List<Grade> grade) {

		if (grade == null || grade.isEmpty()) return null;

		List<GradeDTO> list = new ArrayList<>();

		for (Grade g : grade) {
			list.add(toDto(g));
		}

		return list;

	}
	
	public InstitutionDTO toDto(Institution e) {
		if (e == null) return null;

		InstitutionDTO dto = new InstitutionDTO();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
        dto.setState(toDto(e.getState()));
		return dto;
		
	}

	public List<InstitutionDTO> institutionListToDto(List<Institution> institution) {

		if (institution == null || institution.isEmpty()) return null;

		List<InstitutionDTO> list = new ArrayList<>();

		for (Institution i : institution) {
			list.add(toDto(i));
		}

		return list;

	}
	
	public JobValidationDTO toDto(JobValidation e) {
		if (e == null) return null;

		JobValidationDTO dto = new JobValidationDTO();
		
		dto.setId(e.getId());
		dto.setValidationDate(e.getValidationDate());
		dto.setStartDate(e.getStartDate());
		dto.setEndDate(e.getEndDate());
		dto.setSignedBy(e.getSignedBy());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setEmployee(toDto(e.getEmployee()));
		dto.setValidationType(toDto(e.getValidationType()));

		return dto;
		
	}

	public List<JobValidationDTO> jobValidationListToDto(List<JobValidation> jobValidation) {

		if (jobValidation == null || jobValidation.isEmpty()) return null;

		List<JobValidationDTO> list = new ArrayList<>();

		for (JobValidation jv : jobValidation) {
			list.add(toDto(jv));
		}

		return list;

	}
	
	public PaymentCategoryDTO toDto(PaymentCategory e) {
		if (e == null) return null;

		PaymentCategoryDTO dto = new PaymentCategoryDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));

		return dto;
		
	}

	public List<PaymentCategoryDTO> paymentCategoryListToDto(List<PaymentCategory> paymentCategory) {

		if (paymentCategory == null || paymentCategory.isEmpty()) return null;

		List<PaymentCategoryDTO> list = new ArrayList<>();

		for (PaymentCategory pc : paymentCategory) {
			list.add(toDto(pc));
		}

		return list;

	}
	
	
	public EmployeeDTO toDto(Employee e) {
		if (e == null) return null;

		EmployeeDTO dto = new EmployeeDTO();
		dto.setNid(e.getNid());
		dto.setName(e.getName());
		dto.setSurname(e.getSurname());
		dto.setFatherName(e.getFatherName());
		dto.setMotherName(e.getMotherName());
		dto.setMaidenName(e.getMaidenName());
		dto.setGender(e.getGender());
		dto.setEmployeeNo(e.getEmployeeNo());
		dto.setDossierNo(e.getDossierNo());
		dto.setStartDate(e.getStartDate());
		dto.setEndDate(e.getEndDate());
		dto.setDob(e.getDob());
		dto.setPob(e.getPob());
		dto.setCivilStatus(e.getCivilStatus());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setCitizenship(toDto(e.getCitizenship()));
		dto.setDepartmentPosition(toDto(e.getDepartmentPosition()));
		dto.setNationality(toDto(e.getNationality()));
		dto.setPaymentCategory(toDto(e.getPaymentCategory()));
		
		return dto;
		
	}

	public List<EmployeeDTO> employeeListToDto(List<Employee> employee) {

		if (employee == null || employee.isEmpty()) return null;

		List<EmployeeDTO> list = new ArrayList<>();

		for (Employee e : employee) {
			list.add(toDto(e));
		}

		return list;

	}
	
	
	public EmployeeForeignLanguageDTO toDto(EmployeeForeignLanguage e) {
		if (e == null) return null;

		EmployeeForeignLanguageDTO dto = new EmployeeForeignLanguageDTO();
		dto.setId(e.getId());
		dto.setLevel(e.getLevel());
		dto.setDescription(e.getDescription());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setForeignLanguage(toDto(e.getForeignLanguage()));
		dto.setEmployee(toDto(e.getEmployee()));
		
		return dto;
		
	}

	public List<EmployeeForeignLanguageDTO> employeeForeignLanguageListToDto(List<EmployeeForeignLanguage> employeeForeignLanguage) {

		if (employeeForeignLanguage == null || employeeForeignLanguage.isEmpty()) return null;

		List<EmployeeForeignLanguageDTO> list = new ArrayList<>();

		for (EmployeeForeignLanguage pfl : employeeForeignLanguage) {
			list.add(toDto(pfl));
		}

		return list;

	}
	
	public EmployeeGradeDTO toDto(EmployeeGrade e) {
		if (e == null) return null;

		EmployeeGradeDTO dto = new EmployeeGradeDTO();
	    dto.setId(e.getId());
	    dto.setStartDate(e.getStartDate());
	    dto.setEndDate(e.getEndDate());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setGrade(toDto(e.getGrade()));
		dto.setEmployee(toDto(e.getEmployee()));
		
		return dto;
		
	}

	public List<EmployeeGradeDTO> employeeGradeListToDto(List<EmployeeGrade> employeeGrade) {

		if (employeeGrade == null || employeeGrade.isEmpty()) return null;

		List<EmployeeGradeDTO> list = new ArrayList<>();

		for (EmployeeGrade pg : employeeGrade) {
			list.add(toDto(pg));
		}

		return list;

	}
	
	
	public PositionDTO toDto(Position e) {
		if (e == null) return null;

		PositionDTO dto = new PositionDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		return dto;
		
	}

	public List<PositionDTO> positionListToDto(List<Position> position) {

		if (position == null || position.isEmpty()) return null;

		List<PositionDTO> list = new ArrayList<>();

		for (Position p : position) {
			list.add(toDto(p));
		}

		return list;

	}
	
	
	public ProvisionTypeDTO toDto(ProvisionType e) {
		if (e == null) return null;

		ProvisionTypeDTO dto = new ProvisionTypeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		return dto;
		
	}

	public List<ProvisionTypeDTO> provisionTypeListToDto(List<ProvisionType> provisionType) {

		if (provisionType == null || provisionType.isEmpty()) return null;

		List<ProvisionTypeDTO> list = new ArrayList<>();

		for (ProvisionType pt : provisionType) {
			list.add(toDto(pt));
		}

		return list;

	}
	
	
	
	public RoleDTO toDto(Role e) {
		if (e == null) return null;

		RoleDTO dto = new RoleDTO();
		dto.setCode(e.getCode());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		return dto;
		
	}

	public List<RoleDTO> roleListToDto(List<Role> role) {

		if (role == null || role.isEmpty()) return null;

		List<RoleDTO> list = new ArrayList<>();

		for (Role r : role) {
			list.add(toDto(r));
		}

		return list;

	}
	
	
	public StateDTO toDto(State e) {
		if (e == null) return null;

		StateDTO dto = new StateDTO();
		dto.setCode(e.getCode());
		dto.setCodeAlpha3(e.getCodeAlpha3());
		dto.setName(e.getName());
		
		return dto;
		
	}

	public List<StateDTO> stateListToDto(List<State> state) {

		if (state == null || state.isEmpty()) return null;

		List<StateDTO> list = new ArrayList<>();

		for (State s : state) {
			list.add(toDto(s));
		}

		return list;

	}
	
	
	public StudyFieldDTO toDto(StudyField e) {
		if (e == null) return null;

		StudyFieldDTO dto = new StudyFieldDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		
		return dto;
		
	}

	public List<StudyFieldDTO> studyFieldListToDto(List<StudyField> studyField) {

		if (studyField == null || studyField.isEmpty()) return null;

		List<StudyFieldDTO> list = new ArrayList<>();

		for (StudyField sf : studyField) {
			list.add(toDto(sf));
		}

		return list;

	}
	
	public TrainingDTO toDto(Training e) {
		if (e == null) return null;

		TrainingDTO dto = new TrainingDTO();
		dto.setId(e.getId());
		dto.setDescription(e.getDescription());
		dto.setCompleted((e.getCompleted() != null) && (e.getCompleted() == IStatus.ACTIVE));
		dto.setResult(e.getResult());
		dto.setTrainingDate(e.getTrainingDate());
		dto.setCreateUser(e.getCreateUser());
		dto.setCreateTime(e.getCreateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setUpdateTime(e.getUpdateTime());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setInstitution(toDto(e.getInstitution()));
		dto.setEmployee(toDto(e.getEmployee()));
		dto.setTrainingType(toDto(e.getTrainingType()));
		
		return dto;
		
	}

	public List<TrainingDTO> trainingListToDto(List<Training> training) {

		if (training == null || training.isEmpty()) return null;

		List<TrainingDTO> list = new ArrayList<>();

		for (Training t : training) {
			list.add(toDto(t));
		}

		return list;

	}
	
	
	public TrainingTypeDTO toDto(TrainingType e) {
		if (e == null) return null;

		TrainingTypeDTO dto = new TrainingTypeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		return dto;
		
	}

	public List<TrainingTypeDTO> trainingTypeListToDto(List<TrainingType> trainingType) {

		if (trainingType == null || trainingType.isEmpty()) return null;

		List<TrainingTypeDTO> list = new ArrayList<>();

		for (TrainingType tt : trainingType) {
			list.add(toDto(tt));
		}

		return list;

	}
	
	public UserDTO toDto(User e) {
		if (e == null) return null;

		UserDTO dto = new UserDTO();
		dto.setUsername(e.getUsername());
		dto.setSecret(e.getSecret());
		dto.setRole(toDto(e.getRole()));
		dto.setEmployee(toDto(e.getEmployee()));
		
		return dto;
		
	}

	public List<UserDTO> userListToDto(List<User> user) {

		if (user == null || user.isEmpty()) return null;

		List<UserDTO> list = new ArrayList<>();

		for (User u : user) {
			list.add(toDto(u));
		}

		return list;

	}
	
	
	
	public ValidationTypeDTO toDto(ValidationType e) {
		if (e == null) return null;

		ValidationTypeDTO dto = new ValidationTypeDTO();
		dto.setId(e.getId());
		dto.setTag(e.getTag());
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		
		return dto;
		
	}

	public List<ValidationTypeDTO> validationTypeListToDto(List<ValidationType> validationType) {

		if (validationType == null || validationType.isEmpty()) return null;

		List<ValidationTypeDTO> list = new ArrayList<>();

		for (ValidationType vt: validationType) {
			list.add(toDto(vt));
		}

		return list;

	}
	
	
}
