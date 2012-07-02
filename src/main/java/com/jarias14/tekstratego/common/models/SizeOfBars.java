package com.jarias14.tekstratego.common.models;

import java.util.concurrent.TimeUnit;

public enum SizeOfBars {
    
    ONE_DAY (1, TimeUnit.DAYS);
    
    private final int timeValue;
    private final TimeUnit timeUnit;
    
    SizeOfBars(int timeValue, TimeUnit timeUnit) {
        this.timeValue = timeValue;
        this.timeUnit = timeUnit;
    }

    public int getTimeValue() {
        return timeValue;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

}
