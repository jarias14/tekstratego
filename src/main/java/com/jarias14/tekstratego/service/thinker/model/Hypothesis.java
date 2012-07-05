package com.jarias14.tekstratego.service.thinker.model;

import java.util.List;

import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class Hypothesis {
    
    private String id;
    private HypothesisStatusEnum status;
    private List<Strategy> strategies;
    private String portfolioId;
    
    public Hypothesis() {
        
    }

    public Hypothesis(HypothesisResource resource) {
        // TODO Auto-generated constructor stub
    }
    
    public HypothesisResource toResource() {
        // TODO Auto-generated constructor stub
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
