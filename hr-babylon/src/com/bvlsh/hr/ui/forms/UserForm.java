package com.bvlsh.hr.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	     String username;
	     String secret;
	     boolean limitedUser;
	     Integer rootDepartmentId;
	     String roleCode;
	     boolean status;

	     
	     
}
