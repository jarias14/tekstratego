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
        resource.setType("simple_moving_average");
        resource.setSizeOfBars("ONE_DAY");
        resource.setPriceOfBars("OPEN");
        
        webservice.createIndicator(resource);
    }

}
