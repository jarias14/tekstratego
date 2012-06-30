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

import java.util.HashMap;

import com.jarias14.tekstratego.service.pricer.biz.indicator.IndicatorBase;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public class SimpleMovingAverage extends IndicatorBase {
    
    private static final long serialVersionUID = 8192599356783190029L;
    
    private int period;

    public SimpleMovingAverage(IndicatorResource resource) {
        super(resource);
        period = 3;
    }
    
    public IndicatorResource toResource() {
        
        IndicatorResource resource = super.toResource();
        
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("period", String.valueOf(period));
        resource.setDetails(details);
        
        return resource;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
