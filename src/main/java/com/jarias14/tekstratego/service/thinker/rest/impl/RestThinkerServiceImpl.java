package com.jarias14.tekstratego.service.thinker.rest.impl;

import java.util.Set;

import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class RestThinkerServiceImpl implements RestThinkerService{

    private ThinkerService thinkerService;
    
    public RestThinkerServiceImpl() {
        
    }
    
    @Override
    public HypothesisResource createHypothesis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getHypothesis(String hypothesisId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource addStrategy(String hypothesisId, String strategyType, String body) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<BaseResource> getStrategies(String hypothesisId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getStrategy(String hypothesisId, String strategyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource addStudy(String hypothesisId, String strategyId) {
        // TODO Auto-generated method stub
        return null;
    }

    public ThinkerService getThinkerService() {
        return thinkerService;
    }

    public void setThinkerService(ThinkerService thinkerService) {
        this.thinkerService = thinkerService;
    }

}
