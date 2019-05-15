package com.bvlsh.hr.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		return (Long)em.createQuery("SELECT COUNT(d) FROM Department d WHERE d.status=:st ")
				.setParameter("st", IStatus.ACTIVE)
				.getSingleResult();
	}
	
	public Long positionsCount(List<Integer> deptIds)
	{
		String sql = "SELECT COUNT(p) FROM DepartmentPosition p WHERE p.status=:st ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND p.department.id IN :deptIds ";
		}
		
		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return (Long)q.getSingleResult();
	}
	
	public Long freePositionsCount(List<Integer> deptIds)
	{
		String sql = "SELECT COUNT(p) FROM DepartmentPosition p WHERE p.status=:st AND p.currentEmployee IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND p.department.id IN :deptIds ";
		}
		
		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return (Long)q.getSingleResult();
	
	
	}
	
	public Long employeesCount(List<Integer> deptIds)
	{
		String sql = "SELECT COUNT(e) FROM Employee e WHERE e.endDate IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND e.departmentPosition.department.id IN :deptIds ";
		}
		
		Query q = em.createQuery(sql);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return (Long)q.getSingleResult();
	}
	
	public Long employeesCountByGender(String gender, List<Integer> deptIds)
	{
		String sql = "SELECT COUNT(e) FROM Employee e WHERE e.endDate IS NULL AND e.gender=:g ";
		
				if(deptIds != null && !deptIds.isEmpty())
				{
					sql += "AND e.departmentPosition.department.id IN :deptIds ";
				}
		
				Query q = em.createQuery(sql).setParameter("g", gender.trim().toUpperCase());
				
				if(deptIds != null && !deptIds.isEmpty())
				{
					q.setParameter("deptIds", deptIds);
				}
				
				return (Long)q.getSingleResult();
	}
	
	//### GRAFIK Pie SIPAS GRUPIMIT
	
	public List<KeyValue> employeesByStudyField(List<Integer> deptIds)
	{
		
		String sql = "SELECT new "+KeyValue.class.getName()+"(e.studyField.tag, COUNT(e) AS cnt) FROM Education e WHERE e.status=:st "
				+ "AND e.employee.endDate IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND e.employee.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY e.studyField.tag ORDER BY cnt DESC";
		
		Query q = em.createQuery(sql+order).setParameter("st", IStatus.ACTIVE);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
	}
	
	public List<KeyValue> employeesByGrade(List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(e.grade.tag, COUNT(e) AS cnt) FROM Employee e WHERE "
				+ "e.endDate IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND e.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY e.grade.tag ORDER BY cnt DESC";
		
		Query q = em.createQuery(sql+order);
		
				if(deptIds != null && !deptIds.isEmpty())
				{
					q.setParameter("deptIds", deptIds);
				}
				
				return q.getResultList();
	}
	
	public List<KeyValue> employeesByForeignLanguage(List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(efl.foreignLanguage.tag, COUNT(efl) AS cnt) FROM EmployeeForeignLanguage efl WHERE efl.status=:st "
				+ "AND efl.employee.endDate IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND efl.employee.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = " GROUP BY efl.foreignLanguage.tag ORDER BY cnt DESC";
		
		Query q = em.createQuery(sql+order).setParameter("st",IStatus.ACTIVE);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
	}
	
	public List<KeyValue> employeesByPaymentCategory(List<Integer> deptIds)
	{
		String sql ="SELECT new "+KeyValue.class.getName()+"(e.paymentCategory.tag, COUNT(e) AS cnt) FROM Employee e WHERE "
				+ "e.endDate IS NULL ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND e.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY e.paymentCategory.tag ORDER BY cnt DESC";
           Query q = em.createQuery(sql+order);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
	}
	
	
	// ####### GRAFIK sipas DATES
	
	public List<KeyValue> employeesByJobEndingReason(Date from, Date to, List<Integer> deptIds)
	{
		
		String sql = "SELECT new "+KeyValue.class.getName()+"(e.endJobReason.tag, COUNT(e) AS cnt) FROM EmployeeHistory e WHERE e.status=:st "
				+ "AND e.endDate>=:st_date AND e.endDate<=:end_date ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND e.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY e.paymentCategory.tag ORDER BY cnt DESC ";
		
		Query q = em.createQuery( sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
				
	}
	
	public List<KeyValue> employmentsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(eh.startDate, COUNT(eh) AS cnt) FROM EmployeeHistory eh WHERE eh.status=:st "
				+ "AND eh.startDate>=:st_date AND eh.startDate<=:end_date ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND eh.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY eh.startDate ORDER BY eh.startDate ";
		Query q = em.createQuery( sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
				
	}
	
	public List<KeyValue> jobEndingsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(eh.endDate, COUNT(eh) AS cnt) FROM EmployeeHistory eh WHERE eh.status=:st "
	               + "AND eh.endDate>=:st_date AND eh.endDate<=:end_date "; 
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND eh.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY eh.endDate ORDER BY eh.endDate";
		
		Query q =  em.createQuery(sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
			
	}
	
	public List<KeyValue> provisionsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(p.actDate, COUNT(p) AS cnt) FROM AdministrativeProvision p WHERE p.status=:st "
				+ "AND p.actDate>=:st_date AND p.actDate<=:end_date ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND p.employee.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY p.actDate ORDER BY p.actDate";
		
		Query q = em.createQuery(sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
			
		
				
	}
	
	public List<KeyValue> validationsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+"(v.validationDate, COUNT(v) AS cnt) FROM JobValidation v WHERE v.status=:st "
				+ "AND v.validationDate>=:st_date AND v.validationDate<=:end_date ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND v.employee.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY v.validationDate ORDER BY v.validationDate";
		
		
		Query q = em.createQuery(sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
		

		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
				
	}
	
	public List<KeyValue> trainingsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		
		String sql = "SELECT new "+KeyValue.class.getName()+"(t.trainingDate, COUNT(t) AS cnt) FROM Training t WHERE t.status=:st "
				+ "AND t.trainingDate>=:st_date AND t.trainingDate<=:end_date ";
		
		if(deptIds != null && !deptIds.isEmpty())
		{
			sql += "AND t.employee.departmentPosition.department.id IN :deptIds ";
		}
		
		String order = "GROUP BY t.trainingDate ORDER BY t.trainingDate";
		
		Query q = em.createQuery(sql+order)
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("st_date", from)
				.setParameter("end_date", to);
			
		if(deptIds != null && !deptIds.isEmpty())
		{
			q.setParameter("deptIds", deptIds);
		}
		
		return q.getResultList();
	}
	

}
