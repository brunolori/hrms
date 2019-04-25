package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdministrativeProvisionSx implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String actNo;
	Date actDate;
	private Date startDateFrom;
    private Date endDateTo;
	Integer validityInMonths;
	Integer provisionTypeId;
		
	Integer firstResult;
	Integer maxResult;

}
