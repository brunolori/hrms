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
import com.bvlsh.hr.entities.Education;
import com.bvlsh.hr.forms.EducationSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class EducationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<Education> searchEducations(EducationSx sx, List<Integer> deptIds) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM Education ed WHERE ed.status=:st ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND ed.employee.departmentPosition.department.id IN :deptIds ";
			params.put("deptIds", deptIds);
		}

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
			sql += "AND UPPER(ed.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(ad.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND ed.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND ed.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if (sx.getEducationTypeId() != null) {
			sql += "AND ed.educationType.id=:pt_id ";
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

		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
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
	
	public List<Education> getEmployeeEducations(String nid) {
		return em.createQuery("FROM Education ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.issueDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}

}
