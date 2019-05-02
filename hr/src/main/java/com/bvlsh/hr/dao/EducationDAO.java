package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.bvlsh.hr.entities.Education;
import com.bvlsh.hr.forms.EducationSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class EducationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<Education> searchEducations(EducationSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM Education ed WHERE 1=1 ";

		
		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND ed.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND ed.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(e.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(e.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND ed.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if (sx.getEducationTypeId() != null) {
			sql += "AND ed.provisionType.id=:pt_id ";
			params.put("pt_id", sx.getEducationTypeId());
		}
		
		if (sx.getInstitutionId() != null) {
			sql += "AND ed.institution.id=:inst_id ";
			params.put("inst_id", sx.getEducationTypeId());
		}
		
		if (sx.getStudyFieldId() != null) {
			sql += "AND ed.studyField.id=:stf_id ";
			params.put("stf_id", sx.getStudyFieldId());
		}
		
		if (sx.getFromDate() != null) {
			sql += "AND ed.issueDate >=:start_fr ";
			params.put("start_fr", sx.getFromDate());
		}
			
		if (sx.getToDate() != null) {
				sql += "AND ed.issueDate <=:end_to ";
				params.put("end_to", sx.getToDate());
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
	
	

}
