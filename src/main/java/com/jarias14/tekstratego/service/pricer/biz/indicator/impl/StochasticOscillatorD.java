package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;


/**
 * {
 *   "type": "price",
 *   "sizeOfBars": "ONE_DAY",
 *   "priceOfBars": "OPEN"
 * }
 * 
 */
public class StochasticOscillatorD extends IndicatorBase {

    private static final long serialVersionUID = 1L;
    
    private PriceOfBarsEnum priceOfBars;

    public StochasticOscillatorD() {
        
    }
    
    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, BigDecimal> values = new TreeMap<Calendar, BigDecimal>();
        
        populateValues(values, stock, startDate, endDate, this.getSizeOfBars());
        
        return values;
    }
    
    private void populateValues(SortedMap<Calendar, BigDecimal> values, Stock stock, Calendar startDate, Calendar endDate, SizeOfBarsEnum sizeOfBars) {
        
        // NICETOHAVE: create singleton factory for DAOs
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
