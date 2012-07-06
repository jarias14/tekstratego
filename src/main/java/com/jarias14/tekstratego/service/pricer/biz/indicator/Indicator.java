package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.io.Serializable;
import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public interface Indicator extends Serializable {
    
    public IndicatorResource toResource();
    public void fromResource(IndicatorResource resource);
    
    public String getId();
    public void setId(String indicatorId);
    
    public SortedMap<Date, Double> calculate(Stock stock, Date startDate, Date endDate);
    

}
