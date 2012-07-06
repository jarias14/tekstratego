package com.jarias14.tekstratego.service.pricer.biz;

import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface PricerService {
    
    public Indicator createIndicator(Indicator indicator);
    public Indicator retrieveIndicator(String indicatorId);
    public SortedMap<Calendar, Double> calculateIndicator(String indicatorId, String stockId, String startDate, String endDate);

}
