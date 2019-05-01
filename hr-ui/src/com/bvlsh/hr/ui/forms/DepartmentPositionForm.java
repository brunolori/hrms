package com.bvlsh.hr.ui.forms;

import java.io.Serializable;



import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionForm implements Serializable {

	Integer id;
	Integer departmentId;
	Integer positionId;
	String name;
	Integer paymentCategoryId;
	
	
	
}
