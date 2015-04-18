package com.jarias14.tekstratego.service.manager.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.models.TradeRequest;
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
 * Created by jarias14 on 4/12/2015.
 */
public class TraderServiceDaoImpl implements DataAccessObject<TradeRequest, Position> {

    private ObjectMapper objectMapper;
    private static final String TRADER_TRADE_URL_BASE = "http://localhost:8082/tekstratego/trader-service/trades";

    @Override
    public Position request(TradeRequest request) {
        Position position = getRestTemplate().postForObject(TRADER_TRADE_URL_BASE, request, Position.class);

        return position;
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

    private Map<String, String> getUriVariablesMap(String strategyId, Stock stock, long time) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("strategy-id", strategyId);
        uriVariables.put("stock-symbol", stock.getSymbol());
        uriVariables.put("stock-exchange", stock.getExchange().name());
        uriVariables.put("epoch-time", String.valueOf(time));
        return uriVariables;
    }

    @Required
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
