package com.jarias14.tekstratego.service.pricer.biz;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface PricerService {
    
    public Indicator createIndicator(Indicator indicator);
    public Indicator retrieveIndicator(String indicatorId);
    public SortedMap<Calendar, BigDecimal> calculateIndicator(String indicatorId, String stockId, String startDate, String endDate);
    public SortedMap<Calendar, BigDecimal> calculateIndicatorUsingNumberOfBars(String indicatorId, String stockId, String startDate, int numberOfBars);

}
