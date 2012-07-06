package com.jarias14.tekstratego.common.models;

import java.util.Calendar;

public enum SizeOfBarsEnum {
    
    ONE_DAY (1, Calendar.DATE);
    
    private final int timeValue;
    private final int timeUnit;
    
    SizeOfBarsEnum(int timeValue, int timeUnit) {
        this.timeValue = timeValue;
        this.timeUnit = timeUnit;
    }

    public int getTimeValue() {
        return timeValue;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

}
