package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DifferenceIndicator extends IndicatorBase {

    private static final long serialVersionUID = 1L;
    
    private IndicatorBase indicatorOne, indicatorTwo;

    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, BigDecimal> valuesOne = indicatorOne.calculate(stock, startDate, endDate);
        SortedMap<Calendar, BigDecimal> valuesTwo = indicatorTwo.calculate(stock, startDate, endDate);
        
        SortedMap<Calendar, BigDecimal> values = calculate(valuesOne, valuesTwo, startDate, endDate);
        values = values.subMap(startDate, endDate);
        
        return values;
    }

    private SortedMap<Calendar, BigDecimal> calculate(
            SortedMap<Calendar, BigDecimal> valuesOne,
            SortedMap<Calendar, BigDecimal> valuesTwo,
            Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, BigDecimal> values = new TreeMap<Calendar, BigDecimal>();
        
        for (Calendar key : valuesOne.keySet()) {
            BigDecimal value = valuesOne.get(key).subtract(valuesTwo.get(key)); 
            values.put(key, value);
        }
        
        return values;
    }


    public IndicatorResource toResource() {
        
        // populate resource from base class
        IndicatorResource resource = super.toResource();
        
        // populate resource from this class
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("indicatorOne", indicatorOne.getId());
        details.put("indicatorTwo", indicatorTwo.getId());
        resource.setDetails(details);
        
        return resource;
    }

    public void fromResource(IndicatorResource resource) {
        super.fromResource(resource);
        String indicatorOneId = (String)resource.getDetails().get("indicatorOne");
        String indicatorTwoId = (String)resource.getDetails().get("indicatorTwo");
        
        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");
        
        indicatorOne = (IndicatorBase) dao.readIndicator(indicatorOneId);
        indicatorTwo = (IndicatorBase) dao.readIndicator(indicatorTwoId);
    }

    public IndicatorBase getIndicatorOne() {
        return indicatorOne;
    }

    public void setIndicatorOne(IndicatorBase indicatorOne) {
        this.indicatorOne = indicatorOne;
    }

    public IndicatorBase getIndicatorTwo() {
        return indicatorTwo;
    }

    public void setIndicatorTwo(IndicatorBase indicatorTwo) {
        this.indicatorTwo = indicatorTwo;
    }
}
