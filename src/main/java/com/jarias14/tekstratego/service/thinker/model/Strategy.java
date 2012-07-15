package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.model.TradeTypeEnum;
import com.jarias14.tekstratego.common.resource.StrategyResource;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;

public class Strategy extends AbstractBase {
    
    private static final long serialVersionUID = 1L;

    private Study study;
    private List<Stock> stocks;
    private TradeTypeEnum type;
    private String description;
    private BigDecimal maxStrategyInvestment;
    private BigDecimal maxSecurityInvestment;
    private BigDecimal maxPerTradeInvestment;
    private boolean isStrategyExclusive;
    
    public Strategy() {
        
    }

    public Strategy(StrategyResource resource) {
        super(resource);
        this.type = TradeTypeEnum.valueOf(resource.getType());
        this.maxStrategyInvestment = BigDecimal.valueOf(Double.valueOf((resource.getMaxStrategyInvestment())));
        this.maxSecurityInvestment = BigDecimal.valueOf(Double.valueOf((resource.getMaxSecurityInvestment())));
        this.maxPerTradeInvestment = (BigDecimal.valueOf(Double.valueOf((resource.getMaxPerTradeInvestment()))));
        this.isStrategyExclusive = Boolean.valueOf(resource.getIsStrategyExclusive());
        this.description = resource.getDescription();
        this.stocks = new ArrayList<Stock>();
        for (String stock : resource.getStocks()) {
            stocks.add(new Stock("", stock));
        }
        
        this.study = new AndStudy();
        this.study.setId("root");
    }

    public StrategyResource toResource() {
        StrategyResource resource = new StrategyResource();
        resource.setType(this.type.name());
        resource.setMaxStrategyInvestment(this.maxStrategyInvestment.toString());
        resource.setMaxSecurityInvestment(this.maxSecurityInvestment.toString());
        resource.setMaxPerTradeInvestment(this.maxPerTradeInvestment.toString());
        resource.setIsStrategyExclusive(String.valueOf(this.isStrategyExclusive));
        resource.setDescription(this.description);
        resource.setStocks(new ArrayList<String>());
        for (Stock stock : this.stocks) {
            resource.getStocks().add(stock.getSymbol());
        }
        resource.setId(super.getId());
        resource.setType(type.toString());
        
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

    public BigDecimal getMaxPerTradeInvestment() {
        return maxPerTradeInvestment;
    }

    public void setMaxPerTradeInvestment(BigDecimal maxPerTradeInvestment) {
        this.maxPerTradeInvestment = maxPerTradeInvestment;
    }

}
