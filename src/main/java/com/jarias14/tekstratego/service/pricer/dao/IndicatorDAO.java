package com.jarias14.tekstratego.service.pricer.dao;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface IndicatorDAO {
    
    public void saveIndicator(Indicator indicator);
    
    public void readIndicator(String indicatorId);

}
