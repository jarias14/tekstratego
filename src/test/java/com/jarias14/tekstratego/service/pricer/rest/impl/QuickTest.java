package com.jarias14.tekstratego.service.pricer.rest.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.jarias14.tekstratego.common.resource.IndicatorResource;

public class QuickTest {

    @Ignore
    public void test() {
        
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        String postUrl = "http://localhost:8080/tekstratego/pricer-service/indicators";
        
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "4");
        request.getDetails().put("priceOfBars", "OPEN");
        request.setType("simpleMovingAverage");
        request.setSizeOfBars("ONE_DAY");
        
        IndicatorResource postResult = restTemplate.postForObject(postUrl, request, IndicatorResource.class);
        
        String url = "http://localhost:8080/tekstratego/pricer-service/indicators/{ID}";
        
        IndicatorResource getResult = restTemplate.getForObject(url, IndicatorResource.class, postResult.getId());
        
        Assert.assertEquals(postResult.getId(), getResult.getId());
        //Assert.assertEquals("20", getResult.getNumberOfBars());
        Assert.assertEquals("4", getResult.getDetails().get("period"));
        Assert.assertEquals("ONE_DAY", getResult.getSizeOfBars());
    }
}


