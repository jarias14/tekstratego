package com.jarias14.tekstratego.service.pricer.dao;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface IndicatorDAO {
    
    public void createIndicator(Indicator indicator);
    
    public Indicator readIndicator(String indicatorId);
    
    public void deleteIndicator(String indicatorId);

}
