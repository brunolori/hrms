package com.bvlsh.hr.dao;

import java.util.Date;
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
	
	//### GRAFIK Pie SIPAS GRUPIMIT
	
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
	
	public List<KeyValue> employeesByPaymentCategory()
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(e.paymentCategory.tag, COUNT(e) AS cnt) FROM Employee e WHERE "
				+ "e.endDate IS NULL GROUP BY e.paymentCategory.tag ORDER BY cnt DESC")
				.getResultList();
	}
	
	
	// ####### GRAFIK sipas DATES
	
	public List<KeyValue> employeesByJobEndingReason(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(e.endJobReason.tag, COUNT(e) AS cnt) FROM EmployeeHistory e WHERE e.status=:st"
				+ "e.endDate>=:st_date AND e.endDate>=:end_date GROUP BY e.paymentCategory.tag ORDER BY cnt DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	
	public List<KeyValue> employmentsByPeriod(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(eh.startDate, COUNT(eh) AS cnt) FROM EmployeeHistory eh WHERE eh.status=:st "
				+ "AND eh.startDate>=:st_date AND eh.startDate<=:end_date GROUP BY eh.startDate ORDER BY eh.startDate")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	
	public List<KeyValue> jobEndingsByPeriod(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(eh.endDate, COUNT(eh) AS cnt) FROM EmployeeHistory eh WHERE eh.status=:st "
				+ "AND eh.endDate>=:st_date AND eh.endDate<=:end_date GROUP BY eh.endDate ORDER BY eh.endDate")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	
	public List<KeyValue> provisionsByPeriod(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(p.actDate, COUNT(p) AS cnt) FROM AdministrativeProvision p WHERE p.status=:st "
				+ "AND p.actDate>=:st_date AND p.actDate<=:end_date GROUP BY p.actDate ORDER BY p.actDate")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	
	public List<KeyValue> validationsByPeriod(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(v.validationDate, COUNT(v) AS cnt) FROM JobValidation v WHERE v.status=:st "
				+ "AND v.validationDate>=:st_date AND v.validationDate<=:end_date GROUP BY v.validationDate ORDER BY v.validationDate")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	
	public List<KeyValue> trainingsByPeriod(Date from, Date to)
	{
		return em.createQuery("SELECT new "+KeyValue.class.getName()+"(t.trainingDate, COUNT(t) AS cnt) FROM Training t WHERE t.status=:st "
				+ "AND t.trainingDate>=:st_date AND t.trainingDate<=:end_date GROUP BY t.trainingDate ORDER BY t.trainingDate")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to)
				.getResultList();
	}
	

}
