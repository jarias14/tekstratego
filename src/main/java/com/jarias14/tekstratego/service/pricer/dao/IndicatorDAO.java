package com.jarias14.tekstratego.service.pricer.dao;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface IndicatorDAO {
    
    public void createIndicator(Indicator indicator);
    
    public Indicator readIndicator(String indicatorId);
    
    public void deleteIndicator(String indicatorId);
    
    public SortedMap<Date, Double> readPrices(Stock stock, SizeOfBarsEnum sizeOfBar,
            PriceOfBarsEnum priceOfBar, Date startDate, Date endDate);

}
