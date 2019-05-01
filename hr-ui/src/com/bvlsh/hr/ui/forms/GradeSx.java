package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GradeSx implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String nid;
	String employeeNo;
	String name;
	String surname;
	Integer departmentId;
	
	Date startDate;
    Date endDate;
    Integer gradeId;
    String personNid;
    
    Integer firstResult;
	Integer maxResult;
	
}
