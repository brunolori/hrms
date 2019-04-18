package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainingForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	private boolean completed;
	private String result;
	private Date trainingDate;
	private Integer institutionId;
	private String personNid;
	private Integer trainingTypeId;

}
