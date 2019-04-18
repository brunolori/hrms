package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeForm implements Serializable {

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
    private Date startDate;
    private Date endDate;
    private Date dob;
    private String pob;
    private String civilStatus;
    private String citizenshipCode;
    private Integer departmentPositionId;
    private String nationalityCode;
    private Integer paymentCategoryId;

}
