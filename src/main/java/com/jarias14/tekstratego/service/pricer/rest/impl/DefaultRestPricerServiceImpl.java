package com.jarias14.tekstratego.service.pricer.rest.impl;

import java.util.Calendar;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.common.resource.IndicatorValuesResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.pricer.biz.PricerService;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorFactory;
import com.jarias14.tekstratego.service.pricer.rest.RestPricerService;


public class DefaultRestPricerServiceImpl implements RestPricerService {
    
    private PricerService pricerService;
    
    @Override
    public IndicatorResource createIndicator(IndicatorResource resource) { 
        
        Indicator model = null;
        model = IndicatorFactory.getIndicator(resource); //from resource to model
        model = pricerService.createIndicator(model);  //save to memory
        
        resource = model.toResource();  //back to resource, now with indicatorId
        resource.getLinks().put("self", LinksUtility.getPricerIndicatorLink(resource.getId()));
        
        return resource;
    }

    @Override
    public IndicatorValuesResource getValues(String indicatorId, String stockId, String startDate, String numberOfBars) {
        
        SortedMap<Calendar,Double> values = pricerService.calculateIndicator(indicatorId, stockId, startDate, numberOfBars);
        
        IndicatorValuesResource resource = new IndicatorValuesResource(values);
        resource.getLinks().put("self", LinksUtility.getPricerIndicatorLink(indicatorId));
        resource.setId(indicatorId);
        
        return resource;
    }

    @Override
    public IndicatorResource getIndicator(String indicatorId) {
        
        IndicatorResource resource = pricerService.retrieveIndicator(indicatorId).toResource();
        
        resource.getLinks().put("self", LinksUtility.getPricerIndicatorLink(resource.getId()));
        
        return resource;
    }

    public PricerService getPricerService() {
        return pricerService;
    }

    public void setPricerService(PricerService pricerService) {
        this.pricerService = pricerService;
    }

}
