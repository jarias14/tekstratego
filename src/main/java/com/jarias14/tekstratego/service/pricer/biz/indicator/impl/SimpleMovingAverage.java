/*
 * A simple moving average is a method for computing an average of a stream of numbers by only
 * averaging the last P numbers from the stream, where P is known as the period. It can be implemented
 * by calling an initialing routine with P as its argument, I(P), which should then return a routine
 * that when called with individual, successive members of a stream of numbers, computes the mean of (up to),
 * the last P of them, lets call this SMA().
 * 
 * The word stateful in the task description refers to the need for SMA() to remember certain information
 * between calls to it:
 * - The period, P
 * - An ordered container of at least the last P numbers from each of its individual calls.
 * 
 * Stateful also means that successive calls to I(), the initializer, should return separate routines that do not share saved state so they could be used on two independent streams of data.
 * 
 * Source:
 * http://rosettacode.org/wiki/Averages/Simple_moving_average
 */

package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;

public class SimpleMovingAverage extends IndicatorBase {
    
    private static final long serialVersionUID = 8192599356783190029L;
    
    private int period;

    public SimpleMovingAverage() {
        
    }
    
    @Override
    public SortedMap<Calendar, Double> calculate(Stock stock, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, Double> priceHistory = getPriceHistory(stock, startDate, endDate);
        
        SortedMap<Calendar, Double> values = calculate(priceHistory, startDate, endDate);
        
        return values;
    }
    
    private SortedMap<Calendar, Double> calculate(SortedMap<Calendar, Double> prices, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, Double> values = new TreeMap<Calendar, Double>();
        
        Object[] valueList = prices.values().toArray();
        Object[] keyList = prices.keySet().toArray();
        
        double sum = 0;
        
        for (int i = 0; i < valueList.length; i++) {
            
            sum = sum + (Double) valueList[i];
            sum = sum - (Double) (i >= period ? valueList[i-period] : 0.0);
            
            if (((Calendar)keyList[i]).compareTo(startDate) >= 0 && ((Calendar)keyList[i]).compareTo(endDate) <= 0) {
                Double value = sum / period; 
                values.put((Calendar) keyList[i], value);
            }
        }
        
        return values;
        
    }

    private SortedMap<Calendar, Double> getPriceHistory(Stock stock, Calendar startDate, Calendar endDate) {
        
        Calendar priceHistoryStartDate = (Calendar) startDate.clone();
        priceHistoryStartDate.add(this.getSizeOfBars().getTimeUnit(), -this.getSizeOfBars().getTimeValue()*period*2);
        Calendar priceHistoryEndDate = (Calendar) endDate.clone();
        priceHistoryEndDate.add(this.getSizeOfBars().getTimeUnit(), this.getSizeOfBars().getTimeValue()*period*2);
        
        Price priceIndicator = new Price();
        priceIndicator.setSizeOfBars(this.getSizeOfBars());
        priceIndicator.setPriceOfBars(this.getPriceOfBars());
        
        SortedMap<Calendar, Double> priceHistory = priceIndicator.calculate(stock, priceHistoryStartDate, priceHistoryEndDate);
        
        return priceHistory;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
