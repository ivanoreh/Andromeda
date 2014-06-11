package com.example.andromeda;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;


public class Vreme {
	int y,mon,day,h,min;
	
	public Vreme() {
		Calendar c = Calendar.getInstance();
		y = c.get(Calendar.YEAR);
		mon = c.get(Calendar.MONTH);
		day = c.get(Calendar.DATE);
		h = c.get(Calendar.HOUR_OF_DAY);
		min= c.get(Calendar.MINUTE) ;
		
	}
	public String vrati(){
		String d="";
		d+=Integer.toString(y); d+=",";
		d+=Integer.toString(mon); d+=",";
		d+=Integer.toString(day); d+=",";
		d+=Integer.toString(h); d+=",";
		d+=Integer.toString(min); d+=",";
		return d;
	}

}
