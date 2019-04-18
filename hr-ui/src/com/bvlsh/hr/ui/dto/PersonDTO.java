/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.ui.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */

@Getter @Setter
public class PersonDTO implements Serializable {
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
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private StateDTO citizenship;
    private DepartmentPositionDTO departmentPosition;
    private StateDTO nationality;
    private PaymentCategoryDTO paymentCategory;

   
    
}
