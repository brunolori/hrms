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
import com.bvlsh.hr.ui.dto.JobValidationDTO;
import com.bvlsh.hr.ui.forms.JobValidationSx;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.JobValidationService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpRapValidationBean implements Serializable {

	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	JobValidationSx req;
	List<JobValidationDTO> list;
	List<DepartmentDTO> departments;
	
	
	
	@PostConstruct
	public void load()
	{
		this.req = new JobValidationSx();
		this.departments = new DepartmentService().getDepartments();
	}

	
	public void search()
	{
		this.list = new JobValidationService().searchJobValidations(req);
	}
	
	
	public void clear()
	{
		this.req = new JobValidationSx();
		this.list = null;
	}
	
	public void onEmployeeSelect(JobValidationDTO t)
	{	
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid",t.getEmployee().getNid()));
		nav.navigate("employee_view",params);
		
	}
	
}
