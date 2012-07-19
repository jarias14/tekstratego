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
    public TransactionResource createTrade(String symbol, String amount, String isBackTesting, String barTime, String barSize) {
        
        
        String postUrl = "http://localhost:8080/tekstratego/pricer-service/indicators";
        
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.setPriceOfBars("LOW");
        request.setType("price");
        request.setSizeOfBars(barSize);
        
        IndicatorResource indicator = getRestTemplate().postForObject(postUrl, request, IndicatorResource.class);
        
        // prepare rest call
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("indicator", indicator.getId());
        replacements.put("symbol", symbol);
        
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("endDate", barTime);
        parameters.put("numberOfBars", "0");
        
        String url = LinksUtility.getUrl(LinksUtility.PRICER_INDICATOR_VALUES, replacements, parameters);
        
        // make rest call
        IndicatorValuesResource values =
                getRestTemplate().getForObject(url, IndicatorValuesResource.class);
        
        Double sharesPrice = values.getValues().get(values.getValues().firstKey());
        Integer sharesBought = (int) Math.floor((Double.valueOf(amount) / sharesPrice));
        
        TransactionResource resource = new TransactionResource();
        resource.setSharesPrice(String.valueOf(sharesPrice));
        resource.setSharesNumber(String.valueOf(sharesBought));
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
