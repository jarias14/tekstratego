package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.common.utilities.LinksUtility;
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
        
        resource = thinkerService.createHypothesis(new Hypothesis(resource)).toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("self", resource.getId()));
        
        return resource;
    }

    @Override
    public HypothesisResource getHypothesis(String hypothesisId) {
        
        HypothesisResource resource = thinkerService.getHypothesis(hypothesisId).toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("self", resource.getId()));
        
        return resource;
    }

    @Override
    public StrategyResource addStrategy(String hypothesisId, StrategyResource resource) {
        
        resource = thinkerService.addStrategy(hypothesisId, new Strategy(resource)).toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerStrategyLink("self", hypothesisId, resource.getId()));
        
        return resource;
    }

    @Override
    public StrategyResource getStrategy(String hypothesisId, String strategyId) {
        
        StrategyResource resource = thinkerService.getStrategy(hypothesisId, strategyId).toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("hypothesis", hypothesisId));
        resource.getLinks().add(LinksUtility.getThinkerStrategyLink("self", hypothesisId, resource.getId()));
        
        return resource;
    }

    @Override
    public StudyResource addStudy(String hypothesisId, String strategyId, String studyId, StudyResource resource) {
        
        Study model = StudyFactory.getInstance(resource);
        
        resource = thinkerService.addStudy(hypothesisId, strategyId, studyId, model).toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("hypothesis", hypothesisId));
        resource.getLinks().add(LinksUtility.getThinkerStrategyLink("self", hypothesisId, resource.getId()));
        resource.getLinks().add(LinksUtility.getThinkerStudyLink("self", hypothesisId, strategyId, resource.getId()));
        
        return resource;
    }

    public ThinkerService getThinkerService() {
        return thinkerService;
    }

    public void setThinkerService(ThinkerService thinkerService) {
        this.thinkerService = thinkerService;
    }

}
