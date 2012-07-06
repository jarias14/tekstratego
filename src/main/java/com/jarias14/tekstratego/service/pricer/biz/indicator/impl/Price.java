package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public class Price extends IndicatorBase {

    private static final long serialVersionUID = 1L;
    
    private PriceOfBarsEnum priceOfBars;

    public Price() {
        
    }
    
    @Override
    public SortedMap<Date, Double> calculate(Stock stock, Date startDate, Date endDate) {
        
        SortedMap<Date, Double> values = new TreeMap<Date, Double>();
        
        populateValues(values, stock, startDate, endDate, this.getSizeOfBars());
        
        return values;
    }
    
    private void populateValues(SortedMap<Date, Double> values, Stock stock, Date startDate, Date endDate, SizeOfBarsEnum sizeOfBars) {
        
        // TODO: create singleton factory for DAOs
        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");

        values.putAll(dao.readPrices(stock, sizeOfBars, this.priceOfBars, startDate, endDate));
    }

    public IndicatorResource toResource() {
        
        // populate resource from base class
        IndicatorResource resource = super.toResource();
        
        return resource;
    }
    
    public void fromResource(IndicatorResource resource) {
        this.priceOfBars = PriceOfBarsEnum.valueOf(resource.getPriceOfBars().toUpperCase());
        super.fromResource(resource);
    }

    public PriceOfBarsEnum getPriceOfBars() {
        return priceOfBars;
    }

    public void setPriceOfBars(PriceOfBarsEnum priceOfBars) {
        this.priceOfBars = priceOfBars;
    }
}
