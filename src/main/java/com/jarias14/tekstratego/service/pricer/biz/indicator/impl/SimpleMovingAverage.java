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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.context.ContextLoader;

import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

/**
 * @author jarias14
 *
 * {
 *   "type": "simple_moving_average",
 *   "sizeOfBars": "ONE_DAY",
 *   "priceOfBars": "OPEN",
 *   "details": {
 *       "period": "3"
 *   }
 * }
 */
public class SimpleMovingAverage extends IndicatorBase {
    
    private static final long serialVersionUID = 8192599356783190029L;
    
    private int period;

    public SimpleMovingAverage() {
        
    }
    
    @Override
    public SortedMap<Calendar, BigDecimal> calculate(Stock stock, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, BigDecimal> priceHistory = getPriceHistory(stock, startDate, endDate);
        
        SortedMap<Calendar, BigDecimal> values = calculate(priceHistory, startDate, endDate);
        
        return values;
    }
    
    public SortedMap<Calendar, BigDecimal> calculate(SortedMap<Calendar, BigDecimal> prices, Calendar startDate, Calendar endDate) {
        
        SortedMap<Calendar, BigDecimal> values = new TreeMap<Calendar, BigDecimal>();
        
        Object[] valueList = prices.values().toArray();
        Object[] keyList = prices.keySet().toArray();
        
        // sum up first values
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < period-1; i++) {
            sum = sum.add((BigDecimal)valueList[i]);
            //values.put((Calendar)keyList[i], (BigDecimal)valueList[i]);
        }
        
        for (int i = period-1; i < valueList.length; i++) {
            sum = sum.add((BigDecimal) valueList[i]);
            
            BigDecimal value = sum.divide(new BigDecimal(period), 4, RoundingMode.HALF_EVEN);
            
            values.put((Calendar) keyList[i], value);
            
            sum = sum.subtract((BigDecimal)valueList[i-period+1]);
        }
        
        return values;
        
    }

    private SortedMap<Calendar, BigDecimal> getPriceHistory(Stock stock, Calendar startDate, Calendar endDate) {
        
        IndicatorDAO dao = (IndicatorDAO) ContextLoader.getCurrentWebApplicationContext().getBean("realIndicatorDAO");
        Calendar priceHistoryStartDate = dao.getStartDate(endDate, period-1, SizeOfBarsEnum.ONE_DAY);
        Calendar priceHistoryEndDate = (Calendar) endDate.clone();
        
        Price priceIndicator = new Price();
        priceIndicator.setSizeOfBars(this.getSizeOfBars());
        priceIndicator.setPriceOfBars(this.getPriceOfBars());
        
        SortedMap<Calendar, BigDecimal> priceHistory = priceIndicator.calculate(stock, priceHistoryStartDate, priceHistoryEndDate);
        
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
