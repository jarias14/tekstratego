package com.jarias14.tekstratego.service.thinker.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.TradeAlert;

public interface ThinkerDAO {

    public void createHypothesis(Hypothesis hypothesis);

    public Hypothesis readHypothesis(String hypothesisId);
    
    public SortedMap<Calendar, BigDecimal> getIndicatorValues(String indicatorId, Stock stock, Calendar startDate, Calendar endDate);
    
    public SortedMap<Calendar, BigDecimal> getIndicatorValues(String indicatorId, Stock stock, Calendar today, Integer numberOfBars);
    
    public void postTradeAlers(String portfolioId, SortedMap<Calendar,List<TradeAlert>> tradeAlerts);

}
