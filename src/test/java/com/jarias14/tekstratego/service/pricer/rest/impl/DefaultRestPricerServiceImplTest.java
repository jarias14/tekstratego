package com.jarias14.tekstratego.service.pricer.rest.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class DefaultRestPricerServiceImplTest {
    
    @Resource(name="restPricerService")
    DefaultRestPricerServiceImpl webservice;
    
    @Test
    public void test() {
        HashMap<String,String> details = new HashMap<String,String>();
        details.put("period", "3");
        
        IndicatorResource resource = new IndicatorResource();
        resource.setDetails(details);
        resource.setIndicatorType("simpleMovingAverage");
        resource.setNumberOfBars("90");
        resource.setSizeOfBars("3");
        
        webservice.createIndicator(resource);
    }

    @Test
    public void testCalculate() {
        HashMap<String,String> details = new HashMap<String,String>();
        details.put("period", "3");
        
        IndicatorResource resource = new IndicatorResource();
        resource.setDetails(details);
        resource.setIndicatorType("simpleMovingAverage");
        resource.setSizeOfBars("ONE_DAY");
        
        resource = (IndicatorResource) webservice.createIndicator(resource);
        
        webservice.getValues(resource.getIndicatorId(), "AAPL", "ONE_DAY", "2012-03-14T12:13:00Z", "40");
    }
}
