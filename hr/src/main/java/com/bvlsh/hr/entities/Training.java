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
@Table(name = "training")
@Getter @Setter
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COMPLETED")
    private Integer completed;
    @Size(max = 45)
    @Column(name = "RESULT")
    private String result;
    @Column(name = "TRAINING_DATE")
    @Temporal(TemporalType.DATE)
    private Date trainingDate;
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
    @JoinColumn(name = "INSTITUTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Institution institution;
    @JoinColumn(name = "EMPLOYEE_NID", referencedColumnName = "NID")
    @ManyToOne
    private Employee employee;
    @JoinColumn(name = "TRAINING_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private TrainingType trainingType;
    
}
