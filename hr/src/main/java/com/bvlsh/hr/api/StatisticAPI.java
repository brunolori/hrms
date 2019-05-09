package com.bvlsh.hr.api;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.models.KeyValue;
import com.bvlsh.hr.services.StatisticService;
import com.bvlsh.hr.utils.DateUtil;

@RestController
@RequestMapping("/api/statistic")
public class StatisticAPI {
	
	@Autowired StatisticService statisticService;
	
	
	@RequestMapping(value="/departmentsCount", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> departmentsCount(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		Long cnt = statisticService.departmentsCount();
	    return new ResponseEntity<>(cnt,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/positionsCount", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> positionsCount(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		Long cnt = statisticService.positionsCount();
	    return new ResponseEntity<>(cnt,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/freePositionsCount", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> freePositionsCount(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		Long cnt = statisticService.freePositionsCount();
	    return new ResponseEntity<>(cnt,HttpStatus.OK);
		
	}

	@RequestMapping(value="/employeesCount", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesCount(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		Long cnt = statisticService.employeesCount();
	    return new ResponseEntity<>(cnt,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/employeesCountByGender/{gender}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesCountByGender(@RequestHeader(value="Authorization") String token, @PathVariable String gender)
	{
		//String uname = tokenService.getUsername(token);
				
		Long cnt = statisticService.employeesCountByGender(gender);
	    return new ResponseEntity<>(cnt,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/employeesByStudyField", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesByStudyField(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		List<KeyValue> list = statisticService.employeesByStudyField();
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/employeesByGrade", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesByGrade(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		List<KeyValue> list = statisticService.employeesByGrade();
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/employeesByForeignLanguage", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesByForeignLanguage(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		List<KeyValue> list = statisticService.employeesByForeignLanguage();
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/employeesByPaymentCategory", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesByPaymentCategory(@RequestHeader(value="Authorization") String token)
	{
		//String uname = tokenService.getUsername(token);
				
		List<KeyValue> list = statisticService.employeesByPaymentCategory();
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/employeesByJobEndingReason", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employeesByJobEndingReason(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.employeesByJobEndingReason(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/employmentsByPeriod", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> employmentsByPeriod(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.employmentsByPeriod(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/jobEndingsByPeriod", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> jobEndingsByPeriod(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.jobEndingsByPeriod(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/provisionsByPeriod", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> provisionsByPeriod(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.provisionsByPeriod(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/validationsByPeriod", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> validationsByPeriod(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.validationsByPeriod(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/trainingsByPeriod", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> trainingsByPeriod(@RequestHeader(value="Authorization") String token,
			@RequestParam(name = "from") String fromDate,@RequestParam(name = "to") String toDate)
	{
		//String uname = tokenService.getUsername(token);
				
		Date from = DateUtil.formatReverseStringToDate(fromDate);
		Date to = DateUtil.formatReverseStringToDate(toDate);
		
		List<KeyValue> list = statisticService.trainingsByPeriod(from, to);
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
}
