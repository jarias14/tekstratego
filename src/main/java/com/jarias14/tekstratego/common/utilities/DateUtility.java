package com.jarias14.tekstratego.common.utilities;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

public class DateUtility {
    
    public static Date math(Date date, TimeUnit timeUnit, int timeValue, int amount) {
        
        Date newDate = null;
        
        if (TimeUnit.MINUTES.equals(timeUnit)) {
            newDate = DateUtils.addMinutes(date, amount*timeValue);
            
        } else if (TimeUnit.DAYS.equals(timeUnit)) {
            newDate = DateUtils.addDays(date, amount*timeValue);
        }
        
        return newDate;
    }

}
