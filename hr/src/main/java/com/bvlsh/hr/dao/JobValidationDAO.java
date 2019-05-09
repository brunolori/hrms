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
import com.bvlsh.hr.entities.JobValidation;
import com.bvlsh.hr.forms.JobValidationSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class JobValidationDAO {
	
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("rawtypes")
	public List<JobValidation> searchJobValidations(JobValidationSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM JobValidation jv WHERE jv.status=:st ";
		
		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND jv.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND jv.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(jv.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(jv.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND jv.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if (sx.getValidationDate() != null) {
			sql += "AND jv.validationDate=:val_dt ";
			params.put("val_dt", sx.getValidationDate());
		}

		if (sx.getValidationTypeId()!= null) {
			sql += "AND jv.validationType.id=:vt_id ";
			params.put("vt_id", sx.getValidationTypeId());
		}
		
	
		if (sx.getFromDate() != null) {
			sql += "AND ap.validationDate>=:start_fr ";
			params.put("start_fr", sx.getFromDate());
		}
			
		if (sx.getToDate() != null) {
				sql += "AND ap.validationDate<=:end_to ";
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

	public List<JobValidation> getEmployeeValidations(String nid) {
		return em.createQuery("FROM JobValidation ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.validationDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}

}
