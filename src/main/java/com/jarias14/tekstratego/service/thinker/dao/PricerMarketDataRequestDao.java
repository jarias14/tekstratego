package com.jarias14.tekstratego.service.thinker.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.MarketDataRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jarias14 on 4/14/2015.
 */
public class PricerMarketDataRequestDao implements DataAccessObject<MarketDataRequest, Double> {

    private ObjectMapper objectMapper;
    private static final String PRICER_MARKET_DATA_REQUEST_URL_BASE = "http://localhost:8082/tekstratego/pricer-service/indicators/{indicator-id}/exchange/{stock-exchange}/stock/{stock-symbol}/time/{epoch-time}";

    @Override
    public Double request(MarketDataRequest request) {
        DataPoint dataPoint = getRestTemplate().getForObject(PRICER_MARKET_DATA_REQUEST_URL_BASE, DataPoint.class, getUriVariablesMap(request));
        return (Double) dataPoint.getValue();
    }

    private RestTemplate getRestTemplate(){

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jacksonHttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jacksonHttpMessageConverter);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

    private Map<String, String> getUriVariablesMap(MarketDataRequest request) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("indicator-id", request.getId());
        uriVariables.put("stock-exchange", request.getStock().getExchange().name());
        uriVariables.put("stock-symbol", request.getStock().getSymbol());
        uriVariables.put("epoch-time", String.valueOf(request.getTime()));

        return uriVariables;
    }

    @Required
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
