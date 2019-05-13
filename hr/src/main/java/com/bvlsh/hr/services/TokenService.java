package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.security.TokenUtil;

@Service
public class TokenService {
	
	@Autowired DepartmentService deptService;
	
	public String generateToken(User u)
	{
		List<Integer> deptIds = deptService.getAllChildDepartmentIds(u.getRootDepartment().getId());
				
		return TokenUtil.generateToken(u, deptIds);
	}
	
	public String getUsername(String token)
	{
		return TokenUtil.getUsername(token);
	}
	
	public List<Integer> getDeptIds(String token)
	{
		return TokenUtil.getDeptIds(token);
	}

}
