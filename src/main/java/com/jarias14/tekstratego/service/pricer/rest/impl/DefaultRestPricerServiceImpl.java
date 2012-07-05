package com.jarias14.tekstratego.service.pricer.rest.impl;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.pricer.biz.PricerService;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorFactory;
import com.jarias14.tekstratego.service.pricer.rest.RestPricerService;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorValuesResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

public class DefaultRestPricerServiceImpl implements RestPricerService {
    
    private PricerService pricerService;
    private LinksUtility linksUtility = new LinksUtility();

    @Override
    public BaseResource createIndicator(IndicatorResource resource) {
        
        Indicator model = null;
        model = IndicatorFactory.getIndicator(resource); //from resource to model
        model = pricerService.createIndicator(model);  //save to memory
        
        resource = model.toResource();  //back to resource, now with indicatorId
        resource.getLinks().add(linksUtility.getPricerIndicatorLink("self", resource.getIndicatorId()));
        
        return resource;
    }

    @Override
    public IndicatorValuesResource getValues(String indicatorId, String stockId, String sizeOfBars, String startDate, String numberOfBars) {
        
        SortedMap<Date,Double> values = pricerService.calculateIndicator(indicatorId, stockId, sizeOfBars, startDate, numberOfBars);
        
        IndicatorValuesResource resource = new IndicatorValuesResource(values);

        return resource;
    }

    @Override
    public IndicatorResource getIndicator(String indicatorId) {
        
        IndicatorResource resource = pricerService.retrieveIndicator(indicatorId).toResource();
        
        resource.getLinks().add(linksUtility.getPricerIndicatorLink("self", resource.getIndicatorId()));
        
        return resource;
    }

    public PricerService getPricerService() {
        return pricerService;
    }

    public void setPricerService(PricerService pricerService) {
        this.pricerService = pricerService;
    }

}
