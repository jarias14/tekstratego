package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public class IndicatorFactoryTest {
    
    @Test
    public void test() throws Exception {
        
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("period", "3");
        
        IndicatorResource expected = new IndicatorResource();
        expected.setIndicatorId("abc123");
        expected.setIndicatorType("simpleMovingAverage");
        expected.setNumberOfBars("7");
        expected.setSizeOfBars("60min");
        expected.setDetails(details);
        
        boolean success = false;
        
        IndicatorBase actual = (IndicatorBase) IndicatorFactory.getIndicator(expected);
        
        if (actual instanceof SimpleMovingAverage) {
            success = true;
        }
        
        Assert.assertTrue(success);
        Assert.assertEquals(expected.getIndicatorId(), actual.getId());
        Assert.assertEquals(expected.getNumberOfBars(), actual.getNumberOfBars());
        Assert.assertEquals(expected.getSizeOfBars(), actual.getSizeOfBars());
        Assert.assertEquals((String)expected.getDetails().get("period"), String.valueOf(((SimpleMovingAverage)actual).getPeriod()));
    }

}
