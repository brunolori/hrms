package com.bvlsh.hr.ui.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bvlsh.hr.ui.constants.IDate;






public class DateUtil {

	
	public static String formatDate(Date date)
	{
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.DATE_FORMAT);
		
		return df.format(date);
	}
	
	public static String formatDateToStringReverse(Date date)
	{
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.REVERSE_DATE_FORMAT);
		
		return df.format(date);
	}
	
	
	public static Date toDate(String date)
	{
		try {
			
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.DATE_FORMAT);
		
		return df.parse(date);
		
		}catch(Exception e) {
			return null;
		}
	}
	
	public static String formatTimestamp(Date date)
	{
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.TIMESTAMP_FORMAT);
		
		return df.format(date);
	}
	
	public static Date toTimestamp(String date)
	{
		try {
			
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.TIMESTAMP_FORMAT);
		
		return df.parse(date);
		
		}catch(Exception e) {
			return null;
		}
	}
	
	
	public static String formatReverseDate(String reverseDate)
	{
		if(reverseDate != null && reverseDate.trim().length() == 8)
		{
			return reverseDate.substring(6, 8)+"."+reverseDate.substring(4, 6)+"."+reverseDate.substring(0, 4);
		}
		
		return null;
	}
	
	public static String toReverseDate(String date)
	{
		if(date != null && date.trim().length() == 10)
		{
			return date.substring(0, 2)+date.substring(3, 5)+date.substring(6, 10);
		}
		
		return null;
	}
	
	
	public static String getDobReverse(Integer age)
	{
		if(age != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -age);
			
			DateFormat df = new SimpleDateFormat(IDate.REVERSE_DATE_FORMAT);
			return df.format(cal.getTime());
			
		}
		
		return null;
	}
	
	public static Date addDaysToDate(Date date,int days)
	   {       
	       if (date == null) {
	           return null;
	       }
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(date);
	       calendar.add(Calendar.DATE, days);

	       return calendar.getTime();
	   }
	
	
	public static String monthToString(int month)
	{
		switch(month)
		{
	    	case 0 : return "Janar";
	    	case 1 : return "Shkurt";
	    	case 2 : return "Mars";
	    	case 3 : return "Prill";
	    	case 4 : return "Maj";
	    	case 5 : return "Qershor";
	    	case 6 : return "Korrik";
	    	case 7 : return "Gusht";
	    	case 8 : return "Shtator";
	    	case 9 : return "Tetor";
	    	case 10 : return "Nentor";
	    	case 11 : return "Dhjetor";		
		}
		
		return "N/A";
	}
	
	public static String monthToStringShort(int month)
	{
		switch(month)
		{
	    	case 0 : return "JAN";
	    	case 1 : return "SHK";
	    	case 2 : return "MAR";
	    	case 3 : return "PRI";
	    	case 4 : return "MAJ";
	    	case 5 : return "QER";
	    	case 6 : return "KOR";
	    	case 7 : return "GUS";
	    	case 8 : return "SHT";
	    	case 9 : return "TET";
	    	case 10 : return "NEN";
	    	case 11 : return "DHJ";		
		}
		
		return "N/A";
	}
	
	
	public static Date firstDateOfMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return cal.getTime();
	}
	
	
	
}
