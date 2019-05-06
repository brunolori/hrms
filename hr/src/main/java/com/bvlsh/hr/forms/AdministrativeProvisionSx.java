package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdministrativeProvisionSx implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String nid;
	String employeeNo;
	String name;
	String surname;
	String gender;
	Integer departmentId;
	
	String actNo;
	Date actDate;
	Date fromDate;
    Date toDate;
	Integer provisionTypeId;
		
	Integer firstResult;
	Integer maxResult;

}
