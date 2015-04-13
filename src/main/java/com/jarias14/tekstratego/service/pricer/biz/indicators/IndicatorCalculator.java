package com.jarias14.tekstratego.service.pricer.biz.indicators;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;

import java.util.Set;

/**
 * Created by jarias14 on 3/29/2015.
 */
public interface IndicatorCalculator<REQUEST, RESPONSE> {

    public RESPONSE execute(REQUEST request);

    default DataPointCollection getIndicatorData(Set<DataPointCollection> dataPointCollections, DataPointIndicator indicator) {
        return dataPointCollections
                .stream()
                .filter(data ->
                        indicator.equals(data.getDetails().getIndicator()))
                .findFirst()
                .get();
    }

    default Double[] getDataPoints(DataPointCollection dataPointCollection) {
        return dataPointCollection.getDataPoints().stream()
                .map(DataPoint::getValue)
                .map(v -> (Double) v)
                .toArray(Double[]::new);
    }

}
