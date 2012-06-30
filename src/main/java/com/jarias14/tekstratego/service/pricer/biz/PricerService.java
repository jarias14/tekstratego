package com.jarias14.tekstratego.service.pricer.biz;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;

public interface PricerService {
    
    public Indicator createIndicator(Indicator indicator);
    public Indicator retrieveIndicator(String indicatorId);

}
