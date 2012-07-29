package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;

public class StochasticOscillatorK extends IndicatorBase {

    private static final long serialVersionUID = 1L;
    
    private int period;

    private int smoothing;

    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {

        SortedMap<Calendar, BigDecimal> lowsPriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.LOW);
        SortedMap<Calendar, BigDecimal> highsPriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.HIGH);
        SortedMap<Calendar, BigDecimal> closePriceHistory = getPriceHistory(stock, startDate, endDate, PriceOfBarsEnum.CLOSE);
        
        SortedMap<Calendar, BigDecimal> values = calculate(lowsPriceHistory, highsPriceHistory, closePriceHistory, startDate, endDate);
        
        return values;
    }

    private SortedMap<Calendar, BigDecimal> calculate(SortedMap<Calendar, BigDecimal> lows, SortedMap<Calendar, BigDecimal> highs, SortedMap<Calendar, BigDecimal> closes, Calendar startDate, Calendar endDate) {
        
        // %K = 100*SUM (CLOSE - MIN (LOW, Pk), Sk) / SUM (MAX (HIGH, Pk) - MIN (LOW, Pk)), Sk)
        
        SortedMap<Calendar, BigDecimal> values = new TreeMap<Calendar, BigDecimal>();
        
        Object[] lowsList = lows.values().toArray();
        Object[] highsList = highs.values().toArray();
        Object[] closeList = closes.values().toArray();
        Object[] keyList = highs.keySet().toArray();
        
        for (int i = period; i < keyList.length; i++) {
            
            //find all variables
            BigDecimal lowest = findLowestPrice((BigDecimal[])lowsList, i);
            BigDecimal highest = findHighestPrice((BigDecimal[])highsList, i);
            BigDecimal close = (BigDecimal)closeList[i];
            
            //find our value
            BigDecimal value = (new BigDecimal(100)).multiply((close.subtract(lowest)).divide((highest.subtract(lowest))));
            
            //put it on the list
            values.put((Calendar) keyList[i], value);
        }
        
        return values;
        
    }
    
    private BigDecimal findLowestPrice(BigDecimal[] prices, int index) {
        
        BigDecimal lowest = prices[index];
        
        for (int i = index-smoothing+1; i <= index; i++) {
            
            if (prices[i].compareTo(lowest) < 0) {
                
                lowest = prices[i];
            }
        }
        
        return lowest;
    }
    
    private BigDecimal findHighestPrice(BigDecimal[] prices, int index) {
        
        BigDecimal highest = prices[index];
        
        for (int i = index-smoothing+1; i <= index; i++) {
            
            if (prices[i].compareTo(highest) > 0) {
                
                highest = prices[i];
            }
        }
        
        return highest;
    }
    
    private SortedMap<Calendar, BigDecimal> getPriceHistory(Stock stock, Calendar startDate, Calendar endDate, PriceOfBarsEnum priceOfBars) {
        
        Calendar priceHistoryStartDate = (Calendar) startDate.clone();
        priceHistoryStartDate.add(this.getSizeOfBars().getTimeUnit(), -this.getSizeOfBars().getTimeValue()*period*2);
        Calendar priceHistoryEndDate = (Calendar) endDate.clone();
        priceHistoryEndDate.add(this.getSizeOfBars().getTimeUnit(), this.getSizeOfBars().getTimeValue()*period*2);
        
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

    public int getSmoothing() {
        return smoothing;
    }

    public void setSmoothing(int smoothing) {
        this.smoothing = smoothing;
    }
}
