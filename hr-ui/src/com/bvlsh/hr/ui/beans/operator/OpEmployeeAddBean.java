package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.forms.EmployeeForm;
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
	
	
	
	
	EmployeeForm form;
	Integer departmentId;
	List<DepartmentPositionDTO> departmentPositions;
	
	boolean employeeExists;
	
	
	
	@PostConstruct
	public void load()
	{
		
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
				else
				{
					//fill form from employee
				}
			}
			else
			{
				// search NCR and fill form
			}
		}
	}
	
	public void onDepartmentSelect()
	{
		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.departmentId);
	}
	

}
