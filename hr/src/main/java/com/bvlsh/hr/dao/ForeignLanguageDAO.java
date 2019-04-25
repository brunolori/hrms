package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.bvlsh.hr.entities.EmployeeForeignLanguage;
import com.bvlsh.hr.forms.ForeignLanguageSx;

@Repository
@SuppressWarnings("unchecked")
public class ForeignLanguageDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("rawtypes")
	public List<EmployeeForeignLanguage> searchForeignLanguages(ForeignLanguageSx sx) {

		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM EmployeeForeignLanguage efl WHERE 1=1 ";
		
		if(sx.getForeignLanguageId() != null)
		{
			sql += "AND efl.foreignLanguage.id=:fl_id ";
			params.put("fl_id", sx.getForeignLanguageId());
		}
	
		Query q = em.createQuery(sql);
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
	
	

}
