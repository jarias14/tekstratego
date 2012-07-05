package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.StudyFactory;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractOperatorStudy;
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
        
        Hypothesis model = thinkerService.createHypothesis(new Hypothesis(resource));
        
        return getHypothesis(model.getId());
    }

    @Override
    public HypothesisResource getHypothesis(String hypothesisId) {
        
        Hypothesis model = thinkerService.getHypothesis(hypothesisId);
        
        HypothesisResource resource = model.toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("self", model.getId()));
        
        return resource;
    }

    @Override
    public StrategyResource addStrategy(String hypothesisId, StrategyResource resource) {
        
        Strategy model = thinkerService.addStrategy(hypothesisId, new Strategy(resource));
        
        return getStrategy(hypothesisId, model.getId());
    }

    @Override
    public StrategyResource getStrategy(String hypothesisId, String strategyId) {
        
        Strategy model = thinkerService.getStrategy(hypothesisId, strategyId);
        
        StrategyResource resource = model.toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("hypothesis", hypothesisId));
        resource.getLinks().add(LinksUtility.getThinkerStrategyLink("self", hypothesisId, model.getId()));
        resource.setStudy(LinksUtility.getThinkerStudyLink("root", hypothesisId, strategyId, "root"));
        
        return resource;
    }

    @Override
    public StudyResource addStudy(String hypothesisId, String strategyId, String studyId, StudyResource resource) {
        
        Study model = thinkerService.addStudy(hypothesisId, strategyId, studyId, StudyFactory.getInstance(resource));
        
        return getStudy(hypothesisId, strategyId, model.getId());
    }

    @Override
    public StudyResource getStudy(String hypothesisId, String strategyId, String studyId) {
        
        Study model = thinkerService.getStudy(hypothesisId, strategyId, studyId);
        
        StudyResource resource = model.toResource();
        
        resource.getLinks().add(LinksUtility.getThinkerHypothesisLink("hypothesis", hypothesisId));
        resource.getLinks().add(LinksUtility.getThinkerStrategyLink("strategy", hypothesisId, strategyId));
        resource.getLinks().add(LinksUtility.getThinkerStudyLink("self", hypothesisId, strategyId, model.getId()));
        
        if (model.getId() != null && !model.getId().equals("root")) {
            resource.getLinks().add(LinksUtility.getThinkerStudyLink("parent", hypothesisId, strategyId, model.getParentId()));
        }
        
        if (model instanceof AbstractOperatorStudy) {
            for (Study study : ((AbstractOperatorStudy)model).getStudies()) {
                resource.getStudies().add(LinksUtility.getThinkerStudyLink(study.getId(), hypothesisId, strategyId, study.getId()));
            }
        }
        
        return resource;
    }

    public ThinkerService getThinkerService() {
        return thinkerService;
    }

    public void setThinkerService(ThinkerService thinkerService) {
        this.thinkerService = thinkerService;
    }

}
