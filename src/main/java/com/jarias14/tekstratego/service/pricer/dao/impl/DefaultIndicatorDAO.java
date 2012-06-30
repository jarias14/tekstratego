package com.jarias14.tekstratego.service.pricer.dao.impl;

import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DefaultIndicatorDAO implements IndicatorDAO {
    
    private MembaseConnector memory;
    private int timeToLiveInSeconds;

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

}
