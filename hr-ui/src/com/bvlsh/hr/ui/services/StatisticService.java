package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.StatisticClient;
import com.bvlsh.hr.ui.models.KeyValue;


public class StatisticService {
	
	public Long departmentsCount() {
		return new StatisticClient().departmentsCount();
	}
	
	public Long positionsCount() {
		return new StatisticClient().positionsCount();
	}
	
	public Long freePositionsCount() {
		return new StatisticClient().freePositionsCount();
	}

	public Long employeesCount() {
		return new StatisticClient().employeesCount();
	}
	
	public Long employeesCountByGender(String gender) {
		return new StatisticClient().employeesCountByGender(gender);
	}
	
	public List<KeyValue> employeesByStudyField() {
		return new StatisticClient().employeesByStudyField();
	}
	
	
	public List<KeyValue> employeesByGrade() {
		return new StatisticClient().employeesByGrade();
	}
	
	public List<KeyValue> employeesByForeignLanguage() {
		return new StatisticClient().employeesByForeignLanguage();
	}


}
