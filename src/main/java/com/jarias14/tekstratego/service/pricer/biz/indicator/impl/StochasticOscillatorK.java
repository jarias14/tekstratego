package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class StochasticOscillatorK extends IndicatorBase {

    private static final long serialVersionUID = 1L;
    
    private int period;

    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {

        SortedMap<Calendar, BigDecimal> lowsPriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.LOW);
        SortedMap<Calendar, BigDecimal> highsPriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.HIGH);
        SortedMap<Calendar, BigDecimal> closePriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.CLOSE);
        
        SortedMap<Calendar, BigDecimal> values = calculate(lowsPriceHistory, highsPriceHistory, closePriceHistory, startDate, endDate);
        
        return values;
    }

    public SortedMap<Calendar, BigDecimal> calculate(SortedMap<Calendar, BigDecimal> lows, SortedMap<Calendar, BigDecimal> highs, SortedMap<Calendar, BigDecimal> closes, Calendar startDate, Calendar endDate) {
        
        // %K = 100*SUM (CLOSE - MIN (LOW, Pk), Sk) / SUM (MAX (HIGH, Pk) - MIN (LOW, Pk)), Sk)
        
        SortedMap<Calendar, BigDecimal> values = new TreeMap<Calendar, BigDecimal>();
        
        Object[] lowsList = lows.values().toArray();
        Object[] highsList = highs.values().toArray();
        Object[] closeList = closes.values().toArray();
        Object[] keyList = highs.keySet().toArray();
        
        for (int i = period-1; i < keyList.length; i++) {
            
            //find all variables
            BigDecimal lowest = findLowestPrice(lowsList, i);
            BigDecimal highest = findHighestPrice(highsList, i);
            BigDecimal close = (BigDecimal)closeList[i];
            
            //find our value
            BigDecimal closeMinusLow = close.subtract(lowest);
            BigDecimal highMinusLow = highest.subtract(lowest);
            BigDecimal oneHundred = new BigDecimal(100);
            
            BigDecimal value = oneHundred.multiply(closeMinusLow).divide(highMinusLow, 4, RoundingMode.HALF_EVEN);
            
            //put it on the list
            values.put((Calendar) keyList[i], value);
        }
        
        return values.subMap(startDate, endDate);
        
    }
    
    private BigDecimal findLowestPrice(Object[] prices, int index) {
        
        int startIndex = Math.max(index-period+1, 0);
        int endIndex = index;
        
        BigDecimal lowest = (BigDecimal)prices[startIndex];
        
        for (int i = startIndex; i <= endIndex; i++) {
            
            if (((BigDecimal)prices[i]).compareTo(lowest) < 0) {
                
                lowest = (BigDecimal)prices[i];
            }
        }
        
        return lowest;
    }
    
    private BigDecimal findHighestPrice(Object[] prices, int index) {
        
        BigDecimal highest = (BigDecimal)prices[index-period+1];
        
        for (int i = index-period+1; i <= index; i++) {
            
            if (((BigDecimal)prices[i]).compareTo(highest) > 0) {
                
                highest = (BigDecimal)prices[i];
            }
        }
        
        return highest;
    }
    
    private SortedMap<Calendar, BigDecimal> getPriceHistory(Stock stock, Calendar startDate, Calendar endDate, PriceOfBarsEnum priceOfBars) {
        
        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");
        Calendar priceHistoryStartDate = dao.getStartDate(startDate, period-1, SizeOfBarsEnum.ONE_DAY);
        Calendar priceHistoryEndDate = (Calendar) endDate.clone();
        //Calendar priceHistoryStartDate = (Calendar) startDate.clone();
        //priceHistoryStartDate.add(this.getSizeOfBars().getTimeUnit(), -this.getSizeOfBars().getTimeValue()*period*2);
        
        Price priceIndicator = new Price();
        priceIndicator.setSizeOfBars(this.getSizeOfBars());
        priceIndicator.setPriceOfBars(priceOfBars);
        
        SortedMap<Calendar, BigDecimal> priceHistory = priceIndicator.calculate(stock, priceHistoryStartDate, priceHistoryEndDate);
        
        return priceHistory;
    }
    
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public IndicatorResource toResource() {
        
        // populate resource from base class
        IndicatorResource resource = super.toResource();
        
        // populate resource from this class
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("period", String.valueOf(period));
        resource.setDetails(details);
        
        return resource;
    }
    
    public void fromResource(IndicatorResource resource) {
        super.fromResource(resource);
        period = Integer.parseInt((String)resource.getDetails().get("period"));
    }
}
