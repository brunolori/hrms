/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */
@Entity
@Table(name = "employee_history")
@Getter @Setter
public class EmployeeHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 45)
    @Column(name = "EMPLOYEE_NO")
    private String employeeNo;
    @Size(max = 45)
    @Column(name = "DOSSIER_NO")
    private String dossierNo;
    @Column(name = "STATUS")
    private Integer status;
    @Size(max = 45)
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 45)
    @Column(name = "UPDATE_USER")
    private String updateUser;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "DEPARTMENT_POSITION_ID", referencedColumnName = "ID")
    @ManyToOne
    private DepartmentPosition departmentPosition;
    @JoinColumn(name = "PAYMENT_CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private PaymentCategory paymentCategory;
    @JoinColumn(name = "EMPLOYEE_NID", referencedColumnName = "NID")
    @ManyToOne
    private Employee employee;
    @JoinColumn(name = "END_JOB_REASON_ID", referencedColumnName = "ID")
    @ManyToOne
    private JobEndingReason endJobReason;
    @JoinColumn(name = "GRADE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Grade grade;
    
}
