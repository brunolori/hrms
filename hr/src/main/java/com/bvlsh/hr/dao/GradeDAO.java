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
import com.bvlsh.hr.entities.EmployeeGrade;
import com.bvlsh.hr.forms.GradeSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class GradeDAO {

	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<EmployeeGrade> searchGrades(GradeSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM EmployeeGrade g WHERE 1=1 ";
		
		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND g.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND g.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(g.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(g.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND g.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND g.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}

		if (sx.getGradeId() != null) {
			sql += "AND g.grade.id=:g_id ";
			params.put("g_id", sx.getGradeId());
		}
		
		
		if (sx.getStartDate() != null) {
			sql += "AND g.startDate >=:start_fr ";
			params.put("start_fr", sx.getStartDate());
		}
			
		if (sx.getEndDate() != null) {
				sql += "AND g.endDate <=:end_to ";
				params.put("end_to", sx.getEndDate());
			}

		Query q = em.createQuery(sql);
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			q.setParameter((String) pair.getKey(), pair.getValue());
			it.remove();
		}

		if (sx.getFirstResult() != null) {
			q.setFirstResult(sx.getFirstResult());
		}

		if (sx.getMaxResult() != null) {
			q.setMaxResults(sx.getMaxResult());
		}

		return q.getResultList();

	}


	public List<EmployeeGrade> getEmployeeGrades(String nid) {
		return em.createQuery("FROM EmployeeGrade ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.startDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}

}
