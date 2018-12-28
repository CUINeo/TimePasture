package com.example.neo.helloworld;

import java.io.Serializable;
import java.util.Calendar;

public class SinglePlan implements Serializable{
	private static final long serialVersionUID = 3653634L;
	public int year1, month1, day1;
	public int year2, month2, day2;
	public String plan;
	public String topic;
	public SinglePlan() {
		plan = new String();
		topic = new String();
		Calendar cal = Calendar.getInstance();
		year1=year2=cal.get(Calendar.YEAR);       
		month1=month2=cal.get(Calendar.MONTH); 
		day1=day2=cal.get(Calendar.DAY_OF_MONTH);
	}
	
	@Override
	public String toString() {
		String r = topic;
		return r;
	}
}
