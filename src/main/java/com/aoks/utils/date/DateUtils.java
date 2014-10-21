package com.aoks.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

    private static SimpleDateFormat sdFormat8 = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdFormat6 = new SimpleDateFormat("dd/MM/yy");

    public static Calendar parseDate8(String date) {
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTime(sdFormat8.parse(date));
            return cal;
        } catch (ParseException e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Calendar parseDate6(String date) {
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTime(sdFormat6.parse(date));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format8(Calendar cal) {
        return sdFormat8.format(cal.getTime());
    }
    
    public static String format8en(Calendar cal) {
        return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
    }

    public static String format6(Calendar cal) {
        return sdFormat6.format(cal.getTime());
    }

    public static Calendar getMonthReferenceDate(Calendar cal) {
        return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
    }
    
    public static Calendar getDayReferenceDate(Calendar cal, int day){
    	return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), day);
    }
    
    public static Calendar clone(Calendar cal){
    	Calendar instance = Calendar.getInstance();
    	instance = (Calendar) cal.clone();
    	return instance;
    }
}
