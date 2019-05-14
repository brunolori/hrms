package com.bvlsh.hr.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.entities.User;


@Repository
@SuppressWarnings("unchecked")
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	
	@SuppressWarnings("rawtypes")
	public List<User> searchUsers(List<Integer> deptIds) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM User u WHERE 1=1 ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND u.rootDepartment.id IN :deptIds ";
			params.put("deptIds", deptIds);
		}

		Query q = em.createQuery(sql);
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			q.setParameter((String) pair.getKey(), pair.getValue());
			it.remove();
		}
		
		return q.getResultList();

	}

}
