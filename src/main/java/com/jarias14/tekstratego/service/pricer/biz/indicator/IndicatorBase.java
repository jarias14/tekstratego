package com.jarias14.tekstratego.service.pricer.biz.indicator;

import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public class IndicatorBase implements Indicator {
    
    private static final long serialVersionUID = -3455826973698151284L;
    
    private String id;
    private String sizeOfBars;
    private String numberOfBars;
    
    public IndicatorBase(IndicatorResource resource) {
        this.id = resource.getIndicatorId();
        this.sizeOfBars = resource.getSizeOfBars();
        this.numberOfBars = resource.getNumberOfBars();
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

    @Override
    public IndicatorResource toResource() {
        IndicatorResource resource = new IndicatorResource();
        resource.setIndicatorId(this.getId());
        resource.setNumberOfBars(this.getNumberOfBars());
        resource.setSizeOfBars(this.getSizeOfBars());
        return resource;
    }
    
    
}
