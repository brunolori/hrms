package com.bvlsh.hr.services;

import org.springframework.stereotype.Service;

import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.security.TokenUtil;

@Service
public class TokenService {
	
	public String generateToken(User u)
	{
		return TokenUtil.generateToken(u);
	}
	
	public String getUsername(String token)
	{
		return TokenUtil.getUsername(token);
	}
	
	

}
