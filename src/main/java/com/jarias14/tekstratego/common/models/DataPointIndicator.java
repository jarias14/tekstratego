package com.jarias14.tekstratego.common.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    OPEN (double.class, new HashSet(Arrays.asList(RAW_OPEN))),
    HIGH (double.class, new HashSet(Arrays.asList(RAW_HIGH))),
    LOW(double.class, new HashSet(Arrays.asList(RAW_LOW))),
    CLOSE(double.class, new HashSet(Arrays.asList(RAW_CLOSE))),
    ADJ (double.class, new HashSet(Arrays.asList(RAW_ADJ))),
    VOLUME (double.class, new HashSet(Arrays.asList(RAW_VOLUME))),
    SIMPLE_MOVING_AVERAGE(double.class, new HashSet(Arrays.asList(RAW_CLOSE))),
    EXPONENTIAL_MOVING_AVERAGE(double.class, new HashSet(Arrays.asList(RAW_CLOSE))),
    STOCHASTIC_OSCILLATOR_K(double.class, new HashSet(Arrays.asList(RAW_CLOSE, RAW_HIGH, RAW_LOW))),
    STOCHASTIC_OSCILLATOR_D(double.class, new HashSet(Arrays.asList(RAW_CLOSE, RAW_HIGH, RAW_LOW)));

    private final Class valueType;
    private final Set<DataPointIndicator> necessaryRawDataPoints;

    DataPointIndicator(Class valueType, Set<DataPointIndicator> necessaryRawDataPoints) {
        this.valueType = valueType;
        this.necessaryRawDataPoints = necessaryRawDataPoints;
    }
    public <T extends Comparable> Class<T> getValueType() {
        return valueType;
    }

    public Set<DataPointIndicator> getNecessaryRawDataPoints() {
        return necessaryRawDataPoints;
    }
}
