package com.jarias14.tekstratego.service.thinker.biz;

import java.util.Set;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public interface ThinkerService {
    

    public HypothesisResource createHypothesis();
    public BaseResource getHypothesis(String hypothesisId);
    public BaseResource addStrategy(String hypothesisId, String strategyType, String body);
    public Set<BaseResource> getStrategies(String hypothesisId);
    public BaseResource getStrategy(String hypothesisId, String strategyId);
    public BaseResource addStudy(String hypothesisId, String strategyId);

}
