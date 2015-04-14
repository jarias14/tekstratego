package com.jarias14.tekstratego.service.listener.dao.impl;

import com.ib.controller.ApiController;
import com.ib.controller.Bar;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.Processor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class InteractiveBrokersLiveDataCallbackHandler implements ApiController.IRealTimeBarHandler {

    private Processor<Set<DataPointCollection>> rawDataProcessor;
    private DataPointSize dataPointSize;
    private Stock stock;

    @Override
    public void realtimeBar(Bar bar) {
        DataPoint openDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint lowDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint highDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint closeDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint adjDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint volumeDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));

        Set<DataPointCollection> dataPointCollections = new HashSet<>();
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_OPEN, stock, dataPointSize, openDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_LOW, stock, dataPointSize, lowDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_HIGH, stock, dataPointSize, highDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_CLOSE, stock, dataPointSize, closeDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_ADJ, stock, dataPointSize, adjDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_VOLUME, stock, dataPointSize, volumeDataPoint));

        rawDataProcessor.process(dataPointCollections);
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
