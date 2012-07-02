package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.models.SizeOfBars;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public abstract class IndicatorBase implements Indicator {
    
    private static final long serialVersionUID = -3455826973698151284L;
    
    private String id;
    private String sizeOfBars;
    private String numberOfBars;
    
    public IndicatorBase(IndicatorResource resource) {
        this.id = resource.getIndicatorId();
        this.sizeOfBars = resource.getSizeOfBars();
        this.numberOfBars = resource.getNumberOfBars();
    }
    
    public abstract SortedMap<Date, Double> calculate(Stock stock, Date startDate, SizeOfBars sizeOfBars, int numberOfBars);
    
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

    @Override
    public IndicatorResource toResource() {
        IndicatorResource resource = new IndicatorResource();
        resource.setIndicatorId(this.getId());
        resource.setNumberOfBars(this.getNumberOfBars());
        resource.setSizeOfBars(this.getSizeOfBars());
        return resource;
    }
    
}
