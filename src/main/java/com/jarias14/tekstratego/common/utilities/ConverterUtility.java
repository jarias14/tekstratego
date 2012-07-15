package com.jarias14.tekstratego.common.utilities;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ConverterUtility {
    
    

    public static Calendar toCalendar(String date) {
        
        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        Calendar calendar = Calendar.getInstance();
        
        try {
            calendar.setTimeInMillis(formatter.parse(date).getTime());
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return calendar;
    }
    
    public static BigDecimal toBigDecimal(String number) {
        
        Locale in_ID = new Locale("en","US");

        DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(in_ID);
        nf.setParseBigDecimal(true);

        BigDecimal bd = (BigDecimal) nf.parse(number, new ParsePosition(0));
        
        return bd;
    }

    public static String toString(Calendar today) {

        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        String date = formatter.format(today.getTime());
        return date;
    }

    public static Calendar toCalendar(Date date) {
        
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTimeInMillis(date.getTime());
        
        return calendar;
    }

}
