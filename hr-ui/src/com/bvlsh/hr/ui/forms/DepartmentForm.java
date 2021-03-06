package com.bvlsh.hr.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentForm implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer id;
	String name;
	Integer positionsNo;
	boolean expanded;
	String color;
	Integer categoryId;
	Integer parentId;

}
