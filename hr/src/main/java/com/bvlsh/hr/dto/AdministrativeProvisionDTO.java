/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */

@Getter @Setter
public class AdministrativeProvisionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String actNo;
    private Date actDate;
    private Date startDate;
    private Integer validityInMonths;
    private String reason;
    private boolean active;
    private boolean status;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private EmployeeDTO employee;
    private ProvisionTypeDTO provisionType;

    
    
}
