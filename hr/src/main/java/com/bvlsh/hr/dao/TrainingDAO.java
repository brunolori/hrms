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
import com.bvlsh.hr.entities.Training;
import com.bvlsh.hr.forms.TrainingSx;


@Repository
@SuppressWarnings("unchecked")
public class TrainingDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<Training> searchTrainings(TrainingSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM Training t WHERE 1=1 ";

		if (sx.getTrainingDate() != null) {
			sql += "AND t.trainingDate=:training_dt ";
			params.put("training_dt", sx.getTrainingDate());
		}

		if (sx.getInstitutionId() != null) {
			sql += "AND t.institution.id=:inst_id ";
			params.put("inst_id", sx.getInstitutionId());
		}
		
		if (sx.getTrainingTypeId() != null) {
			sql += "AND t.trainingType.id=:tt_id ";
			params.put("tt_id", sx.getTrainingTypeId());
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
	

	public List<Training> getEmployeeTrainings(String nid) {
		return em.createQuery("FROM Training ap WHERE ap.status=:st AND ap.employee.nid=:nid ORDER BY ap.trainingDate DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid.replace(" ", "").toUpperCase())
				.getResultList();
	}
	

}
