package com.jarias14.tekstratego.service.thinker.rest.resource;

import java.util.List;

import com.jarias14.tekstratego.common.resources.LinksResource;

public class HypothesisResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String status;
    private String portfolioId;
    private List<LinksResource> strategies;

    public HypothesisResource() {
        super();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<LinksResource> getStrategies() {
        return strategies;
    }
    
    public void setStrategies(List<LinksResource> strategies) {
        this.strategies = strategies;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

}
