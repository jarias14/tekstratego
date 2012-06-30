package com.jarias14.tekstratego.service.pricer.biz.impl;

import java.util.UUID;

import com.jarias14.tekstratego.service.pricer.biz.PricerService;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DefaultPricerService implements PricerService {
    
    private IndicatorDAO indicatorDAO;
    
    @Override
    public Indicator createIndicator(Indicator indicator) {

        // create unique id for new indicator
        String indicatorId = UUID.randomUUID().toString();
        indicator.setId(indicatorId);
        
        // save indicator to memory
        indicatorDAO.createIndicator(indicator);
        
        // read and return indicator from memory
        return indicatorDAO.readIndicator(indicatorId);
    }

    @Override
    public Indicator retrieveIndicator(String indicatorId) {
        // TODO Auto-generated method stub
        return null;
    }

    public IndicatorDAO getIndicatorDAO() {
        return indicatorDAO;
    }

    public void setIndicatorDAO(IndicatorDAO indicatorDAO) {
        this.indicatorDAO = indicatorDAO;
    }

}
