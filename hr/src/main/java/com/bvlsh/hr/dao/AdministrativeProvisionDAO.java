package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;
import com.bvlsh.hr.utils.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class AdministrativeProvisionDAO {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("rawtypes")
	public List<AdministrativeProvision> searchProvisions(AdministrativeProvisionSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM AdministrativeProvision ap WHERE 1=1 ";

		if (StringUtil.isValid(sx.getActNo())) {
			sql += "AND ap.actNo=:act_no ";
			params.put("act_no", sx.getActNo().toUpperCase().replace(" ", ""));
		}
		
		if (sx.getActDate() != null) {
			sql += "AND ap.actDate >=:act_dt ";
			params.put("act_dt", sx.getActDate());
		}

		if (sx.getProvisionTypeId() != null) {
			sql += "AND ap.provisionType.id=:pt_id ";
			params.put("pt_id", sx.getProvisionTypeId());
		}

		if (sx.getStartDateFrom() != null) {
			sql += "AND ap.startDate >=:start_fr ";
			params.put("start_fr", sx.getStartDateFrom());
		}
			
		if (sx.getEndDateTo() != null) {
				sql += "AND ap.startDate <=:end_to ";
				params.put("end_to", sx.getEndDateTo());
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
