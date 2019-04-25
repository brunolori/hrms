package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.TrainingDAO;
import com.bvlsh.hr.entities.Training;
import com.bvlsh.hr.forms.TrainingForm;
import com.bvlsh.hr.forms.TrainingSx;

@Service
public class TrainingService {

	@Autowired TrainingDAO trainingDAO;

	public List<Training> searchTrainings(TrainingSx sx, String uname) {
		return trainingDAO.searchTrainings(sx);
	}

	public Training registerTraining(TrainingForm form, String uname) {

		return null;
	}

	public Training modifyTraining(TrainingForm form, String uname) {

		return null;
	}

	public void deleteTraining(Integer trainingId, String uname) {

	}
}
