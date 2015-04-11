package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.service.pricer.biz.indicators.impl.StochasticOscillatorCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.UpdateSimpleIndicatorRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class StochasticOscillatorKTest {

    private StochasticOscillatorCalculator stochasticCalculator;
    
    @Before
    public void setUp() {
        stochasticCalculator = new StochasticOscillatorCalculator();
    }

    @Test
    public void test() {

        UpdateSimpleIndicatorRequest request = new UpdateSimpleIndicatorRequest();
        request.setRequestedIndicator(DataProvider.getIndicator(DataPointIndicator.STOCHASTIC_OSCILLATOR_K));
        request.setData(new HashSet<>());
        request.getData().add(DataProvider.getCloses());
        request.getData().add(DataProvider.getHighs());
        request.getData().add(DataProvider.getLows());

        Double result = stochasticCalculator.execute(request);
    }



    private DataPointCollection getExpectedResults() {

        DataPointCollection dataPointCollection = DataProvider.getDataPointCollectionWithoutPrices(DataPointIndicator.STOCHASTIC_OSCILLATOR_K);

        int i = 100;
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 70.4382202470155));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 67.6089091003048));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 89.2021084181696));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 65.8105524262313));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 81.7477132964705));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 64.5237972197819));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 74.5297763406087));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 98.5814423191156));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 70.1045325659149));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 73.0560907339419));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 73.4177905412773));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 61.2312902873376));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 60.9562710235466));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 40.3861022518606));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 40.3861022518606));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 66.8285493379727));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 56.7314197351892));

        return dataPointCollection;
    }


}
