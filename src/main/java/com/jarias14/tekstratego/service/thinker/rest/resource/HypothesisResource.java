package com.jarias14.tekstratego.service.thinker.rest.resource;

import java.util.Map;

import com.jarias14.tekstratego.common.resources.LinksResource;

public class HypothesisResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String status;
    private String portfolioId;
    private Map<String, LinksResource> strategies;
    private String startDate;
    private String endDate;

    public HypothesisResource() {
        super();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
