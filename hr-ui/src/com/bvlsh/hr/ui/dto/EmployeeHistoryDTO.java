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
public class EmployeeHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String employeeNo;
    private String dossierNo;
    private boolean status;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private DepartmentPositionDTO departmentPosition;
    private PaymentCategoryDTO paymentCategory;
    private EmployeeDTO employee;
    private GradeDTO grade;
    private JobEndingReasonDTO endJobReason;
}
