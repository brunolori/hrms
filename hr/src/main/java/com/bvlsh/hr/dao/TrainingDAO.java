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
import com.bvlsh.hr.utils.StringUtil;


@Repository
@SuppressWarnings("unchecked")
public class TrainingDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("rawtypes")
	public List<Training> searchTrainings(TrainingSx sx) {

		HashMap<String, Object> params = new HashMap<>();
		String sql = "FROM Training t WHERE t.status=:st ";

		if(StringUtil.isValid(sx.getEmployeeNo()))
		{
			sql += "AND t.employee.employeeNo like :emp_no ";
			params.put("emp_no", sx.getEmployeeNo().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getNid()))
		{
			sql += "AND t.employee.nid like :nid ";
			params.put("nid", sx.getNid().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getName()))
		{
			sql += "AND UPPER(t.employee.name) like :name ";
			params.put("name", sx.getName().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getSurname()))
		{
			sql += "AND UPPER(t.employee.surname) like :surname ";
			params.put("surname", sx.getSurname().toUpperCase().replace(" ", ""));
		}
		
		if(StringUtil.isValid(sx.getGender()))
		{
			sql += "AND t.employee.gender=:gender ";
			params.put("gender", sx.getGender().toUpperCase().replace(" ", ""));
		}
		
		if(sx.getDepartmentId() != null)
		{
			sql += "AND t.employee.departmentPosition.department.id=:dept_id ";
			params.put("dept_id", sx.getDepartmentId());
		}
		
		if (sx.getInstitutionId() != null) {
			sql += "AND t.institution.id=:inst_id ";
			params.put("inst_id", sx.getInstitutionId());
		}
		
		if (sx.getTrainingTypeId() != null) {
			sql += "AND t.trainingType.id=:tt_id ";
			params.put("tt_id", sx.getTrainingTypeId());
		}
		
		if (sx.getFromDate() != null) {
			sql += "AND t.trainingDate >=:start_fr ";
			params.put("start_fr", sx.getFromDate());
		}
			
		if (sx.getToDate() != null) {
				sql += "AND t.trainingDate <=:end_to ";
				params.put("end_to", sx.getToDate());
			}
		
		

		Query q = em.createQuery(sql).setParameter("st", IStatus.ACTIVE);
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
