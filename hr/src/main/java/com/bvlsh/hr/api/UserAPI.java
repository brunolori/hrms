package com.bvlsh.hr.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.UserDTO;
import com.bvlsh.hr.forms.PasswordForm;
import com.bvlsh.hr.forms.UserForm;
import com.bvlsh.hr.models.Principal;
import com.bvlsh.hr.models.UserToken;
import com.bvlsh.hr.services.TokenService;
import com.bvlsh.hr.services.UserService;



@RestController
@RequestMapping("/api/user")
public class UserAPI {
	
	@Autowired
	UserService userService;
	@Autowired 
	TokenService tokenService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login(@RequestBody Principal principal)
	{
			UserToken ut = userService.login(principal);
			return new ResponseEntity<>(ut, HttpStatus.OK);		
		
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> changePassword(@RequestHeader(value="Authorization") String token, @RequestBody PasswordForm form)
	{
			
			String uname = tokenService.getUsername(token);
			
			userService.changePassword(form, uname);
			
			return new ResponseEntity<>(HttpStatus.OK);	
			
		
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerUser(@RequestHeader(value="Authorization") String token, @RequestBody UserForm form)
	{
			String uname = tokenService.getUsername(token);
			
			UserDTO dto = new Assembler().toDto(userService.registerUser(form, uname));
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyUser", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyEducation(@RequestHeader(value="Authorization") String token, @RequestBody UserForm form)
	{
			String uname = tokenService.getUsername(token);
			
			UserDTO dto = new Assembler().toDto(userService.modifyUser(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	
	
	
	
	

}
