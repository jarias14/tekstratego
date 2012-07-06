package com.jarias14.tekstratego.service.thinker.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.resources.LinksResource;
import com.jarias14.tekstratego.common.utilities.ConstantsUtility;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class Hypothesis extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private HypothesisStatusEnum status;
    private Map<String, Strategy> strategies;
    private List<Stock> stocks;
    private String portfolioId;
    private Calendar startDate;
    private Calendar endDate;
    
    public Hypothesis() {
        
    }

    public Hypothesis(HypothesisResource resource) {
        super(resource);
        //this.status = HypothesisStatusEnum.valueOf(resource.getStatus());
        this.strategies = new HashMap<String, Strategy>();
        this.portfolioId = resource.getPortfolioId();
        this.status = HypothesisStatusEnum.AVAILABLE;
        this.stocks = new ArrayList<Stock>();
        this.stocks.add(new Stock("NYSE", "ED"));
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        try {
            this.startDate.setTime(formatter.parse(resource.getStartDate()));
            this.endDate.setTime(formatter.parse(resource.getEndDate()));
        } catch (Exception e) {
            
        }
    }
    
    public HypothesisResource toResource() {
        
        HypothesisResource resource = new HypothesisResource();
        
        resource.setId(super.getId());
        resource.setStatus(status.toString());
        resource.setPortfolioId(portfolioId);
        resource.setStrategies(new HashMap<String, LinksResource>());
        resource.setStartDate((new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).format(startDate));
        resource.setEndDate((new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).format(endDate));
        
        for (Strategy strategy : strategies.values()) {
            resource.getStrategies().put(strategy.getId(), LinksUtility.getThinkerStrategyLink(super.getId(), strategy.getId()));
        }
        
        return resource;
    }

    public HypothesisStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HypothesisStatusEnum status) {
        this.status = status;
    }

    public Map<String, Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Map<String, Strategy> strategies) {
        this.strategies = strategies;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

}
