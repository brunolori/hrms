package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducationForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	    private Integer id;
	    private Date issueDate;
	    private String description;
	    private Integer educationTypeId;
	    private Integer institutionId;
	    private String personNid;
	    private Integer studyFieldId;

}
