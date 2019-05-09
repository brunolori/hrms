package com.bvlsh.hr.utils;

public class StringUtil {

	
	
	public static boolean isValid(String value)
	{
		return value != null && !"".equals(value.trim());
	}
	
	public static String formatSQ(String value)
	{		
		if(isValid(value))
		{			
			return value.trim().toUpperCase().replace("Ë","E").replace("Ç", "C").replace("\\", "");
		}
		return null;
	}
	
	public static String toUpper(String value)
	{
		if(value == null) return null;
		
		return value.trim().toUpperCase();
	}
	
	public static String firstUpper(String value)
	{
		if(value == null) return null;
		value = value.trim();
		if(value.length() < 1) return null;
		Character f = value.charAt(0);
		return String.valueOf(f) + value.substring(1);
	}
	
	
}
