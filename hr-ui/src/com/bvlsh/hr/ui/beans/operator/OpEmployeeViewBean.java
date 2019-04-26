package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.services.EmployeeService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpEmployeeViewBean implements Serializable {
	
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	EmployeeDTO employee;
	List<EmployeeHistoryDTO> history;
	
	
	
	
	
	public void init() {
				
		String nid = nav.getParam("nid");
		this.employee = new EmployeeService().getEmployeeByNid(nid);
	}
	
	
	
	
	

}
