package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.EmployeeDAO;
import com.bvlsh.hr.entities.DepartmentPosition;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeHistory;
import com.bvlsh.hr.entities.PaymentCategory;
import com.bvlsh.hr.entities.State;
import com.bvlsh.hr.exceptions.EntityExistsException;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.EmployeeForm;
import com.bvlsh.hr.forms.EmployeeSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class EmployeeService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired EmployeeDAO employeeDAO;
	
	
	@Transactional
	public Employee registerEmployee(EmployeeForm form, String uname)
	{
		
		if(form == null)
		{
			throw new ValidationException("Forma e pa plotësuar!");
		}
		
		if(!StringUtil.isValid(form.getNid()))
		{
			throw new ValidationException("Plotësoni Numrin Personal!");
		}
		
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Plotësoni Emrin!");
		}
		
		if(!StringUtil.isValid(form.getSurname()))
		{
			throw new ValidationException("Plotësoni Mbiemrin!");
		}
		
		if(form.getDob() == null)
		{
			throw new ValidationException("Plotësoni Datëlindjen!");
		}
		
		if(!StringUtil.isValid(form.getGender()))
		{
			throw new ValidationException("Plotësoni Gjininë!");
		}
		
		if(!StringUtil.isValid(form.getEmployeeNo()))
		{
			throw new ValidationException("Plotësoni Numrin e Punonjësit!");
		}
		
		if(!StringUtil.isValid(form.getDossierNo()))
		{
			throw new ValidationException("Plotësoni Numrin e Dosjes!");
		}
		
		if(form.getDepartmentPositionId() == null)
		{
			throw new ValidationException("Plotesoni Pozicionin e Punës!");
		}
		
		if(form.getPaymentCategoryId() == null)
		{
			throw new ValidationException("Plotësoni Kategorinë e Pagës!");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Plotësoni datën e fillimit të punës!");
		}
		
		Employee emp;
		boolean newEmp;
		
		Employee currentEmployee = crudDAO.findById(Employee.class, form.getNid());
		if(currentEmployee != null)
		{
			if(currentEmployee.getEndDate() == null)
			{
				throw new EntityExistsException("Punonjesi "+currentEmployee.getName()+" "+currentEmployee.getSurname()+" ndodhet aktualisht i punesuar");
			}
			
			emp = currentEmployee;
			newEmp = false;
		}
		else 
		{
			emp = new Employee();
			newEmp = true;
		}
		
		emp.setCitizenship(crudDAO.findById(State.class, form.getCitizenshipCode()));
		emp.setCivilStatus(form.getCivilStatus());
		emp.setDepartmentPosition(crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId()));
		emp.setDob(form.getDob());
		emp.setDossierNo(form.getDossierNo());
		emp.setEmployeeNo(form.getEmployeeNo());
		emp.setEndDate(null);
		emp.setFatherName(form.getFatherName());
		emp.setGender(form.getGender());
		emp.setMotherName(form.getMotherName());
		emp.setMaidenName(form.getMaidenName());
		emp.setName(form.getName());
		emp.setNationality(crudDAO.findById(State.class, form.getNationalityCode()));
		emp.setNid(form.getNid());
		emp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		emp.setPob(form.getPob());
		emp.setStartDate(form.getStartDate());
		emp.setSurname(form.getSurname());
		emp.setUpdateTime(Calendar.getInstance().getTime());
		emp.setUpdateUser(uname);
		
		
		if(newEmp) 
		{
			emp.setCreateTime(Calendar.getInstance().getTime());
			emp.setCreateUser(uname);
			emp = crudDAO.create(emp);
		}
		else
		{
			emp = crudDAO.update(emp);
		}
		
		EmployeeHistory history = new EmployeeHistory();
		history.setCreateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		history.setDepartmentPosition(emp.getDepartmentPosition());
		history.setDossierNo(emp.getDossierNo());
		history.setEmployee(emp);
		history.setEmployeeNo(emp.getEmployeeNo());
		history.setEndDate(null);
		history.setPaymentCategory(emp.getPaymentCategory());
		history.setStartDate(emp.getStartDate());
		history.setStatus(IStatus.ACTIVE);
		history.setUpdateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		
		crudDAO.create(history);
		
		
		return emp;
		
	}
	
	@Transactional
	public Employee changeEmployeePosition(EmployeeForm form, String uname)
	{
		
		if(form == null)
		{
			throw new ValidationException("Forma e pa plotësuar!");
		}
		
		if(!StringUtil.isValid(form.getNid()))
		{
			throw new ValidationException("Plotësoni Numrin Personal!");
		}
		
		if(form.getDepartmentPositionId() == null)
		{
			throw new ValidationException("Plotësoni Pozicionin e Punës!");
		}
		
		if(form.getPaymentCategoryId() == null)
		{
			throw new ValidationException("Plotësoni Kategorinë e Pagës!");
		}
		
		if(form.getEndDate() == null)
		{
			throw new ValidationException("Plotësoni datën e lënies së pozicionit të mëparshëm!");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Plotësoni datën e fillimit të punës!");
		}
		
		Employee emp = crudDAO.findById(Employee.class, form.getNid());		
		emp.setDepartmentPosition(crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId()));
		emp.setEndDate(null);
		emp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		emp.setStartDate(form.getStartDate());
		emp.setUpdateTime(Calendar.getInstance().getTime());
		emp.setUpdateUser(uname);
		
		EmployeeHistory lastEmployment = employeeDAO.getEmployeeLastEmployment(emp.getNid());
		lastEmployment.setUpdateTime(Calendar.getInstance().getTime());
		lastEmployment.setUpdateUser(uname);
		lastEmployment.setEndDate(form.getEndDate());
		crudDAO.update(lastEmployment);
		
		EmployeeHistory history = new EmployeeHistory();
		history.setCreateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		history.setDepartmentPosition(emp.getDepartmentPosition());
		history.setDossierNo(emp.getDossierNo());
		history.setEmployee(emp);
		history.setEmployeeNo(emp.getEmployeeNo());
		history.setEndDate(null);
		history.setPaymentCategory(emp.getPaymentCategory());
		history.setStartDate(emp.getStartDate());
		history.setStatus(IStatus.ACTIVE);
		history.setUpdateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		crudDAO.create(history);
			
		
		return emp;
		
	}
	
	@Transactional
	public Employee updateEmployeeGeneralities(EmployeeForm form, String uname)
	{
		if(form == null)
		{
			throw new ValidationException("Forma e pa plotësuar!");
		}
		
		if(!StringUtil.isValid(form.getNid()))
		{
			throw new ValidationException("Plotësoni Numrin Personal!");
		}
		
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Plotësoni Emrin!");
		}
		
		if(!StringUtil.isValid(form.getSurname()))
		{
			throw new ValidationException("Plotësoni Mbiemrin!");
		}
		
		if(form.getDob() == null)
		{
			throw new ValidationException("Plotësoni Datëlindjen!");
		}
		
		if(!StringUtil.isValid(form.getGender()))
		{
			throw new ValidationException("Plotësoni Gjininë!");
		}
		
		if(!StringUtil.isValid(form.getEmployeeNo()))
		{
			throw new ValidationException("Plotësoni Numrin e Punonjesit!");
		}
		
		if(!StringUtil.isValid(form.getDossierNo()))
		{
			throw new ValidationException("Plotësoni Numrin e Dosjes!");
		}
		
		
		Employee emp = crudDAO.findById(Employee.class, form.getNid());	
		
		emp.setCitizenship(crudDAO.findById(State.class, form.getCitizenshipCode()));
		emp.setCivilStatus(form.getCivilStatus());
		emp.setDob(form.getDob());
		emp.setDossierNo(form.getDossierNo());
		emp.setEmployeeNo(form.getEmployeeNo());
		emp.setFatherName(form.getFatherName());
		emp.setGender(form.getGender());
		emp.setMotherName(form.getMotherName());
		emp.setMaidenName(form.getMaidenName());
		emp.setName(form.getName());
		emp.setNationality(crudDAO.findById(State.class, form.getNationalityCode()));
		emp.setNid(form.getNid());
		emp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		emp.setPob(form.getPob());
	//	emp.setStartDate(form.getStartDate());
		emp.setSurname(form.getSurname());
		emp.setUpdateTime(Calendar.getInstance().getTime());
		emp.setUpdateUser(uname);
		
		
		return crudDAO.update(emp);
		
		
		
	}
	
	public List<Employee> searchEmployee(EmployeeSx sx, String uname)
	{
		return employeeDAO.searchEmployee(sx);
	}
	
	public List<EmployeeHistory> getEmployeeHistory(String nid, String uname)
	{
		return employeeDAO.getEmployeeHistory(nid);
	}
	
	public Employee getEmployeeByNid(String nid, String uname)
	{
		return employeeDAO.findEmployeeByNid(nid);
	}
	
	
	

}
