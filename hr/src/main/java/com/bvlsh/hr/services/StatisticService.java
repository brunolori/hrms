package com.bvlsh.hr.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.StatisticDAO;
import com.bvlsh.hr.models.KeyValue;

@Service
public class StatisticService {
	
	@Autowired StatisticDAO stsDAO;
	
	
	public Long departmentsCount(List<Integer> deptIds)
	{
		if(deptIds != null && !deptIds.isEmpty())
		{
			return new Long(deptIds.size());
		}
		return stsDAO.departmentsCount();
	}
	
	public Long positionsCount(List<Integer> deptIds)
	{
		return stsDAO.positionsCount(deptIds);
	}
	
	public Long freePositionsCount(List<Integer> deptIds)
	{
		return stsDAO.freePositionsCount(deptIds);
	}
	
	public Long employeesCount(List<Integer> deptIds)
	{
		return stsDAO.employeesCount(deptIds);
	}
	
	public Long employeesCountByGender(String gender, List<Integer> deptIds)
	{
		return stsDAO.employeesCountByGender(gender, deptIds);
	}
	
	
	
	public List<KeyValue> employeesByStudyField(List<Integer> deptIds)
	{
		return stsDAO.employeesByStudyField(deptIds);
	}
	
	public List<KeyValue> employeesByGrade(List<Integer> deptIds)
	{
		return stsDAO.employeesByGrade(deptIds);
	}
	
	
	public List<KeyValue> employeesByForeignLanguage(List<Integer> deptIds)
	{
		return stsDAO.employeesByForeignLanguage(deptIds);
	}
	
	public List<KeyValue> employeesByPaymentCategory(List<Integer> deptIds)
	{
		return stsDAO.employeesByPaymentCategory(deptIds);
	}
	
	
	public List<KeyValue> employeesByJobEndingReason(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.employeesByJobEndingReason(from, to, deptIds);
	}
	
	public List<KeyValue> employmentsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.employmentsByPeriod(from, to, deptIds);
	}
	
	public List<KeyValue> jobEndingsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.jobEndingsByPeriod(from, to, deptIds);
	}
	
	public List<KeyValue> provisionsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.provisionsByPeriod(from, to, deptIds);
	}
	
	public List<KeyValue> validationsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.validationsByPeriod(from, to, deptIds);
	}
	
	public List<KeyValue> trainingsByPeriod(Date from, Date to, List<Integer> deptIds)
	{
		return stsDAO.trainingsByPeriod(from, to, deptIds);
	}

}
