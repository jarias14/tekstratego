package com.jarias14.tekstratego.service.listener.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.service.listener.dao.resource.YahooHistoricalDataResponse;
import com.jarias14.tekstratego.service.listener.dao.resource.YahooQuote;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 3/15/2015.
 */
public class YahooMarketDataDAO  {

    private ObjectMapper objectMapper;
    private static final String YAHOO_FINANCE_URL_BASE = "https://query.yahooapis.com/v1/public/yql?format={format}&env={env}&q={q}";
    private static final String YAHOO_FINANCE_URL_QUERY = "select * from yahoo.finance.historicaldata where symbol in (%s) and startDate = \"%s\" and endDate = \"%s\"";
    private static final String YAHOO_FINANCE_URL_FORMAT_PARAM = "json";
    private static final String YAHOO_FINANCE_URL_ENV_PARAM = "store://datatables.org/alltableswithkeys";
    private static final String YAHOO_FINANCE_URL_STOCKS = "\"%s\"";

    public Set<DataPointCollection<Double>> getPriceBars(String symbol, String startDate, String endDate) {
        if (StringUtils.isEmpty(symbol) || StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            return null;
        }

        YahooHistoricalDataResponse response = getRestTemplate().getForObject(
                YAHOO_FINANCE_URL_BASE, YahooHistoricalDataResponse.class, getUriVariablesMap(symbol, startDate, endDate));

        Set<DataPointCollection<Double>> indicators = getDataPointColletion(response, symbol);

        return indicators;
    }

    private Map<String, String> getUriVariablesMap(String symbol, String startDate, String endDate) {

        symbol = String.format(YAHOO_FINANCE_URL_STOCKS, symbol);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("format", YAHOO_FINANCE_URL_FORMAT_PARAM);
        uriVariables.put("env", YAHOO_FINANCE_URL_ENV_PARAM);
        uriVariables.put("q", String.format(YAHOO_FINANCE_URL_QUERY, symbol, startDate, endDate));

        return uriVariables;
    }

    private Set<DataPointCollection<Double>> getDataPointColletion(YahooHistoricalDataResponse response, String symbol) {

        Set<DataPointCollection<Double>> dataPointCollections =
                Arrays.asList(DataPointIndicator.values()).stream()
                        .map(indicator -> {
                            return new DataPointCollection<Double>(indicator, symbol, "NYSE", TimeUnit.DAYS, 1);
                        }).collect(Collectors.toSet());

        response.getQuery().getResults().getQuote().stream()
                .forEach(quote -> {
                    dataPointCollections.parallelStream()
                            .forEach(dataPointCollection -> {
                                DataPoint<Double> dataPoint = new DataPoint<Double>();
                                dataPoint.setTime(getEpochTime(quote.getDate()));
                                dataPoint.setValue(getPrice(quote, dataPointCollection.getDetails().getIndicator()));

                                dataPointCollection.getDataPoints().add(dataPoint);
                            });
                });

        return dataPointCollections;
    }

    private double getPrice(YahooQuote quote, DataPointIndicator indicator) {
        switch (indicator) {
            case OPEN: return quote.getOpen();
            case LOW: return quote.getLow();
            case HIGH: return quote.getHigh();
            case CLOSE: return quote.getClose();
            case ADJ: return quote.getAdjClose();
            default: return quote.getAdjClose();
        }
    }

    private long getEpochTime(String time){

        SimpleDateFormat df = new SimpleDateFormat(
                "y-M-d");
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();
        return epoch;
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
}
