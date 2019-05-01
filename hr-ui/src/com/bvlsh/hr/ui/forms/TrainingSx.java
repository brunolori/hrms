package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainingSx implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	String nid;
	String employeeNo;
	String name;
	String surname;
	Integer departmentId;
	
	boolean completed;
	String result;
	Date trainingDate;
	Integer institutionId;
	String personNid;
	Integer trainingTypeId;
	
	Integer firstResult;
	Integer maxResult;
}
