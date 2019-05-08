package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bvlsh.hr.dao.StatisticDAO;
import com.bvlsh.hr.models.KeyValue;

@Service
public class StatisticService {
	
	@Autowired StatisticDAO stsDAO;
	
	
	

	public Long departmentsCount()
	{
		return stsDAO.departmentsCount();
	}
	
	public Long positionsCount()
	{
		return stsDAO.positionsCount();
	}
	
	public Long freePositionsCount()
	{
		return stsDAO.freePositionsCount();
	}
	
	public Long employeesCount()
	{
		return stsDAO.employeesCount();
	}
	
	public Long employeesCountByGender(String gender)
	{
		return stsDAO.employeesCountByGender(gender);
	}
	
	
	
	public List<KeyValue> employeesByStudyField()
	{
		return stsDAO.employeesByStudyField();
	}
	
	public List<KeyValue> employeesByGrade()
	{
		return stsDAO.employeesByGrade();
	}
	
	
	public List<KeyValue> employeesByForeignLanguage()
	{
		return stsDAO.employeesByForeignLanguage();
	}
	
	

}
