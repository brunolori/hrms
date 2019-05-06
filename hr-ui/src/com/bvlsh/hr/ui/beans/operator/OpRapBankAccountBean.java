package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.ui.dto.BankAccountDTO;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.forms.BankAccountSx;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.services.BankService;
import com.bvlsh.hr.ui.services.DepartmentService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpRapBankAccountBean implements Serializable {

	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	BankAccountSx req;
	List<BankAccountDTO> list;
	List<DepartmentDTO> departments;
	
	
	
	@PostConstruct
	public void load()
	{
		this.req = new BankAccountSx();
		this.departments = new DepartmentService().getDepartments();
	}

	
	public void search()
	{
		this.list = new BankService().searchBankAccounts(req);
	}
	
	
	public void clear()
	{
		this.req = new BankAccountSx();
		this.list = null;
	}
	
	public void onEmployeeSelect(AdministrativeProvisionDTO t)
	{	
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid",t.getEmployee().getNid()));
		nav.navigate("employee_view",params);
		
	}
	
}
