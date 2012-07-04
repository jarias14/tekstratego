package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public abstract class IndicatorBase implements Indicator {
    
    private static final long serialVersionUID = -3455826973698151284L;
    
    private String id;
    private SizeOfBarsEnum sizeOfBars;
    
    public IndicatorBase() {
        
    }
    
    public abstract SortedMap<Date, Double> calculate(Stock stock, Date startDate, int numberOfBars);
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public SizeOfBarsEnum getSizeOfBars() {
        return sizeOfBars;
    }
    
    public void setSizeOfBars(SizeOfBarsEnum sizeOfBars) {
        this.sizeOfBars = sizeOfBars;
    }

    @Override
    public IndicatorResource toResource() {
        IndicatorResource resource = new IndicatorResource();
        resource.setIndicatorId(this.getId());
        resource.setSizeOfBars(this.getSizeOfBars().name());
        return resource;
    }
    
    public void fromResource(IndicatorResource resource) {
        this.id = resource.getIndicatorId();
        this.sizeOfBars = SizeOfBarsEnum.valueOf(resource.getSizeOfBars());
    }
    
}
