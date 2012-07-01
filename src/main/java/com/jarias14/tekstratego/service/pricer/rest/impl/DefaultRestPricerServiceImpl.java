package com.jarias14.tekstratego.service.pricer.rest.impl;

import com.jarias14.tekstratego.service.pricer.biz.PricerService;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorFactory;
import com.jarias14.tekstratego.service.pricer.rest.RestPricerService;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

public class DefaultRestPricerServiceImpl implements RestPricerService {
    
    private PricerService pricerService;

    @Override
    public IndicatorResource createIndicator(IndicatorResource resource) {
        
        Indicator model = null;
        
        model = IndicatorFactory.getIndicator(resource);
        
        model = pricerService.createIndicator(model);
        
        return model.toResource();
    }

    @Override
    public BaseResource getValues(String indicatorId, String stockId, String sizeOfBars, String startDate, String numberOfBars) {
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IndicatorResource getIndicator(String indicatorId) {
        return pricerService.retrieveIndicator(indicatorId).toResource();
    }

    public PricerService getPricerService() {
        return pricerService;
    }

    public void setPricerService(PricerService pricerService) {
        this.pricerService = pricerService;
    }

}
