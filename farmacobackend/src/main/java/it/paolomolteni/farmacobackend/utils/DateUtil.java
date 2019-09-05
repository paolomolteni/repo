package it.paolomolteni.farmacobackend.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/**
	 * 
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static Date getDateFromString(String s) throws Exception {
		
		if(StringUtils.isBlank(s)) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat(DATE_FORMAT).parse(s));
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static String getFormettedDate(Date date) {
		if(date != null) {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			return df.format(date);
		}
		
		return null;
	}

}
