package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;



import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionForm implements Serializable {

	Integer id;
	String personNid;
	Integer departmentId;
	Integer positionId;
	Date startDate;
	Date endDate;
	
	
	
}
