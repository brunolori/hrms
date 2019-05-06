package com.bvlsh.hr.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BankAccountSx implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String nid;
	String employeeNo;
	String name;
	String surname;
	String gender;
	Integer departmentId;
	
	Integer bankId;
	String iban;
	    
	Integer firstResult;
	Integer maxResult;
	
	

}
