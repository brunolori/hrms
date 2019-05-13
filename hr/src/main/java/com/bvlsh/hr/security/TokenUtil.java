package com.bvlsh.hr.security;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.exceptions.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class TokenUtil {

	public static String getUsername(String token)
	{
		try {
			String jwt = token.substring(7);
			Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(SecurityConstants.SECRET))
				       .parseClaimsJws(jwt).getBody();
				    
			return claims.getSubject();
		}catch(Exception e) {
			e.printStackTrace();
			throw new InvalidTokenException("TOKEN i pa vlefshem");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getDeptIds(String token)
	{
		try {
			String jwt = token.substring(7);
			Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(SecurityConstants.SECRET))
				       .parseClaimsJws(jwt).getBody();
			Object o = claims.get("deptIds");
			if(o == null) return null;
			return (List<Integer>)o;
		}catch(Exception e) {
			e.printStackTrace();
			throw new InvalidTokenException("TOKEN i pa vlefshem");
		}
	}
	
	public static boolean isLimited(String token)
	{
		try {
			String jwt = token.substring(7);
			Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(SecurityConstants.SECRET))
				       .parseClaimsJws(jwt).getBody();
			Object o = claims.get("limited");
			if(o == null) return false;
			return (boolean)o;
		}catch(Exception e) {
			e.printStackTrace();
			throw new InvalidTokenException("TOKEN i pa vlefshem");
		}
	}
	
	
	public static String generateToken(User user, List<Integer> deptIds)
	{
		Calendar cal = Calendar.getInstance();
		Date issued = cal.getTime();
		cal.add(Calendar.HOUR, 4);
		Date expired = cal.getTime();
		
		JwtBuilder builder = Jwts.builder()
				  .setIssuer("HRMS")
				  .setSubject(user.getUsername())
				  .claim("role", user.getRole().getCode());
		
		if(user.getRootDepartment() != null)
		{
			builder.claim("deptIds", deptIds)
			        .claim("limited",true);
		}
		
		builder.setIssuedAt(issued)
	      .setExpiration(expired)
		  .signWith(
		    SignatureAlgorithm.HS256,
		    TextCodec.BASE64.decode(SecurityConstants.SECRET)
		  );
				
		return builder.compact();
	}
	
}
