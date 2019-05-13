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
import com.bvlsh.hr.ui.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.ui.forms.ForeignLanguageSx;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.ForeignLanguageService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpRapLanguageBean implements Serializable {

	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	ForeignLanguageSx req;
	List<EmployeeForeignLanguageDTO> list;
	List<DepartmentDTO> departments;
	
	
	
	@PostConstruct
	public void load()
	{
		this.req = new ForeignLanguageSx();
		this.departments = new DepartmentService().getDepartments();
	}

	
	public void search()
	{
		this.list = new ForeignLanguageService().searchForeignLanguages(req);
	}
	
	
	public void clear()
	{
		this.req = new ForeignLanguageSx();
		this.list = null;
	}
	
	public void onEmployeeSelect(EmployeeForeignLanguageDTO t)
	{	
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid",t.getEmployee().getNid()));
		nav.navigate("employee_view",params);
		
	}
	
}
