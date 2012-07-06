package com.jarias14.tekstratego.service.pricer.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.UUID;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.utilities.ConstantsUtility;
import com.jarias14.tekstratego.service.pricer.biz.PricerService;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorDAO;

public class DefaultPricerService implements PricerService {
    
    private IndicatorDAO indicatorDAO;
    
    @Override
    public Indicator createIndicator(Indicator indicator) {

        // create unique id for new indicator
        String indicatorId = UUID.randomUUID().toString();
        indicator.setId(indicatorId);
        
        // save indicator to memory
        indicatorDAO.createIndicator(indicator);
        
        // read and return indicator from memory
        return retrieveIndicator(indicatorId);
    }

    @Override
    public Indicator retrieveIndicator(String indicatorId) {
        return indicatorDAO.readIndicator(indicatorId);
    }

    public IndicatorDAO getIndicatorDAO() {
        return indicatorDAO;
    }

    public void setIndicatorDAO(IndicatorDAO indicatorDAO) {
        this.indicatorDAO = indicatorDAO;
    }

    @Override
    public SortedMap<Calendar, Double> calculateIndicator(String indicatorId, String stockSymbol,
            String startDate, String endDate) {
        
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        // init my variables to send to indicator
        
        Indicator indicator = null;
        Calendar calcStartDate = Calendar.getInstance();;
        Calendar calcEndDate = Calendar.getInstance();;
        Stock calcStock = null;
        
        // setup variables
        try {
            
            indicator = retrieveIndicator(indicatorId);
            calcStartDate.setTime(sdf.parse(startDate));
            calcEndDate.setTime(sdf.parse(endDate));
            calcStock = new Stock("", stockSymbol);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //make the call down to calculate values
        return indicator.calculate(calcStock, calcStartDate, calcEndDate);
    }

}
