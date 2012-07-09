package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;
import java.util.List;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.model.TradeTypeEnum;
import com.jarias14.tekstratego.common.resource.StrategyResource;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;

public class Strategy extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private TradeTypeEnum type;
    private String description;
    private BigDecimal maxStrategyInvestment;
    private BigDecimal maxSecurityInvestment;
    private BigDecimal perTradeInvestment;
    private boolean isStrategyExclusive;
    private List<Stock> stocks;
    //private BigDecimal minTrade
    private Study study;
    
    public Strategy() {
        
    }

    public Strategy(StrategyResource resource) {
        super(resource);
        this.type = TradeTypeEnum.valueOf(resource.getType());
        this.maxStrategyInvestment = BigDecimal.valueOf(Double.valueOf((resource.getAmount())));
        this.study = new AndStudy();
        this.study.setId("root");
    }

    public StrategyResource toResource() {
        StrategyResource resource = new StrategyResource();
        
        resource.setId(super.getId());
        resource.setType(type.toString());
        resource.setAmount(maxStrategyInvestment.toString());
        
        return resource;
    }

    public TradeTypeEnum getType() {
        return type;
    }

    public void setType(TradeTypeEnum type) {
        this.type = type;
    }
    
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMaxStrategyInvestment() {
        return maxStrategyInvestment;
    }

    public void setMaxStrategyInvestment(BigDecimal maxStrategyInvestment) {
        this.maxStrategyInvestment = maxStrategyInvestment;
    }

    public BigDecimal getMaxSecurityInvestment() {
        return maxSecurityInvestment;
    }

    public void setMaxSecurityInvestment(BigDecimal maxSecurityInvestment) {
        this.maxSecurityInvestment = maxSecurityInvestment;
    }

    public BigDecimal getPerTradeInvestment() {
        return perTradeInvestment;
    }

    public void setPerTradeInvestment(BigDecimal perTradeInvestment) {
        this.perTradeInvestment = perTradeInvestment;
    }

    public boolean isStrategyExclusive() {
        return isStrategyExclusive;
    }

    public void setStrategyExclusive(boolean isStrategyExclusive) {
        this.isStrategyExclusive = isStrategyExclusive;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

}
