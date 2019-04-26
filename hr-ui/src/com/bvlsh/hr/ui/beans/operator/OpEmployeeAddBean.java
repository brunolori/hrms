package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.CacheBean;
import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
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
	@ManagedProperty(value = "#{cacheBean}")
	CacheBean cache;
	
	EmployeeForm form;
	Integer departmentId;
	
	List<DepartmentPositionDTO> departmentPositions;
	
	boolean employeeExists;
	
	List<StateDTO> states;
	
	
	
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
		this.states = null;
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
		if(this.departmentId == null)
		{
			this.departmentPositions = null;
			return;
		}
		
		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.departmentId);
	}
	

	public void filterState(String query)
	{
		this.states = cache.filterState(query);
		System.out.println(states);
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
