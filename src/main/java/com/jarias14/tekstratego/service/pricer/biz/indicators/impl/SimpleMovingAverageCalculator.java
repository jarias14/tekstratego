package com.jarias14.tekstratego.service.pricer.biz.indicators.impl;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.DataPointIndicatorParameter;
import com.jarias14.tekstratego.service.pricer.biz.indicators.IndicatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.tictactec.ta.lib.CoreAnnotated;
import com.tictactec.ta.lib.MInteger;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class SimpleMovingAverageCalculator extends BaseCalculator implements IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double> {

    private static DataPointIndicator closeIndicator = DataPointIndicator.RAW_CLOSE;
    private CoreAnnotated taLib;

    @Override
    public Double execute(NewDataPointIndicatorUpdateRequest newDataPointIndicatorUpdateRequest) {

        DataPointCollection closeDataPointCollection =
                newDataPointIndicatorUpdateRequest
                        .getData()
                        .stream()
                        .filter(data ->
                                closeIndicator.equals(data.getDetails().getIndicator()))
                        .findFirst()
                        .get();

        Double[] dataPoints =
                closeDataPointCollection.getDataPoints().stream()
                        .map(DataPoint::getValue)
                        .map(v -> (Double) v)
                        .toArray(Double[]::new);

        Integer periods = getParameter(DataPointIndicatorParameter.SIMPLE_MOVING_AVERAGE_PERIODS, newDataPointIndicatorUpdateRequest.getRequestedIndicator().getDetails().getParameters());

        double[] closeDataPoints = ArrayUtils.toPrimitive(dataPoints);
        double[] emaDataPoints = new double[1];


        taLib.sma(closeDataPoints.length - 1, closeDataPoints.length - 1, closeDataPoints, periods, new MInteger(), new MInteger(), emaDataPoints);

        Double result = Double.valueOf(emaDataPoints[0]);

        return result;
    }

    @Required
    public void setTaLib(CoreAnnotated taLib) {
        this.taLib = taLib;
    }
}
