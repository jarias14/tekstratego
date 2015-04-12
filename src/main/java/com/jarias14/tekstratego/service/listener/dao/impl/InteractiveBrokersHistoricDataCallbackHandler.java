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
public class InteractiveBrokersHistoricDataCallbackHandler implements ApiController.IHistoricalDataHandler {

    private Processor<Set<DataPointCollection>> historicDataProcessor;
    private DataPointSize dataPointSize;
    private Stock stock;

    @Override
    public void historicalData(Bar bar, boolean hasGaps) {
        DataPoint openDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint lowDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint highDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint closeDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint adjDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));
        DataPoint volumeDataPoint = new DataPoint(bar.time(), Double.valueOf(bar.high()));

        Set<DataPointCollection> dataPointCollections = new HashSet<>();
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_OPEN, stock, dataPointSize, 1, openDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_LOW, stock, dataPointSize, 1, lowDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_HIGH, stock, dataPointSize, 1, highDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_CLOSE, stock, dataPointSize, 1, closeDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_ADJ, stock, dataPointSize, 1, adjDataPoint));
        dataPointCollections.add(new DataPointCollection(DataPointIndicator.RAW_VOLUME, stock, dataPointSize, 1, volumeDataPoint));

        historicDataProcessor.process(dataPointCollections);
    }

    @Override
    public void historicalDataEnd() {

    }

    public void setDataPointSize(DataPointSize dataPointSize) {
        this.dataPointSize = dataPointSize;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setHistoricDataProcessor(Processor<Set<DataPointCollection>> historicDataProcessor) {
        this.historicDataProcessor = historicDataProcessor;
    }
}
