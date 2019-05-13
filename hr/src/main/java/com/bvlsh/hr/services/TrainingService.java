package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.TrainingDAO;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.entities.Institution;
import com.bvlsh.hr.entities.Training;
import com.bvlsh.hr.entities.TrainingType;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.TrainingForm;
import com.bvlsh.hr.forms.TrainingSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class TrainingService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired TrainingDAO trainingDAO;

	public List<Training> searchTrainings(TrainingSx sx, List<Integer> deptIds, String uname) {
		return trainingDAO.searchTrainings(sx, deptIds);
	}

	
	@Transactional
	public Training registerTraining(TrainingForm form, String uname) {

		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(form.getTrainingTypeId() == null)
		{
			throw new ValidationException("Plotesoni tipin e trajnimit");
		}
		if(form.getTrainingDate() == null)
		{
			throw new ValidationException("Plotesoni daten e trajnimit");
		}
		if(!StringUtil.isValid(form.getResult()))
		{
			throw new ValidationException("Plotesoni rezultatin e trajnimit");
		}
		
		Training t = new Training();
		t.setCompleted(form.isCompleted()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		t.setCreateTime(Calendar.getInstance().getTime());
		t.setCreateUser(uname);
		t.setUpdateTime(Calendar.getInstance().getTime());
		t.setUpdateUser(uname);
		t.setStatus(IStatus.ACTIVE);
		t.setDescription(form.getDescription());
		t.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		if(form.getInstitutionId() != null)
		{
		  t.setInstitution(crudDAO.findById(Institution.class, form.getInstitutionId()));
		}
		t.setResult(form.getResult());
		t.setTrainingDate(form.getTrainingDate());
		t.setTrainingType(crudDAO.findById(TrainingType.class, form.getTrainingTypeId()));
		
		return crudDAO.create(t);
	}

	
	@Transactional
	public Training modifyTraining(TrainingForm form, String uname) {

		if(form.getId() == null)
		{
			throw new ValidationException("Trajnimi i papercaktuar");
		}
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(form.getTrainingTypeId() == null)
		{
			throw new ValidationException("Plotesoni tipin e trajnimit");
		}
		if(form.getTrainingDate() == null)
		{
			throw new ValidationException("Plotesoni daten e trajnimit");
		}
		if(!StringUtil.isValid(form.getResult()))
		{
			throw new ValidationException("Plotesoni rezultatin e trajnimit");
		}
		
		Training t = crudDAO.findById(Training.class, form.getId());
		t.setCompleted(form.isCompleted()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		t.setUpdateTime(Calendar.getInstance().getTime());
		t.setUpdateUser(uname);
		t.setDescription(form.getDescription());
		t.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		if(form.getInstitutionId() != null)
		{
		  t.setInstitution(crudDAO.findById(Institution.class, form.getInstitutionId()));
		}
		t.setResult(form.getResult());
		t.setTrainingDate(form.getTrainingDate());
		t.setTrainingType(crudDAO.findById(TrainingType.class, form.getTrainingTypeId()));
		
		return crudDAO.update(t);
	}

	
	@Transactional
	public void deleteTraining(Integer trainingId, String uname) {

		Training t = crudDAO.findById(Training.class, trainingId);
		t.setStatus(IStatus.NOT_ACTIVE);
		t.setUpdateTime(Calendar.getInstance().getTime());
		t.setUpdateUser(uname);
		
		crudDAO.update(t);
		
	}

	public List<Training> getEmployeeTrainings(String nid, String uname) {
		return trainingDAO.getEmployeeTrainings(nid);
	}
}
