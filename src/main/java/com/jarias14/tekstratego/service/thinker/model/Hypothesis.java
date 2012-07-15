package com.jarias14.tekstratego.service.thinker.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.HypothesisResource;
import com.jarias14.tekstratego.common.resource.LinksResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;

public class Hypothesis extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private Map<String, Strategy> strategies;
    private List<Stock> stocks;
    private String portfolioId;
    private Calendar startDate;
    private Calendar endDate;
    
    public Hypothesis() {
        
    }

    public Hypothesis(HypothesisResource resource) {
        super(resource);
        this.strategies = new HashMap<String, Strategy>();
        this.portfolioId = resource.getPortfolioId();
        this.stocks = new ArrayList<Stock>();
        this.stocks.add(new Stock("NYSE", "ED"));
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
    }
    
    public HypothesisResource toResource() {
        
        HypothesisResource resource = new HypothesisResource();
        
        resource.setId(super.getId());
        resource.setPortfolioId(portfolioId);
        resource.setStrategies(new HashMap<String, LinksResource>());
        
        for (Strategy strategy : strategies.values()) {
            resource.getStrategies().put(strategy.getId(), LinksUtility.getThinkerStrategyLink(super.getId(), strategy.getId()));
        }
        
        return resource;
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
