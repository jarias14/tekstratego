package com.jarias14.tekstratego.service.pricer.dao;

import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface IndicatorDAO {
    
    public void createIndicator(Indicator indicator);
    
    public Indicator readIndicator(String indicatorId);
    
    public void deleteIndicator(String indicatorId);
    
    public SortedMap<Calendar, Double> readPrices(Stock stock, SizeOfBarsEnum sizeOfBar,
            PriceOfBarsEnum priceOfBar, Calendar startDate, Calendar endDate);
    
    public Calendar getStartDate(Calendar endDate, int numberOfBars,
            SizeOfBarsEnum sizeOfBars);

}
