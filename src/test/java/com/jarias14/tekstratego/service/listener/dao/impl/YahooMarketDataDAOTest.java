package com.jarias14.tekstratego.service.listener.dao.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.models.StockExchange;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jarias14 on 3/15/2015.
 */
public class YahooMarketDataDAOTest {

    @Test
    public void encodingTest() {

        YahooMarketDataDAO dao = new YahooMarketDataDAO();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //objectMapper

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dao.setObjectMapper(objectMapper);

        Set<Stock> stocks = new HashSet<>();
        stocks.add(new Stock("YHOO", StockExchange.NYSE));

        dao.getPriceBars("YHOO", "2009-09-11", "2010-03-10");

    }
}
