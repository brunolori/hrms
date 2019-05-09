package com.bvlsh.hr.ui.services;

import java.util.Date;
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
	
	
	public List<KeyValue> employeesByPaymentCategory() {
		return new StatisticClient().employeesByPaymentCategory();
	}
	
	public List<KeyValue> employeesByJobEndingReason(Date from, Date to) {
		return new StatisticClient().employeesByJobEndingReason(from, to);
	}
	
	
	public List<KeyValue> employmentsByPeriod(Date from, Date to) {
		return new StatisticClient().employmentsByPeriod(from, to);
	}
	
	public List<KeyValue> jobEndingsByPeriod(Date from, Date to) {
		return new StatisticClient().jobEndingsByPeriod(from, to);
	}
	
	
	public List<KeyValue> provisionsByPeriod(Date from, Date to) {
		return new StatisticClient().provisionsByPeriod(from, to);
	}
	
	
	public List<KeyValue> validationsByPeriod(Date from, Date to) {
		return new StatisticClient().validationsByPeriod(from, to);
	}
	
	
	public List<KeyValue> trainingsByPeriod(Date from, Date to) {
		return new StatisticClient().trainingsByPeriod(from, to);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
