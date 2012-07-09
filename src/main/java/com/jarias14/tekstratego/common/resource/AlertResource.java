package com.jarias14.tekstratego.common.resource;


public class AlertResource extends BaseResource {
    
    private static final long serialVersionUID = 1L;
    
    private String stock;
    private String strategyId;
    private String hypothesisId;
    private String description; //textual description of this alert
    private String strategyType; //entry, scale, exit
    private String limitPerStock; //how much money to trade on this alert
    private String limitForStrategy; //max investment for this strategy
    private Boolean isStrategyExclusive; //strategy only cares about its own transactions
    
    
    public AlertResource() {
        
    }
    
    public String getStock() {
        return stock;
    }
    
    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getHypothesisId() {
        return hypothesisId;
    }

    public void setHypothesisId(String hypothesisId) {
        this.hypothesisId = hypothesisId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public String getLimitPerStock() {
        return limitPerStock;
    }

    public void setLimitPerStock(String limitPerStock) {
        this.limitPerStock = limitPerStock;
    }

    public String getLimitForStrategy() {
        return limitForStrategy;
    }

    public void setLimitForStrategy(String limitForStrategy) {
        this.limitForStrategy = limitForStrategy;
    }

    public Boolean getIsStrategyExclusive() {
        return isStrategyExclusive;
    }

    public void setIsStrategyExclusive(Boolean isStrategyExclusive) {
        this.isStrategyExclusive = isStrategyExclusive;
    }

}
