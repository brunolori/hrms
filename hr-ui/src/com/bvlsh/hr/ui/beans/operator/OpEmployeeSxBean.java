package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.GradeDTO;
import com.bvlsh.hr.ui.forms.EmployeeSx;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.EmployeeService;
import com.bvlsh.hr.ui.services.HelperService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;



@ManagedBean
@ViewScoped
@Getter @Setter
public class OpEmployeeSxBean implements Serializable{

	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	EmployeeSx req;
	List<EmployeeDTO> employees;
	EmployeeDTO employee;
	
	List<DepartmentDTO> departments;
	List<GradeDTO> grades;
	
	boolean renderFilter;
	
	
	@PostConstruct
	public void load()
	{
		renderFilter = true;
		req = new EmployeeSx();
		this.departments = new DepartmentService().getDepartments();
		this.grades = new HelperService().loadGrades();
	}
	
	
	public void onEmployeeSelect()
	{	
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid",employee.getNid()));
		nav.navigate("employee_view",params);
		
	}
	
	
	public void clear()
	{
		this.req = new EmployeeSx();
		renderFilter();
		
	}

	public void renderFilter()
	{
		this.renderFilter = true;
		this.employee = null;
		this.employees = null;
	}
	
	public void renderList()
	{
		this.renderFilter = false;
	}


	public void search()
	{
		try {
			this.employees = new EmployeeService().searchEmployee(req);
			if(employees != null && !employees.isEmpty())
			{
				renderList();
				
			}
			else
			{
				renderFilter();
				Messages.throwFacesMessage("Nuk u gjend asnje punonjës", 2);
			}
			
		}catch(Exception a) {
			Messages.throwFacesMessage(a);
		}
		
		
	}
	
	
	
	
	
}
