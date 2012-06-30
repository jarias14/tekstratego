package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.io.Serializable;

import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public interface Indicator extends Serializable {
    public IndicatorResource toResource();

}
