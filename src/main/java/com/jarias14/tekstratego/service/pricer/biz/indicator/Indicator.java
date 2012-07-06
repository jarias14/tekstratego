package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;

public interface Indicator extends Serializable {
    
    public IndicatorResource toResource();
    public void fromResource(IndicatorResource resource);
    
    public String getId();
    public void setId(String indicatorId);
    
    public SortedMap<Calendar, Double> calculate(Stock stock, Calendar startDate, Calendar endDate);
    

}
