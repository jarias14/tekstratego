package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;

public class IndicatorFactoryTest {
    
    @Test
    public void test() throws Exception {
        
        HashMap<String, String> details = new HashMap<String, String>();
        details.put("period", "3");
        
        IndicatorResource expected = new IndicatorResource();
        expected.setId("abc123");
        expected.setType("simple_moving_average");
        expected.setSizeOfBars("ONE_DAY");
        expected.setPriceOfBars("OPEN");
        expected.setDetails(details);
        
        boolean success = false;
        
        IndicatorBase actual = (IndicatorBase) IndicatorFactory.getIndicator(expected);
        
        if (actual instanceof SimpleMovingAverage) {
            success = true;
        }
        
        Assert.assertTrue(success);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getSizeOfBars(), actual.getSizeOfBars().toString());
        Assert.assertEquals((String)expected.getDetails().get("period"), String.valueOf(((SimpleMovingAverage)actual).getPeriod()));
    }

}
