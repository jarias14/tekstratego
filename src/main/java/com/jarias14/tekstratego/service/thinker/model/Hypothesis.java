package com.jarias14.tekstratego.service.thinker.model;

import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.common.resources.LinksResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class Hypothesis extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private HypothesisStatusEnum status;
    private List<Strategy> strategies;
    private String portfolioId;
    
    public Hypothesis() {
        
    }

    public Hypothesis(HypothesisResource resource) {
        super(resource);
        //this.status = HypothesisStatusEnum.valueOf(resource.getStatus());
        this.strategies = new ArrayList<Strategy>();
        this.portfolioId = resource.getPortfolioId();
        this.status = HypothesisStatusEnum.AVAILABLE;
    }
    
    public HypothesisResource toResource() {
        
        HypothesisResource resource = new HypothesisResource();
        
        resource.setId(super.getId());
        resource.setStatus(status.toString());
        resource.setPortfolioId(portfolioId);
        resource.setStrategies(new ArrayList<LinksResource>());
        
        for (Strategy strategy : strategies) {
            resource.getStrategies().add(LinksUtility.getPricerHypothesisLink(strategy.getId(), super.getId(), strategy.getId()));
        }
        
        return resource;
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
