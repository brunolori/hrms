package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.EmployeeService;
import com.bvlsh.hr.ui.utils.Messages;
import com.bvlsh.hr.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpEmployeeAddBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	EmployeeForm form;
	Integer departmentId;
	
	List<DepartmentPositionDTO> departmentPositions;
	boolean employeeExists;
	
	
	
	
	@PostConstruct
	public void load()
	{
		init();
	}
	
	
	public void init()
	{
		this.form = new EmployeeForm();
		this.departmentId = null;
		this.departmentPositions = null;
		this.employeeExists = false;
	}
	
	public void searchWithNid()
	{
		if(StringUtil.isValid(form.getNid()))
		{
			EmployeeDTO e = new EmployeeService().getEmployeeByNid(form.getNid());
			if(e != null)
			{
				if(e.getEndDate() == null)
				{
					this.employeeExists = true;
					Messages.throwFacesMessage("Personi eshte aktualisht punonjes,"
							+ " nese doni te vazhdoni regjistrimin plotesoni daten e lenies se pozicionit te meparshem", 2);
				}

				
				this.form.setCivilStatus(e.getCivilStatus());
				this.form.setDepartmentPositionId(e.getDepartmentPosition().getId());
				this.form.setNid(e.getNid());
				this.form.setName(e.getName());
				this.form.setSurname(e.getSurname());
				this.form.setFatherName(e.getFatherName());
				this.form.setMotherName(e.getMotherName());
				this.form.setGender(e.getGender());
				this.form.setPob(e.getPob());
				this.form.setDob(e.getDob());
				this.form.setEmployeeNo(e.getEmployeeNo());
				this.form.setDossierNo(e.getDossierNo());
				if(e.getCitizenship() != null)
				{
					form.setCitizenshipCode(e.getCitizenship().getCode());
				}
				if(e.getNationality() != null)
				{
					form.setNationalityCode(e.getNationality().getCode());
				}
				
				
			}
			else
			{
				EmployeeDTO ncr = new EmployeeService().getEmployeeFromNCR(form.getNid());
				if(ncr == null)
				{
					Messages.throwFacesMessage("Nuk u gjend asnjë person në Gjendjen Civile", 2);
					return;
				}
				
				this.form.setCivilStatus(ncr.getCivilStatus());
				this.form.setNid(ncr.getNid());
				this.form.setName(ncr.getName());
				this.form.setSurname(ncr.getSurname());
				this.form.setFatherName(ncr.getFatherName());
				this.form.setMotherName(ncr.getMotherName());
				this.form.setGender(ncr.getGender());
				this.form.setPob(ncr.getPob());
				this.form.setDob(ncr.getDob());
				this.form.setMaidenName(ncr.getMaidenName());
				if(ncr.getCitizenship() != null)
				{
					form.setCitizenshipCode(ncr.getCitizenship().getCode());
				}
				
			}
		}
	}
	
	public void onDepartmentSelect()
	{
		if(this.departmentId == null)
		{
			this.departmentPositions = null;
			return;
		}
		
		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.departmentId);
	}
	

	
	
	
	public void register()
	{
		
		try {
			
			new EmployeeService().registerEmployee(form);
			init();
			Messages.throwFacesMessage("Punonjësi u regjistrua me sukses", 1);
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
		
		
	}
	
	
	public void registerAndView()
	{
		try {
			
			EmployeeDTO e = new EmployeeService().registerEmployee(form);
			init();
			List<Param> params = new ArrayList<>();
			params.add(new Param("nid", e.getNid()));
			nav.navigate("employee_view",params);
			Messages.throwFacesMessage("Punonjësi u regjistrua me sukses", 1);
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	public void clear()
	{
		init();
	}
	
	
	
	
}
