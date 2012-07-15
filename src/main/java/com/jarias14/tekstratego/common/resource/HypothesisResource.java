package com.jarias14.tekstratego.common.resource;

import java.util.Map;

public class HypothesisResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String portfolioId;
    private Map<String, LinksResource> strategies;

    public HypothesisResource() {
        super();
    }
    
    public Map<String, LinksResource> getStrategies() {
        return strategies;
    }
    
    public void setStrategies(Map<String, LinksResource> strategies) {
        this.strategies = strategies;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

}
