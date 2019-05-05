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
import com.bvlsh.hr.entities.JobEndingReason;
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
		
		//pozicioni eshte i zene nga dikush tjeter
		DepartmentPosition newPos = crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId());
		if(newPos.getCurrentEmployee() != null)
		{
			if(!newPos.getCurrentEmployee().getEmployee().getNid().equalsIgnoreCase(form.getNid()))
			{
			       throw new ValidationException("Pozicioni eshte i zene nga "+newPos.getCurrentEmployee().getEmployee().getName()+" " 
		                             + newPos.getCurrentEmployee().getEmployee().getSurname());
			}
		}
		
		Employee emp;
		boolean newEmp;
		
		Employee currentEmployee = crudDAO.findById(Employee.class, form.getNid());
		//punonjesi ekziston ne sistem
		if(currentEmployee != null)
		{
			if(currentEmployee.getEndDate() == null)//punonjesi eshte aktiv
			{
				if(form.getEndDate() == null || form.getEndJobReasonId() == null)
				{
				   throw new EntityExistsException("Punonjesi "+currentEmployee.getName()+" "+currentEmployee.getSurname()
				   +" ndodhet aktualisht i punesuar ne "+currentEmployee.getDepartmentPosition().getName()+", potesoni daten dhe arsyen e lenies se pozicionit te meparshem");
				}
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
		emp.setDepartmentPosition(newPos);
		emp.setDob(form.getDob());
		emp.setDossierNo(form.getDossierNo());
		emp.setEmployeeNo(form.getEmployeeNo());
		emp.setEndDate(null);
		emp.setEndJobReason(null);
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
		else// punonjes egzistues
		{
			emp = crudDAO.update(emp);
			EmployeeHistory h = employeeDAO.getEmployeeLastEmployment(emp.getNid());
			//mbyll punesimin e fundit dhe liro pozicionin
			if(h.getEndDate() == null)
			{
				h.setEndDate(form.getEndDate());
				h.setEndJobReason(crudDAO.findById(JobEndingReason.class, form.getEndJobReasonId()));
				h.setUpdateTime(Calendar.getInstance().getTime());
				h.setUpdateUser(uname);
				crudDAO.update(h);
				DepartmentPosition pos = h.getDepartmentPosition();
				pos.setCurrentEmployee(null);;
				crudDAO.update(pos);
			}
			
		}
		//krijo punesimin e ri
		EmployeeHistory history = new EmployeeHistory();
		history.setCreateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		history.setDepartmentPosition(emp.getDepartmentPosition());
		history.setDossierNo(emp.getDossierNo());
		history.setEmployee(emp);
		history.setEmployeeNo(emp.getEmployeeNo());
		history.setEndDate(null);
		history.setEndJobReason(null);
		history.setPaymentCategory(emp.getPaymentCategory());
		history.setStartDate(emp.getStartDate());
		history.setStatus(IStatus.ACTIVE);
		history.setUpdateTime(Calendar.getInstance().getTime());
		history.setUpdateUser(uname);
		
		history = crudDAO.create(history);
		
		newPos.setCurrentEmployee(history);
		crudDAO.update(newPos);
		
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
		
		
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Plotësoni datën e fillimit të punës!");
		}
		
		
		DepartmentPosition pos = crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId());
		Employee emp = crudDAO.findById(Employee.class, form.getNid());	
		
		if(emp.getEndDate() == null)//eshte aktiv
		{
			if(form.getEndDate() == null || form.getEndJobReasonId() == null)
			{
				throw new ValidationException("Plotësoni arsyen dhe daten e lënies së pozicionit të mëparshëm!");
			}
			//liro pozicionin e fundit
			DepartmentPosition lastPosition = emp.getDepartmentPosition();
			lastPosition.setCurrentEmployee(null);
			crudDAO.update(lastPosition);
			//mbyll punesimin e fundit
			EmployeeHistory lastEmployment = employeeDAO.getEmployeeLastEmployment(emp.getNid());
			lastEmployment.setUpdateTime(Calendar.getInstance().getTime());
			lastEmployment.setUpdateUser(uname);
			lastEmployment.setEndDate(form.getEndDate());
			lastEmployment.setEndJobReason(crudDAO.findById(JobEndingReason.class, form.getEndJobReasonId()));
			crudDAO.update(lastEmployment);
		}
		
		//updeto employee me vlerat e reja
		emp.setDepartmentPosition(pos);
		emp.setEndDate(null);
		emp.setEndJobReason(null);
		emp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		emp.setStartDate(form.getStartDate());
		emp.setUpdateTime(Calendar.getInstance().getTime());
		emp.setUpdateUser(uname);
		emp = crudDAO.update(emp);
		
		//krijo historik te ri
		EmployeeHistory history = new EmployeeHistory();
		history.setCreateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		history.setDepartmentPosition(emp.getDepartmentPosition());
		history.setDossierNo(emp.getDossierNo());
		history.setEmployee(emp);
		history.setEmployeeNo(emp.getEmployeeNo());
		history.setEndDate(null);
		history.setEndJobReason(null);
		history.setPaymentCategory(emp.getPaymentCategory());
		history.setStartDate(emp.getStartDate());
		history.setStatus(IStatus.ACTIVE);
		history.setUpdateTime(Calendar.getInstance().getTime());
		history.setCreateUser(uname);
		history = crudDAO.create(history);
		
		//kontrollo nese pozicioni i ri eshte bosh
		if(pos.getCurrentEmployee() != null && (!pos.getCurrentEmployee().getEmployee().getNid().equalsIgnoreCase(emp.getNid())))
		{
			throw new ValidationException("Pozicioni i punes eshte i zene nga "+pos.getCurrentEmployee().getEmployee().getName()
					+" "+pos.getCurrentEmployee().getEmployee().getSurname());
		}
		
		//updeto pozicionin e ri me punonjesin aktual
		pos.setCurrentEmployee(history);
		crudDAO.update(pos);
		
		return emp;
		
	}
	
	@Transactional
	public EmployeeHistory updateEmployment(EmployeeForm form, String uname)
	{
		
		if(form == null)
		{
			throw new ValidationException("Forma e pa plotësuar!");
		}
		
		if(form.getEmploymentId() == null)
		{
			throw new ValidationException("Punesimi i pa percaktuar!");
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
		
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Plotësoni datën e fillimit të punës!");
		}
		
		Employee emp = crudDAO.findById(Employee.class, form.getNid());				
		EmployeeHistory lastEmployment = employeeDAO.getEmployeeLastEmployment(emp.getNid());		
		EmployeeHistory history = crudDAO.findById(EmployeeHistory.class, form.getEmploymentId());
		
		if(lastEmployment != null)
		{
			//dmth po modifikon nje punesim te meparshem dhe kontrrollo daten
			if(lastEmployment.getId() != history.getId())
			{
				throw new ValidationException("Punësimi i mëparshëm nuk mund të ndryshohet!");
			}
		}
		
		
		// #### po modifikon punesimin e fundit ######
		
		if(form.getEndDate() != null)// dmth po i jep fund punesimit dhe updeto employee dhe organigram position
		{
			if(form.getEndJobReasonId() == null) // nuk ka plotesuar arsyen e lirimit
			{
				throw new ValidationException("Ploteso arsyen e lirimit ose hiq daten e lirimit");
			}
				DepartmentPosition dp = emp.getDepartmentPosition();
				dp.setCurrentEmployee(null);
				crudDAO.update(dp);
		}
				
		DepartmentPosition pos = crudDAO.findById(DepartmentPosition.class, form.getDepartmentPositionId());
		
		if(pos.getCurrentEmployee() != null) // posicioni eshte i zene nga dikush tjeter
		{
			if(pos.getCurrentEmployee().getId() != history.getId())
			{
				throw new ValidationException("Pozicioni eshte i zene nga "+pos.getCurrentEmployee().getEmployee().getName()
						+" "+pos.getCurrentEmployee().getEmployee().getSurname());
				
			}
		}
		
		PaymentCategory pc = crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId());
		
		JobEndingReason jer = null;
		if(form.getEndJobReasonId() != null)
		{
			if(form.getEndDate() == null)
			{
				throw new ValidationException("Ploteso daten e lirimit ose hiq arsyen e lirimit");
			}
			jer = crudDAO.findById(JobEndingReason.class, form.getEndJobReasonId());
		}
		
		emp.setEndDate(form.getEndDate());
		emp.setEndJobReason(jer);
		emp.setDepartmentPosition(pos);
		emp.setPaymentCategory(pc);
		emp.setStartDate(form.getStartDate());
		emp.setUpdateTime(Calendar.getInstance().getTime());
		emp.setUpdateUser(uname);
		emp = crudDAO.update(emp);
		
		history.setDepartmentPosition(pos);
		history.setEndDate(form.getEndDate());
		history.setEndJobReason(jer);
		history.setPaymentCategory(pc);
		history.setStartDate(form.getStartDate());
		history.setUpdateTime(Calendar.getInstance().getTime());
		history.setUpdateUser(uname);
		history = crudDAO.update(history);
		
		pos.setCurrentEmployee(history);
		pos.setUpdateTime(Calendar.getInstance().getTime());
		pos.setUpdateUser(uname);
		crudDAO.update(pos);
		
		return history;
					
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
		//emp.setDossierNo(form.getDossierNo());
		//emp.setEmployeeNo(form.getEmployeeNo());
		emp.setFatherName(form.getFatherName());
		emp.setGender(form.getGender());
		emp.setMotherName(form.getMotherName());
		emp.setMaidenName(form.getMaidenName());
		emp.setName(form.getName());
		emp.setNationality(crudDAO.findById(State.class, form.getNationalityCode()));
		emp.setNid(form.getNid());
		emp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		emp.setPob(form.getPob());
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
