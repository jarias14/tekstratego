package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class StochasticOscillatorD extends StochasticOscillatorK {

    private static final long serialVersionUID = 1L;

    private int smoothing;

    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {

        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");
        Calendar priceHistoryStartDate = dao.getStartDate(startDate, smoothing-1, SizeOfBarsEnum.ONE_DAY);
        Calendar priceHistoryEndDate = dao.getStartDate(endDate, -1, SizeOfBarsEnum.ONE_DAY);
        
        SortedMap<Calendar, BigDecimal> D = super.calculate(stock, priceHistoryStartDate, priceHistoryEndDate);
        
        SimpleMovingAverage sma = new SimpleMovingAverage();
        sma.setPeriod(this.smoothing);
        sma.setPriceOfBars(getPriceOfBars());
        sma.setSizeOfBars(getSizeOfBars());
        
        SortedMap<Calendar, BigDecimal> values = sma.calculate(D, startDate, endDate);
        values = values.subMap(startDate, priceHistoryEndDate);
        
        return values;
    }

    public int getSmoothing() {
        return smoothing;
    }

    public void setSmoothing(int smoothing) {
        this.smoothing = smoothing;
    }
    

    public IndicatorResource toResource() {
        
        // populate resource from base class
        IndicatorResource resource = super.toResource();
        
        // populate resource from this class
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("period", String.valueOf(super.getPeriod()));
        details.put("smoothing", String.valueOf(smoothing));
        resource.setDetails(details);
        
        return resource;
    }

    public void fromResource(IndicatorResource resource) {
        super.fromResource(resource);
        smoothing = Integer.parseInt((String)resource.getDetails().get("smoothing"));
    }
}
