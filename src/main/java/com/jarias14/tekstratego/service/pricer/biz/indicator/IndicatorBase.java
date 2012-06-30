package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.HashMap;

public class IndicatorBase {
    
    private String id;
    private String sizeOfBars;
    private String numberOfBars;
    
    public IndicatorBase(HashMap<String, Object> indicator) {
        
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getSizeOfBars() {
        return sizeOfBars;
    }
    
    public void setSizeOfBars(String sizeOfBars) {
        this.sizeOfBars = sizeOfBars;
    }
    
    public String getNumberOfBars() {
        return numberOfBars;
    }
    
    public void setNumberOfBars(String numberOfBars) {
        this.numberOfBars = numberOfBars;
    }
}
