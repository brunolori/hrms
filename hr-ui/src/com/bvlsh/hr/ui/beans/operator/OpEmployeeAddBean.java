package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bvlsh.hr.ui.forms.EmployeeForm;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpEmployeeAddBean implements Serializable {
	
	
	
	
	EmployeeForm form;
	Integer departmentId;
	
	
	

}
