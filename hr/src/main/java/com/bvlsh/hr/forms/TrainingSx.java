package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainingSx implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean completed;
	private String result;
	private Date trainingDate;
	private Integer institutionId;
	private String personNid;
	private Integer trainingTypeId;
	
	Integer firstResult;
	Integer maxResult;
}
