package com.jarias14.tekstratego.service.pricer.rest.impl;

import org.springframework.stereotype.Service;

import com.jarias14.tekstratego.service.pricer.rest.RestPricerService;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

@Service("restPricerService")
public class DefaultPricerServiceImpl implements RestPricerService {

    @Override
    public BaseResource createIndicator(IndicatorResource indicator) {
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getValues(String indicatorId, String stockId, String sizeOfBars, String startDate, String numberOfBars) {
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getIndicator(String indicatorId) {
        // TODO Auto-generated method stub
        return null;
    }

}
