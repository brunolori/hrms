package com.bvlsh.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.entities.Bank;
import com.bvlsh.hr.entities.ContactType;
import com.bvlsh.hr.entities.DepartmentCategory;
import com.bvlsh.hr.entities.EducationType;
import com.bvlsh.hr.entities.ForeignLanguage;
import com.bvlsh.hr.entities.Grade;
import com.bvlsh.hr.entities.Institution;
import com.bvlsh.hr.entities.JobEndingReason;
import com.bvlsh.hr.entities.PaymentCategory;
import com.bvlsh.hr.entities.Position;
import com.bvlsh.hr.entities.ProvisionType;
import com.bvlsh.hr.entities.Role;
import com.bvlsh.hr.entities.State;
import com.bvlsh.hr.entities.StudyField;
import com.bvlsh.hr.entities.TrainingType;
import com.bvlsh.hr.entities.ValidationType;


@Repository
@SuppressWarnings("unchecked")
public class CrudDAO {
	
	
	@PersistenceContext
	EntityManager em;
	
	public <T> T create(T entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

	public <T> T update(T entity) {
		return em.merge(entity);
	}

	public <T> T findById(Class<T> claxx, Object id) {
		return em.find(claxx, id);
	}
	
	
	public List<ProvisionType> loadProvisionTypes() {

		return em.createQuery("FROM ProvisionType pt WHERE pt.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}

	public List<JobEndingReason> loadJobEndingReasons() {

		return em.createQuery("FROM JobEndingReason je WHERE je.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<TrainingType> loadTrainingTypes(){
		
		return em.createQuery("FROM TrainingType tt WHERE tt.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<ValidationType> loadValidationTypes(){
		
		return em.createQuery("FROM ValidationType vt WHERE vt.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<ForeignLanguage> loadForeignLanguages(){
		
		return em.createQuery("FROM ForeignLanguage fl WHERE fl.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<StudyField> loadStudyFields(){
		
		return em.createQuery("FROM StudyField sf WHERE sf.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<EducationType> loadEducationTypes(){
		
		return em.createQuery("FROM EducationType et WHERE et.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}

	
	public List<Institution> loadInstitutions(){
		
		return em.createQuery("FROM Institution i WHERE i.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
	
    public List<ContactType> loadContactTypes(){
		
		return em.createQuery("FROM ContactType ct WHERE ct.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
    
    public List<Position> loadPositions(){
		
		return em.createQuery("FROM Position p WHERE p.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
    
   public List<Bank> loadBanks(){
		
		return em.createQuery("FROM Bank b WHERE b.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
   
   
   public List<PaymentCategory> loadPaymentCategories(){
		
		return em.createQuery("FROM PaymentCategory pc WHERE pc.status=:status").setParameter("status", IStatus.ACTIVE)
				.getResultList();
	}
   
   
   public List<DepartmentCategory> loadDepartmentCategories(){
	   
	   return em.createQuery("FROM DepartmentCategory dc WHERE dc.status=:status").setParameter("status", IStatus.ACTIVE)
			   .getResultList();
   }
   
   public List<State> loadStates(){
	   
	   return em.createQuery("FROM State s ORDER BY s.name")
			   .getResultList();
   }
   
   public List<Role> loadRoles(){
	   
	   return em.createQuery("FROM Role r WHERE r.status=:status").setParameter("status", IStatus.ACTIVE)
			   .getResultList();
   }

public List<Grade> loadGrades() {
	return em.createQuery("FROM Grade r WHERE r.status=:status order by r.level").setParameter("status", IStatus.ACTIVE)
			   .getResultList();
}
   
}
