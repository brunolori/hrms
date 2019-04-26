package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.TrainingClient;
import com.bvlsh.hr.ui.dto.TrainingDTO;
import com.bvlsh.hr.ui.forms.TrainingForm;
import com.bvlsh.hr.ui.forms.TrainingSx;;

public class TrainingService {

	public TrainingDTO registerTraining(TrainingForm form) {
		return new TrainingClient().registerTraining(form);
	}

	public TrainingDTO modifyTraining(TrainingForm form) {
		return new TrainingClient().modifyTraining(form);
	}

	public TrainingDTO deleteTraining(Integer trainingId) {
		return new TrainingClient().deleteTraining(trainingId);
	}

	public List<TrainingDTO> searchTrainings(TrainingSx sx) {
		return new TrainingClient().searchTrainings(sx);
	}

}
