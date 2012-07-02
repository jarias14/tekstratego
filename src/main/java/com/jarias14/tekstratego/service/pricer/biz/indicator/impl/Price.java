package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.models.SizeOfBars;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public class Price extends IndicatorBase {

    private static final long serialVersionUID = 1L;

    public Price(IndicatorResource resource) {
        super(resource);
    }
    
    @Override
    public SortedMap<Date, Double> calculate(Stock stock, Date startDate, SizeOfBars sizeOfBars, int numberOfBars) {
        
        SortedMap<Date, Double> values = new TreeMap<Date, Double>();
        
        populateValues(values, stock, startDate, sizeOfBars, numberOfBars);
        
        return values;
    }
    
    private void populateValues(SortedMap<Date, Double> values, Stock stock, Date startDate, SizeOfBars sizeOfBars, int numberOfBars) {
        
        // TODO: create singleton factory for DAOs
        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("testBean");

        values.putAll(dao.readPrices(stock, sizeOfBars, startDate, numberOfBars));
    }

    public IndicatorResource toResource() {
        
        // populate resource from base class
        IndicatorResource resource = super.toResource();
        
        return resource;
    }
}
