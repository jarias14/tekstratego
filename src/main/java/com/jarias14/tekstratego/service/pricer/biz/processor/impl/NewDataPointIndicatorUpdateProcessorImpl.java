package com.jarias14.tekstratego.service.pricer.biz.processor.impl;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.pricer.biz.indicators.IndicatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class NewDataPointIndicatorUpdateProcessorImpl implements TransactionManager<NewDataPointIndicatorUpdateRequest, DataPoint> {

    private Map<DataPointIndicator, IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double>> calculators;

    @Override
    public DataPoint process(NewDataPointIndicatorUpdateRequest request) {

        IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double> calculator =
                calculators.get(request.getRequestedIndicator().getDetails().getIndicator());

        Double response = calculator.execute(request);

        DataPoint dataPoint = new DataPoint(request.getRequestedIndicator().getTime(), response);

        return dataPoint;

    }

    @Required
    public void setCalculators(Map<DataPointIndicator, IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double>> calculators) {
        this.calculators = calculators;
    }

}
