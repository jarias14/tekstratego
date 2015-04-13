package com.jarias14.tekstratego.service.listener.dao.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.DataPointSize;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.models.StockExchange;
import com.jarias14.tekstratego.service.listener.biz.processors.RawDataProcessor;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 3/15/2015.
 */
public class YahooMarketDataDAOTest {

    private RawDataProcessor simulator;

    @Before
    public void setUp() {
        simulator = EasyMock.createMock(RawDataProcessor.class);
    }

    @Test
    public void encodingTest() {

        simulator.process(EasyMock.anyObject(Set.class));
        EasyMock.expectLastCall().times(124);


        YahooHistoricDataDao dao = new YahooHistoricDataDao();
        dao.setRawDataProcessor(simulator);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //objectMapper

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dao.setObjectMapper(objectMapper);

        Set<Stock> stocks = new HashSet<>();
        stocks.add(new Stock("YHOO", StockExchange.NYSE));


        long startTime=0, endTime=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startTime = sdf.parse("2009-09-11").getTime();
            endTime = sdf.parse("2010-03-10").getTime();
        } catch (Exception e) { }

        dao.request(new RawDataRequest(new Stock("YHOO", StockExchange.NYSE), new DataPointSize(TimeUnit.DAYS, 1), startTime, endTime));

    }
}
