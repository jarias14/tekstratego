package com.jarias14.tekstratego.common.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jarias14 on 3/22/2015.
 */
public enum DataPointIndicator {


    RAW_OPEN (double.class, null),
    RAW_HIGH (double.class, null),
    RAW_LOW (double.class, null),
    RAW_CLOSE (double.class, null),
    RAW_ADJ (double.class, null),
    RAW_VOLUME (double.class, null),
    OPEN (double.class, Arrays.asList(RAW_OPEN)),
    HIGH (double.class, Arrays.asList(RAW_HIGH)),
    LOW (double.class, Arrays.asList(RAW_LOW)),
    CLOSE (double.class, Arrays.asList(RAW_CLOSE)),
    ADJ (double.class, Arrays.asList(RAW_ADJ)),
    VOLUME (double.class, Arrays.asList(RAW_VOLUME)),
    SIMPLE_MOVING_AVERAGE (double.class, Arrays.asList(CLOSE)),
    EXPONENTIAL_MOVING_AVERAGE (double.class, Arrays.asList(CLOSE));

    private final Class valueType;
    private final List<DataPointIndicator> necessaryRawDataPoints;

    DataPointIndicator(Class valueType, List<DataPointIndicator> necessaryRawDataPoints) {
        this.valueType = valueType;
        this.necessaryRawDataPoints = necessaryRawDataPoints;
    }

    public <T extends Comparable> Class<T> getValueType() {
        return valueType;
    }

    public List<DataPointIndicator> getNecessaryRawDataPoints() {
        return necessaryRawDataPoints;
    }
}
