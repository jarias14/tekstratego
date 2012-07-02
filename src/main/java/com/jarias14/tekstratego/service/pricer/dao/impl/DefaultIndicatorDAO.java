package com.jarias14.tekstratego.service.pricer.dao.impl;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.models.SizeOfBars;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.common.utilities.MySqlConnector;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DefaultIndicatorDAO implements IndicatorDAO {
    
    private MembaseConnector memory;
    private MySqlConnector mysql;
    private int timeToLiveInSeconds;

    @Override
    public SortedMap<Date, Double> readPrices(Stock stock, SizeOfBars sizeOfBar, Date startDate, int numberOfBars) {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        
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

    public MySqlConnector getMysql() {
        return mysql;
    }

    public void setMysql(MySqlConnector mysql) {
        this.mysql = mysql;
    }

}
