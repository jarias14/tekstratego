package com.jarias14.tekstratego.service.pricer.biz.indicators.impl;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.DataPointIndicatorParameter;
import com.jarias14.tekstratego.service.pricer.biz.indicators.IndicatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.tictactec.ta.lib.CoreAnnotated;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class StochasticOscillatorCalculator extends BaseCalculator implements IndicatorCalculator<NewDataPointIndicatorUpdateRequest, Double> {

    private static DataPointIndicator closeIndicator = DataPointIndicator.RAW_CLOSE;
    private static DataPointIndicator highIndicator = DataPointIndicator.RAW_HIGH;
    private static DataPointIndicator lowIndicator = DataPointIndicator.RAW_LOW;

    private CoreAnnotated taLib;

    @Override
    public Double execute(NewDataPointIndicatorUpdateRequest newDataPointIndicatorUpdateRequest) {

        DataPointCollection closeDataPointCollection = getIndicatorData(newDataPointIndicatorUpdateRequest.getData(), closeIndicator);
        DataPointCollection highDataPointCollection = getIndicatorData(newDataPointIndicatorUpdateRequest.getData(), highIndicator);
        DataPointCollection lowDataPointCollection = getIndicatorData(newDataPointIndicatorUpdateRequest.getData(), lowIndicator);

        Integer kPeriods = getParameter(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_K_PERIODS, newDataPointIndicatorUpdateRequest.getRequestedIndicator().getDetails().getParameters());
        Integer kSlowPeriods = getParameter(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_K_SLOWING_PERIODS, newDataPointIndicatorUpdateRequest.getRequestedIndicator().getDetails().getParameters());
        Integer dPeriods = getParameter(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_D_PERIODS, newDataPointIndicatorUpdateRequest.getRequestedIndicator().getDetails().getParameters());

        Double[] closeDataPointsWrapper = getDataPoints(closeDataPointCollection);
        Double[] highDataPointsWrapper = getDataPoints(highDataPointCollection);
        Double[] lowDataPointsWrapper = getDataPoints(lowDataPointCollection);

        double[] closeDataPoints = ArrayUtils.toPrimitive(closeDataPointsWrapper);
        double[] highDataPoints = ArrayUtils.toPrimitive(highDataPointsWrapper);
        double[] lowDataPoints = ArrayUtils.toPrimitive(lowDataPointsWrapper);

        double[] kDataPoints = new double[30];
        double[] dDataPoints = new double[30];

        taLib.stoch(
                closeDataPoints.length - 1,
                closeDataPoints.length - 1,
                highDataPoints,
                lowDataPoints,
                closeDataPoints,
                kPeriods,
                kSlowPeriods,
                MAType.Sma,
                dPeriods,
                MAType.Sma,
                new MInteger(),
                new MInteger(),
                kDataPoints,
                dDataPoints);

        if (newDataPointIndicatorUpdateRequest.getRequestedIndicator().getDetails().getIndicator().equals(DataPointIndicator.STOCHASTIC_OSCILLATOR_K)) {
            return Double.valueOf(kDataPoints[0]);
        } else {
            return Double.valueOf(dDataPoints[0]);
        }
    }



    @Required
    public void setTaLib(CoreAnnotated taLib) {
        this.taLib = taLib;
    }
}
