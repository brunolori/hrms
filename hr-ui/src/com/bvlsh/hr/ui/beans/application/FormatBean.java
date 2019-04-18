package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bvlsh.hr.ui.utils.DateUtil;
import com.bvlsh.hr.ui.utils.StringUtil;



@ManagedBean
@RequestScoped
public class FormatBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public String formatDate(Date date)
	{
		return DateUtil.formatDate(date);
	}
	
	public String formatTimestamp(Date date)
	{
		return DateUtil.formatTimestamp(date);
	}

	
	public boolean isValid(String c)
	{
		return StringUtil.isValid(c);
	}
	
	
	public String thisMonth()
	{
		return DateUtil.monthToString(Calendar.getInstance().get(Calendar.MONTH));
	}
	
	public String lastMonth()
	{
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);
		cal.add(Calendar.MONTH, -1);
		int prevYear = cal.get(Calendar.YEAR);
		int prevMonth = cal.get(Calendar.MONTH);
		
		String prev = DateUtil.monthToString(prevMonth);
		if(prevYear < thisYear) prev += (" "+prevYear);
		return prev;
	}
	
	public String lastThreeMonths()
	{
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);
		cal.add(Calendar.MONTH, -1);
		int prevYear = cal.get(Calendar.YEAR);
		int prevMonth = cal.get(Calendar.MONTH);
		cal.add(Calendar.MONTH, -2);
		int threeMonthAgo = cal.get(Calendar.MONTH);
		int threeMonthYear = cal.get(Calendar.YEAR);
		
		String prev = DateUtil.monthToString(prevMonth);
		if(prevYear < thisYear) prev += (" "+prevYear);
		
		String three = DateUtil.monthToString(threeMonthAgo);
		if(threeMonthYear < thisYear) three += (" "+threeMonthYear);
		
		return three +" - "+prev;
		
	}
	
	public String lastSixMonths()
	{
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);
		cal.add(Calendar.MONTH, -1);
		int prevYear = cal.get(Calendar.YEAR);
		int prevMonth = cal.get(Calendar.MONTH);
		cal.add(Calendar.MONTH, -5);
		int sixMonthAgo = cal.get(Calendar.MONTH);
		int sixMonthYear = cal.get(Calendar.YEAR);
		
		String prev = DateUtil.monthToString(prevMonth);
		if(prevYear < thisYear) prev += (" "+prevYear);
		
		String six = DateUtil.monthToString(sixMonthAgo);
		if(sixMonthYear < thisYear) six += (" "+sixMonthYear);
		
		return six +" - "+prev;
		
	}
	
	public String thisYear()
	{
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR));
	}
	
	public String lastYear()
	{
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR) - 1);
	}
	
	public String formatPrc(long a, long b)
	{
		if(b == 0) return "-"; 
		
		Double one = new Double(a);
		Double two = new Double(b);
		
		Double value = one/two;
		
		double roundValue = Math.round(value*100);
		
		return roundValue+" %";
	}
	
	
	public String formatDateName(Date date)
	{
		if(date == null) return "-";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.setTime(date);
		String month = DateUtil.monthToString(cal.get(Calendar.MONTH));
		
		String value = cal.get(Calendar.DATE)+" "+month;
		int dateYear = cal.get(Calendar.YEAR);
		if(dateYear < year) value += (" "+dateYear);
		
		return value;
		
	}
	
	
	
	
}
