package com.bvlsh.hr.models;

import java.io.Serializable;

import com.bvlsh.hr.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;
	

	UserDTO user;
	String token;
	
	
	public UserToken() {}
	
	
	public UserToken(UserDTO user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	
	
	
	
}
