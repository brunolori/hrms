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
import com.bvlsh.hr.entities.EmployeeForeignLanguage;
import com.bvlsh.hr.forms.ForeignLanguageSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class ForeignLanguageDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("rawtypes")
	public List<EmployeeForeignLanguage> searchForeignLanguages(ForeignLanguageSx sx, List<Integer> deptIds) {

		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM EmployeeForeignLanguage efl WHERE efl.status=:st ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND efl.employee.departmentPosition.department.id IN :deptIds ";
			params.put("deptIds", deptIds);
		}

		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND efl.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND efl.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(efl.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(efl.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND efl.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND efl.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if(sx.getForeignLanguageId() != null)
		{
			sql += "AND efl.foreignLanguage.id=:fl_id ";
			params.put("fl_id", sx.getForeignLanguageId());
		}
	
		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
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
	
	public List<EmployeeForeignLanguage> getEmployeeLanguages(String nid) {
		return em.createQuery("FROM EmployeeForeignLanguage ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}

}
