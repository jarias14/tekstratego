package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class StochasticOscillatorD extends StochasticOscillatorK {

    private static final long serialVersionUID = 1L;

    private int smoothing;

    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {

        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");
        Calendar priceHistoryStartDate = dao.getStartDate(startDate, smoothing-1, SizeOfBarsEnum.ONE_DAY);
        
        SortedMap<Calendar, BigDecimal> D = super.calculate(stock, priceHistoryStartDate, endDate);
        
        SimpleMovingAverage sma = new SimpleMovingAverage();
        sma.setPeriod(this.smoothing);
        sma.setPriceOfBars(getPriceOfBars());
        sma.setSizeOfBars(getSizeOfBars());
        
        SortedMap<Calendar, BigDecimal> values = sma.calculate(D, startDate, endDate); 
        
        BigDecimal lastKey = values.get(endDate);
        values = values.subMap(startDate, endDate);
        values.put(endDate, lastKey);
        
        return values;
    }

    public int getSmoothing() {
        return smoothing;
    }

    public void setSmoothing(int smoothing) {
        this.smoothing = smoothing;
    }
}
