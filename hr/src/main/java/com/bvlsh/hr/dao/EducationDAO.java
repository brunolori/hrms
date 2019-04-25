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

@Repository
@SuppressWarnings("unchecked")
public class EducationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<Education> searchEducations(EducationSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM Education e WHERE 1=1 ";

		if (sx.getIssueDate() != null) {
			sql += "AND e.issueDate=:issue_dt ";
			params.put("issue_dt", sx.getIssueDate());
		}

		if (sx.getEducationTypeId() != null) {
			sql += "AND e.provisionType.id=:pt_id ";
			params.put("pt_id", sx.getEducationTypeId());
		}
		
		if (sx.getInstitutionId() != null) {
			sql += "AND e.institution.id=:inst_id ";
			params.put("inst_id", sx.getEducationTypeId());
		}
		
		if (sx.getStudyFieldId() != null) {
			sql += "AND e.studyField.id=:stf_id ";
			params.put("stf_id", sx.getStudyFieldId());
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
