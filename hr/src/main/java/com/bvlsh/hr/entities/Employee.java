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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */
@Entity
@Table(name = "employee")
@Getter @Setter
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NID")
    private String nid;
    @Size(max = 45)
    @Column(name = "NAME")
    private String name;
    @Size(max = 45)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 45)
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Size(max = 45)
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Size(max = 45)
    @Column(name = "MAIDEN_NAME")
    private String maidenName;
    @Size(max = 45)
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "EMPLOYEE_NO")
    private String employeeNo;
    @Column(name = "DOSSIER_NO")
    private String dossierNo;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 45)
    @Column(name = "POB")
    private String pob;
    @Size(max = 1)
    @Column(name = "CIVIL_STATUS")
    private String civilStatus;
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
    @JoinColumn(name = "CITIZENSHIP_CODE", referencedColumnName = "CODE")
    @ManyToOne
    private State citizenship;
    @JoinColumn(name = "DEPARTMENT_POSITION_ID", referencedColumnName = "ID")
    @ManyToOne
    private DepartmentPosition departmentPosition;
    @JoinColumn(name = "NATIONALITY_CODE", referencedColumnName = "CODE")
    @ManyToOne
    private State nationality;
    @JoinColumn(name = "PAYMENT_CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private PaymentCategory paymentCategory;

   
    
}
