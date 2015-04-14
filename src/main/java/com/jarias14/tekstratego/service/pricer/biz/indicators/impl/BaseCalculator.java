package com.jarias14.tekstratego.service.pricer.biz.indicators.impl;

import com.jarias14.tekstratego.common.models.DataPointIndicatorParameter;

import java.util.Map;

/**
 * Created by jarias14 on 4/13/2015.
 */
public abstract class BaseCalculator {

    protected Integer getParameter(DataPointIndicatorParameter parameter, Map<DataPointIndicatorParameter, Integer> requestParams) {
        return requestParams.containsKey(parameter) ? requestParams.get(parameter) : parameter.getDefaultValue();
    }
}
