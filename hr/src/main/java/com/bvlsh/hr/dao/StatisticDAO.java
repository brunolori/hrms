package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.models.KeyValue;

@Repository
@SuppressWarnings("unchecked")
public class StatisticDAO {
	
	@PersistenceContext
	EntityManager em;

	
	
	public Long departmentsCount()
	{
		return (Long)em.createQuery("SELECT COUNT(d) FROM Department d WHERE d.status=:st")
				.setParameter("st", IStatus.ACTIVE)
				.getSingleResult();
	}
	
	public Long positionsCount()
	{
		return (Long)em.createQuery("SELECT COUNT(p) FROM DepartmentPosition p WHERE p.status=:st")
				.setParameter("st", IStatus.ACTIVE)
				.getSingleResult();
	}
	
	public Long freePositionsCount()
	{
		return (Long)em.createQuery("SELECT COUNT(p) FROM DepartmentPosition p WHERE p.status=:st AND p.currentEmployee IS NULL")
				.setParameter("st", IStatus.ACTIVE)
				.getSingleResult();
	}
	
	public Long employeesCount()
	{
		return (Long)em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.endDate IS NULL")
				.getSingleResult();
	}
	
	public Long employeesCountByGender(String gender)
	{
		return (Long)em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.endDate IS NULL AND e.gender=:g")
				.setParameter("g", gender.toUpperCase())
				.getSingleResult();
	}
	
	
	
	public List<KeyValue> employeesByStudyField()
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(e.studyField.tag, COUNT(e) AS cnt) FROM Education e WHERE e.status=:st "
				+ "AND e.employee.endDate IS NULL GROUP BY e.studyField.tag ORDER BY cnt DESC")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<KeyValue> employeesByGrade()
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(e.grade.tag, COUNT(e) AS cnt) FROM Employee e WHERE "
				+ "e.endDate IS NULL GROUP BY e.grade.tag ORDER BY cnt DESC")
				.getResultList();
	}
	
	
	public List<KeyValue> employeesByForeignLanguage()
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(efl.foreignLanguage.tag, COUNT(efl) AS cnt) FROM EmployeeForeignLanguage efl WHERE efl.status=:st "
				+ "AND efl.employee.endDate IS NULL GROUP BY efl.foreignLanguage.tag ORDER BY cnt DESC")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	
	

}
