package com.jarias14.tekstratego.service.pricer.biz.processor.impl;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.pricer.biz.indicators.IndicatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class NewDataPointIndicatorUpdateProcessorImpl implements Processor<NewDataPointIndicatorUpdateRequest> {

    private Map<DataPointIndicator, IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double>> calculators;
    private DataStore indicatorDataStore;

    @Override
    public void process(NewDataPointIndicatorUpdateRequest request) {

        IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double> calculator =
                calculators.get(request.getRequestedIndicator().getDetails().getIndicator());

        Double response = calculator.execute(request);

        indicatorDataStore.putDataPoint(
                request.getRequestedIndicator().getStock(),
                new DataPoint(request.getRequestedIndicator().getTime(), response),
                request.getRequestedIndicator().getDetails());
    }

    @Required
    public void setCalculators(Map<DataPointIndicator, IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double>> calculators) {
        this.calculators = calculators;
    }

    @Required
    public void setIndicatorDataStore(DataStore indicatorDataStore) {
        this.indicatorDataStore = indicatorDataStore;
    }
}
