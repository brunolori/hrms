package com.bvlsh.hr.ui.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.bvlsh.hr.ui.api.clients.EmployeeClient;
import com.bvlsh.hr.ui.api.clients.NcrClient;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.dto.EmployeeHistoryDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.forms.EmployeeSx;
import com.bvlsh.hr.ui.models.PersonRest;
import com.bvlsh.hr.ui.utils.StringUtil;


public class EmployeeService {

	
	public EmployeeDTO registerEmployee(EmployeeForm form)
	{
		return new EmployeeClient().registerEmployee(form);
	}

	public EmployeeHistoryDTO updateEmployment(EmployeeForm form)
	{
		return new EmployeeClient().updateEmployment(form);
	}
	
	public void removeEmploymeeFromPosition(EmployeeForm form)
	{
		new EmployeeClient().removeEmploymeeFromPosition(form);
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
	
	public EmployeeDTO getEmployeeFromNCR(String nid)
	{
		List<PersonRest> list = new NcrClient().searchPerson(nid, null, null, null);
		if(list == null || list.isEmpty()) return null;
		
		PersonRest r = list.get(0);
		EmployeeDTO e;
		e = getEmployeeByNid(nid);
		if(e == null)
		{
			e = new EmployeeDTO();
		}
		e.setFatherName(r.getFathername());
		e.setName(r.getName());
		e.setGender(r.getGender());
		e.setSurname(r.getSurname());
		e.setMotherName(r.getMotherName());
		e.setMaidenName(r.getMaidenName());
		if(StringUtil.isValid(r.getCivilStatus()))
		{
			if(r.getCivilStatus().equalsIgnoreCase("beqar/e"))
			{
				e.setCivilStatus("B");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("martuar"))
			{
				e.setCivilStatus("M");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("divorcuar"))
			{
				e.setCivilStatus("D");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("i/e ve"))
			{
				e.setCivilStatus("V");
			}
		}
				
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			e.setDob(df.parse(r.getDob()));
		}catch(Exception ex) {}
		e.setPob(r.getPob());
		e.setNid(r.getNid());
		e.setCitizenship(new StateDTO("AL"));
		
		return e;
		
	}
	
	public EmployeeDTO updateEmployeeFromNCR(String nid)
	{
		List<PersonRest> list = new NcrClient().searchPerson(nid, null, null, null);
		if(list == null || list.isEmpty()) return null;
		
		PersonRest r = list.get(0);
		EmployeeDTO e = getEmployeeByNid(nid);
		if(e == null) return null;
		
		EmployeeForm f = new EmployeeForm();
						
		f.setFatherName(r.getFathername());
		f.setName(r.getName());
		f.setGender(r.getGender());
		f.setSurname(r.getSurname());
		f.setMotherName(r.getMotherName());
		f.setMaidenName(r.getMaidenName());
		if(StringUtil.isValid(r.getCivilStatus()))
		{
			if(r.getCivilStatus().equalsIgnoreCase("beqar/e"))
			{
				f.setCivilStatus("B");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("martuar"))
			{
				f.setCivilStatus("M");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("divorcuar"))
			{
				f.setCivilStatus("D");
			}
			else if(r.getCivilStatus().equalsIgnoreCase("i/e ve"))
			{
				f.setCivilStatus("V");
			}
		}
				
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			f.setDob(df.parse(r.getDob()));
		}catch(Exception ex) {}
		f.setPob(r.getPob());
		f.setNid(r.getNid());
		f.setCitizenshipCode("AL");
		//nga emp actual
		f.setDossierNo(e.getDossierNo());
		f.setEmployeeNo(e.getDossierNo());
		if(e.getPaymentCategory() != null)
		{
		   f.setPaymentCategoryId(e.getPaymentCategory().getId());
		}
		
		return updateEmployeeGeneralities(f);
		
	}
	
	

}
