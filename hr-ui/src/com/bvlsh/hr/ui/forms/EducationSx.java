package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducationSx implements Serializable {

	private static final long serialVersionUID = 1L;
	
	    private Date issueDate;
	    private Integer educationTypeId;
	    private Integer institutionId;
	    private Integer studyFieldId;
	    
	    Integer firstResult;
		Integer maxResult;
	
	

}
