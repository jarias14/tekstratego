package com.jarias14.tekstratego.common.utilities;

import java.util.Date;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;

public class DateUtility {
    
    private static RdsConnector rdsConnector;
    
    public static Date math(boolean marketDays, Date date, SizeOfBarsEnum sizeOfBars, int amount) {
        
        if (!marketDays) {
            return DateUtility.math(date, sizeOfBars.getTimeUnit(), sizeOfBars.getTimeValue(), amount);
        }
        
        SortedSet<Date> dates = DateUtility.getRdsConnector().getMarketDates(date, amount, sizeOfBars);
        
        return dates.last();
        
    }
    
    public static Date math(Date date, TimeUnit timeUnit, int timeValue, int amount) {
        
        Date newDate = null;
        
        if (TimeUnit.MINUTES.equals(timeUnit)) {
            newDate = DateUtils.addMinutes(date, amount*timeValue);
            
        } else if (TimeUnit.DAYS.equals(timeUnit)) {
            newDate = DateUtils.addDays(date, amount*timeValue);
        }
        
        return newDate;
    }

    public static RdsConnector getRdsConnector() {
        return rdsConnector;
    }

    public static void setRdsConnector(RdsConnector rdsConnector) {
        DateUtility.rdsConnector = rdsConnector;
    }

}
