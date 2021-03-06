/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */
@Entity
@Table(name = "user")
@Getter @Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 45)
    @Column(name = "SECRET")
    private String secret;
    @Column(name = "LIMITED_USER")
    private Integer limitedUser;
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private EmployeeHistory employee;
    @JoinColumn(name = "ROOT_DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department rootDepartment;
    @JoinColumn(name = "ROLE_CODE", referencedColumnName = "CODE")
    @ManyToOne
    private Role role;
    @Column(name = "STATUS")
    private Integer status;

   
}
