package com.jarias14.tekstratego.service.listener.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.listener.dao.resource.YahooHistoricalDataResponse;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jarias14 on 3/15/2015.
 */
public class YahooHistoricDataDao implements DataAccessObject<RawDataRequest, Boolean> {

    private Processor<Set<DataPointCollection>> historicBarProcessor;
    private YahooHistoricDataCallbackHandler dataCallbackHandler;
    private ObjectMapper objectMapper;
    private static final String YAHOO_FINANCE_URL_BASE = "https://query.yahooapis.com/v1/public/yql?format={format}&env={env}&q={q}";
    private static final String YAHOO_FINANCE_URL_QUERY = "select * from yahoo.finance.historicaldata where symbol in (%s) and startDate = \"%s\" and endDate = \"%s\"";
    private static final String YAHOO_FINANCE_URL_FORMAT_PARAM = "json";
    private static final String YAHOO_FINANCE_URL_ENV_PARAM = "store://datatables.org/alltableswithkeys";
    private static final String YAHOO_FINANCE_URL_STOCKS = "\"%s\"";


    @Override
    public Boolean request(RawDataRequest request) {

        YahooHistoricalDataResponse response = getRestTemplate().getForObject(
                YAHOO_FINANCE_URL_BASE, YahooHistoricalDataResponse.class, getUriVariablesMap(request.getStock().getSymbol(), request.getStartTime(), request.getEndTime()));

        dataCallbackHandler = new YahooHistoricDataCallbackHandler();
        dataCallbackHandler.setStock(request.getStock());
        dataCallbackHandler.setDataPointSize(request.getDataPointSize());
        dataCallbackHandler.setHistoricDataProcessor(historicBarProcessor);

        dataCallbackHandler.processYahooHistoricData(response);

        return true;
    }

    private Map<String, String> getUriVariablesMap(String symbol, long startDateInMillis, long endDateInMillis) {

        Date startDate = new Date(startDateInMillis);
        Date endDate = new Date(endDateInMillis);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = df.format(startDate);
        String formattedEndDate = df.format(endDate);

        symbol = String.format(YAHOO_FINANCE_URL_STOCKS, symbol);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("format", YAHOO_FINANCE_URL_FORMAT_PARAM);
        uriVariables.put("env", YAHOO_FINANCE_URL_ENV_PARAM);
        uriVariables.put("q", String.format(YAHOO_FINANCE_URL_QUERY, symbol, formattedStartDate, formattedEndDate));

        return uriVariables;
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

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Required
    public void setHistoricBarProcessor(Processor<Set<DataPointCollection>> historicBarProcessor) {
        this.historicBarProcessor = historicBarProcessor;
    }
}
