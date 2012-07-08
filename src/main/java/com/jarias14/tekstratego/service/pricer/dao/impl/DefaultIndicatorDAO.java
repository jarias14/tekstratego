package com.jarias14.tekstratego.service.pricer.dao.impl;

import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.common.utilities.RdsConnector;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DefaultIndicatorDAO implements IndicatorDAO {
    
    private MembaseConnector memory;
    private RdsConnector rds;
    private int timeToLiveInSeconds;

    @Override
    public SortedMap<Calendar, Double> readPrices(Stock stock, SizeOfBarsEnum sizeOfBar, PriceOfBarsEnum priceOfBar,Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, Double> prices = rds.getPrices(stock.getExchange(), stock.getSymbol(), sizeOfBar, priceOfBar, startDate, endDate);
        
        // TODO if empty or null try yahoo and other sources of data http://code.google.com/p/tekstratego/issues/detail?id=1
        
        return prices;
    }
    
    @Override
    public void createIndicator(Indicator indicator) {
        memory.save(indicator.getId(), indicator, timeToLiveInSeconds);
    }

    @Override
    public Indicator readIndicator(String indicatorId) {
        return (Indicator) memory.read(indicatorId);
    }

    @Override
    public void deleteIndicator(String indicatorId) {
        // TODO allow deletion of indicators
        
    }
    
    public MembaseConnector getMemory() {
        return memory;
    }

    public void setMemory(MembaseConnector memory) {
        this.memory = memory;
    }
    
    public int getTimeToLiveInSeconds() {
        return timeToLiveInSeconds;
    }

    public void setTimeToLiveInSeconds(int timeToLiveInSeconds) {
        this.timeToLiveInSeconds = timeToLiveInSeconds;
    }

    public RdsConnector getRds() {
        return rds;
    }

    public void setRds(RdsConnector rds) {
        this.rds = rds;
    }

    @Override
    public Calendar getStartDate(Calendar endDate, int numberOfBars, SizeOfBarsEnum sizeOfBars) {
        
        Calendar date = rds.getMarketDate(sizeOfBars, endDate, -1*numberOfBars);
        
        return date;
    }
}
