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
import com.bvlsh.hr.ui.dto.EmployeeGradeDTO;
import com.bvlsh.hr.ui.forms.GradeSx;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.GradeService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpRapGradeBean implements Serializable {

	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	GradeSx req;
	List<EmployeeGradeDTO> list;
	List<DepartmentDTO> departments;
	
	
	
	@PostConstruct
	public void load()
	{
		this.req = new GradeSx();
		this.departments = new DepartmentService().getDepartments();
	}

	
	public void search()
	{
		this.list = new GradeService().searchGrades(req);
	}
	
	
	public void clear()
	{
		this.req = new GradeSx();
		this.list = null;
	}
	
	public void onEmployeeSelect(EmployeeGradeDTO t)
	{	
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid",t.getEmployee().getNid()));
		nav.navigate("employee_view",params);
		
	}
	
}
