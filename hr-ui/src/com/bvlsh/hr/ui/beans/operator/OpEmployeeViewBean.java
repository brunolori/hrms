package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.services.AdministrativeProvisionService;
import com.bvlsh.hr.ui.services.EmployeeService;
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
	
	
	
	
	public void init() {
				
		String nid = nav.getParam("nid");
		this.employee = new EmployeeService().getEmployeeByNid(nid);
		initProvisions();
		initEmployment();
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
	
	
	
	
	
	
	
	

}
