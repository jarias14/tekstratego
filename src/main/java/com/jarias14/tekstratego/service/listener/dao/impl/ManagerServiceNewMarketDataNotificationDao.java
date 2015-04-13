package com.jarias14.tekstratego.service.listener.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.service.manager.rest.MarketDataNotification;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class ManagerServiceNewMarketDataNotificationDao implements DataAccessObject<MarketDataNotification, Boolean> {

    private ObjectMapper objectMapper;
    private static final String THINKER_STRATEGY_DECISION_URL_BASE = "http://localhost:8082/manager-service/accounts?newDataStock={stock-symbol}&newDataTime={epoch-time}";

    @Override
    public Boolean request(MarketDataNotification marketDataNotification) {
        getRestTemplate().put(THINKER_STRATEGY_DECISION_URL_BASE, Boolean.class, getUriVariablesMap(marketDataNotification.getStock(), marketDataNotification.getTime()));
        return true;
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

    private Map<String, String> getUriVariablesMap(Stock stock, long time) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("epoch-time", String.valueOf(time));
        uriVariables.put("stock-symbol", stock.getSymbol());
        return uriVariables;
    }

    @Required
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
