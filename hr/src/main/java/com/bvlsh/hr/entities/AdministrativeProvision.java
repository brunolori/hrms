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
@Table(name = "administrative_provision")
@Getter @Setter
public class AdministrativeProvision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ACT_NO")
    private String actNo;
    @Column(name = "ACT_DATE")
    @Temporal(TemporalType.DATE)
    private Date actDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "VALIDITY_IN_MONTHS")
    private Integer validityInMonths;
    @Size(max = 200)
    @Column(name = "REASON")
    private String reason;
    @Column(name = "ACTIVE")
    private Integer active;
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
    @JoinColumn(name = "EMPLOYEE_NID", referencedColumnName = "NID")
    @ManyToOne
    private Employee employee;
    @JoinColumn(name = "PROVISION_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private ProvisionType provisionType;

    
    
}
