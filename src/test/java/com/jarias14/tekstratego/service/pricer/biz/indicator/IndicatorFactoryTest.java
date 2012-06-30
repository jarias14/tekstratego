package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;

public class IndicatorFactoryTest {
    
    @Test
    public void test() throws Exception {
        
        boolean success = false;
        
        IndicatorBase indicator = IndicatorFactory.getIndicator("simpleMovingAverage", new HashMap<String,Object>());
        
        if (indicator instanceof SimpleMovingAverage) {
            success = true;
        }
        
        Assert.assertTrue(success);
    }

}
