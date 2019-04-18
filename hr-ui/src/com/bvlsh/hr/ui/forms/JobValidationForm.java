package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobValidationForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	    private Date validationDate;
	    private Date startDate;
	    private Date endDate;
	    private String signedBy;
	    private String personNid;
	    private Integer validationTypeId;


}
