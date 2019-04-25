package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeSx implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nid;
    private String name;
    private String surname;
    private String fatherName;
    private String motherName;
    private String maidenName;
    private String gender;
    private String employeeNo;
    private String dossierNo;
    private Date startDateFrom;
    private Date startDateTo;
    private Date endDateFrom;
    private Date endDateTo;
    private Date dob;
    private String civilStatus;
   // private Integer departmentPositionId;
    private Integer paymentCategoryId;
    private Integer departmentId;
    private Integer positionId;
    
    private Boolean onlyActive;
    private Boolean childDepartments;
    
    
    Integer firstResult;
	Integer maxResult;

}
