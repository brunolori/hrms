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

@Repository
@SuppressWarnings("unchecked")
public class GradeDAO {

	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<EmployeeGrade> searchGrades(GradeSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM EmployeeGrade g WHERE 1=1 ";

		if (sx.getGradeId() != null) {
			sql += "AND g.grade.id=:g_id ";
			params.put("g_id", sx.getGradeId());
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
