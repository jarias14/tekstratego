package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.StudyFactory;
import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.StrategyResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public class RestThinkerServiceImpl implements RestThinkerService{

    private ThinkerService thinkerService;
    
    public RestThinkerServiceImpl() {
        
    }
    
    @Override
    public HypothesisResource createHypothesis(HypothesisResource resource) {
        return thinkerService.createHypothesis(new Hypothesis(resource)).toResource();
    }

    @Override
    public HypothesisResource getHypothesis(String hypothesisId) {
        return thinkerService.getHypothesis(hypothesisId).toResource();
    }

    @Override
    public StrategyResource addStrategy(String hypothesisId, StrategyResource resource) {
        return thinkerService.addStrategy(hypothesisId, new Strategy(resource)).toResource();
    }

    @Override
    public StrategyResource getStrategy(String hypothesisId, String strategyId) {
        return thinkerService.getStrategy(hypothesisId, strategyId).toResource();
    }

    @Override
    public StudyResource addStudy(String hypothesisId, String strategyId, String studyId, StudyResource resource) {
        
        Study model = StudyFactory.getInstance(resource);
        
        return thinkerService.addStudy(hypothesisId, strategyId, studyId, model).toResource();
    }

    public ThinkerService getThinkerService() {
        return thinkerService;
    }

    public void setThinkerService(ThinkerService thinkerService) {
        this.thinkerService = thinkerService;
    }

}
