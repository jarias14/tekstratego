package com.jarias14.tekstratego.service.thinker.model;

import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.resources.LinksResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class Hypothesis extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private HypothesisStatusEnum status;
    private List<Strategy> strategies;
    private List<Stock> stocks;
    private String portfolioId;
    
    public Hypothesis() {
        
    }

    public Hypothesis(HypothesisResource resource) {
        super(resource);
        //this.status = HypothesisStatusEnum.valueOf(resource.getStatus());
        this.strategies = new ArrayList<Strategy>();
        this.portfolioId = resource.getPortfolioId();
        this.status = HypothesisStatusEnum.AVAILABLE;
        this.stocks = new ArrayList<Stock>();
        this.stocks.add(new Stock("NYSE", "ED"));
    }
    
    public HypothesisResource toResource() {
        
        HypothesisResource resource = new HypothesisResource();
        
        resource.setId(super.getId());
        resource.setStatus(status.toString());
        resource.setPortfolioId(portfolioId);
        resource.setStrategies(new ArrayList<LinksResource>());
        
        for (Strategy strategy : strategies) {
            resource.getStrategies().add(LinksUtility.getThinkerStrategyLink(strategy.getId(), super.getId(), strategy.getId()));
        }
        
        return resource;
    }

    public HypothesisStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HypothesisStatusEnum status) {
        this.status = status;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
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

}
