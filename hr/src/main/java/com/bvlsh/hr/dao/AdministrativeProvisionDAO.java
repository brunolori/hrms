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
import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class AdministrativeProvisionDAO {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("rawtypes")
	public List<AdministrativeProvision> searchProvisions(AdministrativeProvisionSx sx, List<Integer> deptIds) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM AdministrativeProvision ap WHERE ap.status=:st ";

		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND ap.employee.departmentPosition.department.id IN :deptIds ";
			params.put("deptIds", deptIds);
		}
		
		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND ap.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND ap.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(ap.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(ap.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND ap.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND ap.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if (StringUtil.isValid(sx.getActNo())) {
			sql += "AND ap.actNo=:act_no ";
			params.put("act_no", sx.getActNo().toUpperCase().replace(" ", ""));
		}
		
		if (sx.getActDate() != null) {
			sql += "AND ap.actDate=:act_dt ";
			params.put("act_dt", sx.getActDate());
		}

		if (sx.getProvisionTypeId() != null) {
			sql += "AND ap.provisionType.id=:pt_id ";
			params.put("pt_id", sx.getProvisionTypeId());
		}

		if (sx.getFromDate() != null) {
			sql += "AND ap.startDate >=:start_fr "; // ose actDate
			params.put("start_fr", sx.getFromDate());
		}
			
		if (sx.getToDate() != null) {
				sql += "AND ap.startDate <=:end_to ";
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

	public List<AdministrativeProvision> getProvisionsByNid(String nid) {
		return em.createQuery("FROM AdministrativeProvision ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.startDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}

}
