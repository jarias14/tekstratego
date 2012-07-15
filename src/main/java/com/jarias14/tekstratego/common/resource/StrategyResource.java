package com.jarias14.tekstratego.common.resource;

import java.util.List;

/**
 * {
 *    "type":"ENTRY",
 *    "description":"buy on sma > 80",
 *    "maxStrategyInvestment":"5000",
 *    "maxSecurityInvestment":"1000",
 *    "maxPerTradeInvestment":"500",
 *    "isStrategyExclusive":"true",
 *    "stocks": ["MSFT", "GOOG"]
 * }
 * 
 */
public class StrategyResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String type;
    private String description;
    private String maxStrategyInvestment;
    private String maxSecurityInvestment;
    private String maxPerTradeInvestment;
    private String isStrategyExclusive;
    private List<String> stocks;
    private LinksResource rootStudy;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxStrategyInvestment() {
        return maxStrategyInvestment;
    }

    public void setMaxStrategyInvestment(String maxStrategyInvestment) {
        this.maxStrategyInvestment = maxStrategyInvestment;
    }

    public String getMaxSecurityInvestment() {
        return maxSecurityInvestment;
    }

    public void setMaxSecurityInvestment(String maxSecurityInvestment) {
        this.maxSecurityInvestment = maxSecurityInvestment;
    }

    public String getIsStrategyExclusive() {
        return isStrategyExclusive;
    }

    public void setIsStrategyExclusive(String isStrategyExclusive) {
        this.isStrategyExclusive = isStrategyExclusive;
    }

    public List<String> getStocks() {
        return stocks;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }
    
    public String getMaxPerTradeInvestment() {
        return maxPerTradeInvestment;
    }

    public void setMaxPerTradeInvestment(String maxPerTradeInvestment) {
        this.maxPerTradeInvestment = maxPerTradeInvestment;
    }

    public LinksResource getRootStudy() {
        return rootStudy;
    }

    public void setRootStudy(LinksResource rootStudy) {
        this.rootStudy = rootStudy;
    }
}
