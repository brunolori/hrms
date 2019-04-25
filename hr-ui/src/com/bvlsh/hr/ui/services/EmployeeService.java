package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.EmployeeClient;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.forms.EmployeeSx;


public class EmployeeService {

	
	public EmployeeDTO registerEmployee(EmployeeForm form)
	{
		return new EmployeeClient().registerEmployee(form);
	}

	public EmployeeDTO changeEmployeePosition(EmployeeForm form)
	{
		return new EmployeeClient().changeEmployeePosition(form);
	}

	public EmployeeDTO updateEmployeeGeneralities(EmployeeForm form) 
	{
		return new EmployeeClient().updateEmployeeGeneralities(form);
	}

	public List<EmployeeDTO> searchEmployee(EmployeeSx sx) 
	{
		return new EmployeeClient().searchEmployee(sx);
	}

	public List<EmployeeHistoryDTO> getEmployeeHistory(String nid) 
	{
		return new EmployeeClient().getEmployeeHistory(nid);
	}

	public EmployeeDTO getEmployeeByNid(String nid)
	{
		return new EmployeeClient().getEmployeeByNid(nid);
	}
	
	
	

}
