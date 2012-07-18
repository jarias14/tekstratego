package com.jarias14.tekstratego.service.trader.rest.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.common.resource.IndicatorValuesResource;
import com.jarias14.tekstratego.common.resource.TransactionResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.trader.rest.RestTraderService;

public class DefaultRestTraderServiceImpl implements RestTraderService {

    @Override
    public TransactionResource createTrade(String symbol, String shares, String isBackTesting, String barTime, String barSize) {
        
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("type", "price");
        replacements.put("sizeOfBars", "ONE_DAY");
        replacements.put("priceOfBars", "LOW");
        
        String url = LinksUtility.getUrl(LinksUtility.PRICER_INDICATOR, replacements, new HashMap<String,String>());
        
        IndicatorResource indicator = getRestTemplate().postForObject(url, null, IndicatorResource.class);
        
        
        // prepare rest call
        replacements = new HashMap<String,String>();
        replacements.put("indicator", indicator.getId());
        
        url = LinksUtility.getUrl(LinksUtility.PRICER_INDICATOR_VALUES, replacements, new HashMap<String,String>());
        
        // make rest call
        IndicatorValuesResource values =
                getRestTemplate().postForObject(url, null, IndicatorValuesResource.class);
        
        TransactionResource resource = new TransactionResource();
        resource.setSharesPrice(String.valueOf(values.getValues().get(values.getValues().firstKey())));
        resource.setSharesNumber(shares);
        resource.setSharesPrice(String.valueOf(values.getValues().get(barTime)));
        return resource;
    }
    
    private RestTemplate getRestTemplate(){
        
        RestTemplate restTemplate = new RestTemplate();
        
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        return restTemplate;
    }

}
