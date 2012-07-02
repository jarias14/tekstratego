package com.jarias14.tekstratego.service.pricer.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.jarias14.tekstratego.common.models.SizeOfBars;
import com.jarias14.tekstratego.common.models.Stock;
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
    public Indicator calculateIndicator(String indicatorId, String stockSymbol,
            String sizeOfBars, String startDate, String numberOfBars) {
        
        // init my variables to send to indicator
        
        Indicator indicator = null;
        Date calcStartDate = null;
        SizeOfBars calcSizeOfBars = null;
        int calcNumberOfBars = 1;
        Stock calcStock = null;
        
        // setup variables
        try {
            
            indicator = retrieveIndicator(indicatorId);
            calcStartDate = (new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).parse(startDate);
            calcSizeOfBars = SizeOfBars.valueOf(sizeOfBars);
            calcNumberOfBars = Integer.valueOf(numberOfBars);
            calcStock = new Stock(stockSymbol);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //make the call down to calculate values
        indicator.calculate(calcStock, calcStartDate, calcNumberOfBars);
        
        return indicator;
    }

}
