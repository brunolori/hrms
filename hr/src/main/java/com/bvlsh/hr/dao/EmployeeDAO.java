package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.EmployeeHistory;
import com.bvlsh.hr.forms.EmployeeSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;

	public Employee findEmployeeByNid(String nid) {
		
		return em.find(Employee.class, nid);
		
	}

	
	public EmployeeHistory getEmployeeLastEmployment(String nid) {
		List<EmployeeHistory> history = em.createQuery("FROM EmployeeHistory eh WHERE eh.status=:st AND eh.employee.nid=:nid AND eh.endDate is null ORDER BY eh.startDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.toUpperCase().replace(" ", ""))
				.getResultList();
		
		if(history != null && !history.isEmpty())
		{
			return history.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<Employee> searchEmployee(EmployeeSx sx) {

		
		
		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM Employee e WHERE 1=1 ";
		
		
		if(StringUtil.isValid(sx.getCivilStatus()))
		{
			sql += "AND e.civilStatus=:cv_st ";
			params.put("cv_st", sx.getCivilStatus().toUpperCase());
		}
		
		if(StringUtil.isValid(sx.getDossierNo()))
		{
			sql += "AND e.dossierNo=:dos_no ";
			params.put("doc_no", sx.getDossierNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND e.employeeNo=:emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getFatherName()))
		{
			sql += "AND UPPER(e.fatherName) like :fth_nm ";
			params.put("fth_nm", sx.getFatherName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND e.gender :gnd ";
			params.put("gnd", sx.getGender().toUpperCase());
		}
		
		if(StringUtil.isValid(sx.getMaidenName()))
		{
			sql += "AND UPPER(e.maidenName) like :maid_nm ";
			params.put("maid_nm", sx.getMaidenName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getMotherName()))
		{
			sql += "AND UPPER(e.motherName) like :mth_nm ";
			params.put("mth_nm", sx.getMotherName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(e.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND UPPER(e.nid) like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(e.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDob() != null)
		{
			sql += "AND e.dob=:dob ";
			params.put("dob", sx.getDob());
		}
		
		if(sx.getDepartmentId() != null)
		{
			if(sx.getChildDepartments() != null && sx.getChildDepartments())
			{
				// get chils dept, set list
			}
			else {
			sql += "AND e.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
			}
		}
		
		if(sx.getPositionId() != null)
		{
			sql += "AND e.departmentPosition.position.id=:pos_id ";
			params.put("pos_id", sx.getPositionId());
		}
		
		if(sx.getPaymentCategoryId() != null)
		{
			sql += "AND e.paymentCategory.id=:payc_id ";
			params.put("payc_id", sx.getPaymentCategoryId());
		}
		
		if(sx.getEndJobReasonId() != null)
		{
			sql += "AND e.endJobReason.id=:ejr_id ";
			params.put("ejr_id", sx.getEndJobReasonId());
		}
		
		if(sx.getStartDateFrom() != null)
		{
			sql += "AND e.startDate >=:start_fr ";
			params.put("start_fr", sx.getStartDateFrom());
		}
		if(sx.getStartDateTo() != null)
		{
			sql += "AND e.startDate <=:start_to ";
			params.put("start_to", sx.getStartDateTo());
		}
		
		if(sx.getOnlyActive() != null && sx.getOnlyActive())
		{
			sql += "AND e.endDate IS NULL ";
		}
		
		else
		{
			if(sx.getEndDateFrom() != null)
			{
				sql += "AND e.endDate >=:end_fr ";
				params.put("end_fr", sx.getEndDateFrom());
			}
			if(sx.getEndDateTo() != null)
			{
				sql += "AND e.endDate <=:end_to ";
				params.put("end_to", sx.getEndDateTo());
			}
		}
				
		
		Query q = em.createQuery(sql);
		Iterator it = params.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			q.setParameter((String) pair.getKey(), pair.getValue());
			it.remove();
		}
		
		
		if(sx.getFirstResult() != null)
		{
			q.setFirstResult(sx.getFirstResult());
		}
		
		if(sx.getMaxResult() != null)
		{
			q.setMaxResults(sx.getMaxResult());
		}
		
		
		
		return q.getResultList();
		
		
		
	}

	public List<EmployeeHistory> getEmployeeHistory(String nid) {
		return em.createQuery("FROM EmployeeHistory eh WHERE eh.status=:st AND eh.employee.nid=:nid ORDER BY eh.startDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.toUpperCase().replace(" ", ""))
				.getResultList();
	}

	
	


}
