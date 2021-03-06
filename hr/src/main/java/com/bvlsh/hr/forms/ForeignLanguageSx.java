package com.bvlsh.hr.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForeignLanguageSx implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String nid;
	String employeeNo;
	String name;
	String surname;
	String gender;
	Integer departmentId;
	
	
    Integer foreignLanguageId;

    Integer firstResult;
	Integer maxResult;
}
