package com.jarias14.tekstratego.service.thinker.model;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.AlertResource;

public class TradeAlert extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private Strategy strategy;
    private String hypothesisId;
    private Stock stock;
    
    public TradeAlert() {
        
    }

    public AlertResource toResource() {
        
        AlertResource resource = new AlertResource();
        
        resource.setId(getId());
        resource.setStock(stock.getSymbol());
        resource.setStrategyId(strategy.getId());
        resource.setHypothesisId(hypothesisId);
        resource.setDescription(strategy.getDescription());
        resource.setStrategyType(strategy.getType().name());
        resource.setLimitPerTrade(strategy.getMaxSecurityInvestment().toString());
        resource.setLimitPerStock(strategy.getMaxSecurityInvestment().toString());
        resource.setLimitForStrategy(strategy.getMaxStrategyInvestment().toString());
        resource.setIsStrategyExclusive(strategy.isStrategyExclusive());
        
        return resource;
    }
    
    public TradeAlert(Stock stock, Strategy strategy) {
        this.stock = stock;
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }
    
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public Stock getStock() {
        return stock;
    }
    
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getHypothesisId() {
        return hypothesisId;
    }

    public void setHypothesisId(String hypothesisId) {
        this.hypothesisId = hypothesisId;
    }

}
