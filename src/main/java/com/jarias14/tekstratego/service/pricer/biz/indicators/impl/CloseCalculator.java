package com.jarias14.tekstratego.service.pricer.biz.indicators.impl;

import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.service.pricer.biz.indicators.IndicatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class CloseCalculator extends BaseCalculator implements IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double> {

    private static DataPointIndicator closeIndicator = DataPointIndicator.RAW_CLOSE;

    @Override
    public Double execute(NewDataPointIndicatorUpdateRequest newDataPointIndicatorUpdateRequest) {


        return (Double)newDataPointIndicatorUpdateRequest.getData().iterator().next().getDataPoints().get(0).getValue();
    }
}
