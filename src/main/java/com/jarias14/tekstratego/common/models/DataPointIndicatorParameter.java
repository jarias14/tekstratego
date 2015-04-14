package com.jarias14.tekstratego.common.models;

/**
 * Created by jarias14 on 4/13/2015.
 */
public enum DataPointIndicatorParameter {

    // GENERAL STUFF
    REQUIRED_PERIODS(1),

    // SIMPLE_MOVING_AVERAGE Parameters
    SIMPLE_MOVING_AVERAGE_PERIODS (3),

    // EXPONENTIAL_MOVING_AVERAGE Parameters
    EXPONENTIAL_MOVING_AVERAGE_PERIODS (3),

    // STOCHASTIC_OSCILLATOR_K PARAMETERS
    STOCHASTIC_OSCILLATOR_K_PERIODS (14),
    STOCHASTIC_OSCILLATOR_K_SLOWING_PERIODS (3),
    STOCHASTIC_OSCILLATOR_D_PERIODS (3);

    private final Integer defaultValue;


    DataPointIndicatorParameter(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }
}
