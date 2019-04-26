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
public class DepartmentPositionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Integer id;
    private boolean status;
    private String name;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private EmployeeHistoryDTO currentEmployee;
    private DepartmentDTO department;
    private PositionDTO position;
    private PaymentCategoryDTO paymentCategory;
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentPositionDTO other = (DepartmentPositionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    
    
}
