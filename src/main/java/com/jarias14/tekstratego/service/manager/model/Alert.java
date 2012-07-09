package com.jarias14.tekstratego.service.manager.model;

import java.math.BigInteger;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.model.TradeTypeEnum;
import com.jarias14.tekstratego.common.resource.AlertResource;

public class Alert extends AbstractBase {

    private static final long serialVersionUID = 1L;
    
    private Stock stock;
    private String strategyId;
    private String hypothesisId;
    private String description; //textual description of this alert
    private BigInteger amountToTrade; //how much money to trade on this alert
    private TradeTypeEnum strategyType; //entry, scale, exit
    private BigInteger limitPerStock; //how much money to trade on this alert
    private BigInteger limitForStrategy; //max investment for this strategy
    private boolean isStrategyExclusive; //strategy only cares about its own transactions
    
    private int tradedShares;
    
    public Alert(AlertResource resource) {
        
        this.setId(resource.getId());
        this.stock = new Stock("", resource.getStock());
        this.strategyId = resource.getStrategyId();
        this.hypothesisId = resource.getHypothesisId();
        this.description = resource.getDescription();
        this.strategyType = TradeTypeEnum.valueOf(resource.getStrategyType());
        this.limitPerStock = new BigInteger(resource.getLimitPerStock());
        this.limitForStrategy = new BigInteger(resource.getLimitForStrategy());
        this.isStrategyExclusive = resource.getIsStrategyExclusive();
        
    }
    
    public Alert() {

    }

    public AlertResource toResource() {
        AlertResource resource = new AlertResource();
        
        resource.setId(getId());
        resource.setStock(stock.getSymbol());
        resource.setStrategyId(strategyId);
        resource.setHypothesisId(hypothesisId);
        resource.setDescription(description);
        resource.setStrategyType(strategyType.name());
        resource.setLimitPerStock(limitPerStock.toString());
        resource.setLimitForStrategy(limitForStrategy.toString());
        resource.setIsStrategyExclusive(isStrategyExclusive);
        
        return resource;
    }
    
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
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
    public TradeTypeEnum getStrategyType() {
        return strategyType;
    }
    public void setStrategyType(TradeTypeEnum strategyType) {
        this.strategyType = strategyType;
    }
    public BigInteger getLimitPerStock() {
        return limitPerStock;
    }
    public void setLimitPerStock(BigInteger limitPerStock) {
        this.limitPerStock = limitPerStock;
    }
    public BigInteger getLimitForStrategy() {
        return limitForStrategy;
    }
    public void setLimitForStrategy(BigInteger limitForStrategy) {
        this.limitForStrategy = limitForStrategy;
    }
    public boolean isStrategyExclusive() {
        return isStrategyExclusive;
    }
    public void setStrategyExclusive(boolean isStrategyExclusive) {
        this.isStrategyExclusive = isStrategyExclusive;
    }

    public int getTradedShares() {
        return tradedShares;
    }

    public void setTradedShares(int tradedShares) {
        this.tradedShares = tradedShares;
    }

    public BigInteger getAmountToTrade() {
        return amountToTrade;
    }

    public void setAmountToTrade(BigInteger amountToTrade) {
        this.amountToTrade = amountToTrade;
    }

}
