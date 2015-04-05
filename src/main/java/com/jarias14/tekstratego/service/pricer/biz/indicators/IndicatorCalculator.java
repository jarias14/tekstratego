package com.jarias14.tekstratego.service.pricer.biz.indicators;

/**
 * Created by jarias14 on 3/29/2015.
 */
public interface IndicatorCalculator<REQUEST, RESPONSE> {

    public RESPONSE execute(REQUEST request);

}
