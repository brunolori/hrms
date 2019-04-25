package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdministrativeProvisionSx implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String actNo;
	Date actDate;
	Date startDateFrom;
    Date endDateTo;
	Integer provisionTypeId;
		
	Integer firstResult;
	Integer maxResult;

}
