package com.jarias14.tekstratego.service.thinker.biz.impl;

import java.util.Set;

import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class DefaultThinkerService implements ThinkerService {

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
    public BaseResource addStrategy(String hypothesisId, String strategyType,
            String body) {
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

}
