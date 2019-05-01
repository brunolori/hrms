package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.AdministrativeProvisionDAO;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.ProvisionType;
import com.bvlsh.hr.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class AdministrativeProvisionService {

	
	
	@Autowired CrudDAO crudDAO;
	@Autowired AdministrativeProvisionDAO provisionDAO;

	public List<AdministrativeProvision> searchProvisions(AdministrativeProvisionSx sx, String uname) {
		return provisionDAO.searchProvisions(sx);
	}

	
	@Transactional
	public AdministrativeProvision registerProvision(AdministrativeProvisionForm form, String uname) {

	if(!StringUtil.isValid(form.getPersonNid())) {
			throw new ValidationException("Punonjesi i pa percaktuar");
	}
	
	if(!StringUtil.isValid(form.getActNo())) {
		throw new ValidationException("Plotesoni Nr e aktit");
	}
	
	if(form.getActDate() == null) {
		throw new ValidationException("Plotesoni Daten e aktit");
	}
	
	if(form.getStartDate() == null) {
		throw new ValidationException("Plotesoni Daten e fillimit");
	}
	
	if(form.getValidityInMonths() == null || form.getValidityInMonths() < 1) {
		throw new ValidationException("Plotesoni vlefshmerine");
	}
	
	if(form.getProvisionTypeId() == null) {
		throw new ValidationException("Plotesoni tipin e mases");
	}
	
	Employee e = crudDAO.findById(Employee.class, form.getPersonNid());
	
	ProvisionType pt = crudDAO.findById(ProvisionType.class, form.getProvisionTypeId());
	
	AdministrativeProvision a = new AdministrativeProvision();
	a.setActDate(form.getActDate());
	a.setActive(form.isActive()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
	a.setActNo(form.getActNo());
	a.setCreateTime(Calendar.getInstance().getTime());
	a.setCreateUser(uname);
	a.setEmployee(e);
	a.setProvisionType(pt);
	a.setReason(form.getReason());
	a.setStartDate(form.getStartDate());
	a.setStatus(IStatus.ACTIVE);
	a.setUpdateTime(Calendar.getInstance().getTime());
	a.setUpdateUser(uname);
	a.setValidityInMonths(form.getValidityInMonths());
	
	return crudDAO.create(a);
	
	
}
	
	@Transactional
	public AdministrativeProvision modifyProvision(AdministrativeProvisionForm form, String uname) {
		
	if(form.getId() == null)
	{
		throw new ValidationException("Masa administrative e pa percaktuar");
	}
	
	if(!StringUtil.isValid(form.getPersonNid())) {
		throw new ValidationException("Punonjesi i pa percaktuar");
	}
	
	if(!StringUtil.isValid(form.getActNo())) {
		throw new ValidationException("Plotesoni Nr e aktit");
	}
	
	if(form.getActDate() == null) {
		throw new ValidationException("Plotesoni Daten e aktit");
	}
	
	if(form.getStartDate() == null) {
		throw new ValidationException("Plotesoni Daten e fillimit");
	}
	
	if(form.getValidityInMonths() == null || form.getValidityInMonths() < 1) {
		throw new ValidationException("Plotesoni vlefshmerine");
	}
	
	if(form.getProvisionTypeId() == null) {
		throw new ValidationException("Plotesoni tipin e mases");
	}
	
	//Employee e = crudDAO.findById(Employee.class, form.getPersonNid());
	
	ProvisionType pt = crudDAO.findById(ProvisionType.class, form.getProvisionTypeId());
	
	AdministrativeProvision a = crudDAO.findById(AdministrativeProvision.class, form.getId());
	a.setActDate(form.getActDate());
	a.setActive(form.isActive()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
	a.setActNo(form.getActNo());
	//a.setEmployee(e);
	a.setProvisionType(pt);
	a.setReason(form.getReason());
	a.setStartDate(form.getStartDate());
	a.setUpdateTime(Calendar.getInstance().getTime());
	a.setUpdateUser(uname);
	a.setValidityInMonths(form.getValidityInMonths());
	
	return crudDAO.update(a);
	
	}

	@Transactional
	public void deleteProvision(Integer provisionId, String uname) {
		AdministrativeProvision a = crudDAO.findById(AdministrativeProvision.class, provisionId);
		a.setUpdateTime(Calendar.getInstance().getTime());
		a.setUpdateUser(uname);
		a.setStatus(IStatus.NOT_ACTIVE);
		crudDAO.update(a);
	}

	public List<AdministrativeProvision> getEmployeeProvisions(String nid, String uname) {
		return provisionDAO.getProvisionsByNid(nid);
	}

}
