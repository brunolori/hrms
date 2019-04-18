package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdministrationProvisionForm implements Serializable {

	private static final long serialVersionUID = 1L;

	String actNo;
	Date actDate;
	Date startDate;
	Integer validityInMonths;
	String reason;
	boolean active;
	String personNid;
	Integer provisionTypeId;

}
