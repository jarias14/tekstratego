package com.jarias14.tekstratego.service.listener.dao.impl;

import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.listener.dao.resource.YahooHistoricalDataResponse;
import com.jarias14.tekstratego.service.listener.dao.resource.YahooQuote;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/12/2015.
 */

@EnableAsync
public class YahooHistoricDataCallbackHandler {

    private Processor<Set<DataPointCollection>> rawDataProcessor;
    private DataPointSize dataPointSize;
    private Stock stock;

    @Async("historicMarketDataSimulatorExecutor")
    public void processYahooHistoricData(YahooHistoricalDataResponse response) {

        List<Set<DataPointCollection>> dataPointCollectionList = response.getQuery().getResults().getQuote().stream().map(this::getDataPointCollectionSet).collect(Collectors.toList());

        dataPointCollectionList.stream().forEach(rawDataProcessor::process);
    }

    private Set<DataPointCollection> getDataPointCollectionSet(YahooQuote yahooQuote) {

        long epochTime = getEpochTime(yahooQuote.getDate());

        DataPoint openDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getOpen()));
        DataPoint lowDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getLow()));
        DataPoint highDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getHigh()));
        DataPoint closeDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getClose()));
        DataPoint adjDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getAdjClose()));
        DataPoint volumeDataPoint = new DataPoint(epochTime, Double.valueOf(yahooQuote.getVolume()));


        Set<DataPointCollection> dataPointCollections = new HashSet<>();
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_OPEN, stock, dataPointSize, 1, openDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_LOW, stock, dataPointSize, 1, lowDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_HIGH, stock, dataPointSize, 1, highDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_CLOSE, stock, dataPointSize, 1, closeDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_ADJ, stock, dataPointSize, 1, adjDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_VOLUME, stock, dataPointSize, 1, volumeDataPoint));

        return dataPointCollections;
    }

    private long getEpochTime(String time){

        SimpleDateFormat df = new SimpleDateFormat("y-M-d");
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();
        return epoch;
    }

    public void setDataPointSize(DataPointSize dataPointSize) {
        this.dataPointSize = dataPointSize;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setRawDataProcessor(Processor<Set<DataPointCollection>> rawDataProcessor) {
        this.rawDataProcessor = rawDataProcessor;
    }
}
