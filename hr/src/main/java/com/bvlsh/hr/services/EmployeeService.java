package com.bvlsh.hr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.EmployeeDAO;
import com.bvlsh.hr.entities.DepartmentPosition;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.State;
import com.bvlsh.hr.exceptions.EntityExistsException;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.EmployeeForm;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class EmployeeService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired EmployeeDAO employeeDAO;
	
	
	
	public Employee registerEmployee(EmployeeForm form, String uname)
	{
		
		if(form == null)
		{
			throw new ValidationException("Forma e pa plotesuar");
		}
		
		if(!StringUtil.isValid(form.getNid()))
		{
			throw new ValidationException("Plotesoni Numrin Personal");
		}
		
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Plotesoni Emrin");
		}
		
		if(!StringUtil.isValid(form.getSurname()))
		{
			throw new ValidationException("Plotesoni Mbiemrin");
		}
		
		if(form.getDob() == null)
		{
			throw new ValidationException("Plotesoni Datelindjen");
		}
		
		if(!StringUtil.isValid(form.getGender()))
		{
			throw new ValidationException("Plotesoni Gjinine");
		}
		
		if(!StringUtil.isValid(form.getEmployeeNo()))
		{
			throw new ValidationException("Plotesoni Numrin e Punonjesit");
		}
		
		if(!StringUtil.isValid(form.getDossierNo()))
		{
			throw new ValidationException("Plotesoni Numrin e Dosjes");
		}
		
		if(form.getDepartmentPositionId() == null)
		{
			throw new ValidationException("Plotesoni Pozicionin e Punes");
		}
		
		if(form.getPaymentCategoryId() == null)
		{
			throw new ValidationException("Plotesoni Kategorine e Pages");
		}
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Plotesoni daten e fillimit te punes");
		}
		
		Employee emp;
		
		Employee currentEmployee = crudDAO.findById(Employee.class, form.getNid());
		if(currentEmployee != null)
		{
			if(currentEmployee.getEndDate() == null)
			{
				throw new EntityExistsException("Punonjesi "+currentEmployee.getName()+" "+currentEmployee.getSurname()+" ndodhet aktualisht i punesuar");
			}
			
			emp = currentEmployee;
		}
		else 
		{
			emp = new Employee();
		}
		
		emp.setCitizenship(crudDAO.findById(State.class, form.getCitizenshipCode()));
		emp.setCivilStatus(form.getCivilStatus());
		emp.setDepartmentPosition(crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId()));
		emp.setDob(form.getDob());
		emp.setDossierNo(form.getDossierNo());
		emp.setEmployeeNo(form.getEmployeeNo());
		
		
		
		return emp;
		
	}
	
	
	

}
