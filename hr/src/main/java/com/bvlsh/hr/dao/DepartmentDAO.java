package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Department;
import com.bvlsh.hr.entities.DepartmentPosition;


@Repository
@SuppressWarnings("unchecked")
public class DepartmentDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	public Department getRootDepartment()
	{
		
		List<Department> list = em.createQuery("FROM Department d WHERE d.status=:st AND d.parent IS NULL")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	public List<Department> getChildDepartments(Integer deptId)
	{
		return em.createQuery("FROM Department d WHERE d.status=:st AND d.parent.id=:did")
				.setParameter("st", IStatus.ACTIVE).setParameter("did", deptId)
				.getResultList();
	}
	
	public List<DepartmentPosition> getDepartmentPositions(Integer deptId)
	{
		return em.createQuery("FROM DepartmentPosition p WHERE p.status=:st AND p.department.id=:did")
				.setParameter("st", IStatus.ACTIVE).setParameter("did", deptId)
				.getResultList();
	}

	public List<Department> listDepartments(List<Integer> deptIds) {
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			return em.createQuery("FROM Department d WHERE d.status=:st AND d.id IN :deptIds ORDER BY d.name")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("deptIds", deptIds)
					.getResultList();
		}
		
		return em.createQuery("FROM Department d WHERE d.status=:st ORDER BY d.name")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	
	
	
	
	
	
	
	
}
