package com.bvlsh.hr.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;
	

	String username;
	String password;
	String browser;
	String ip;
	
	
	
	
	
}
