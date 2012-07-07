package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.common.resource.HypothesisResource;
import com.jarias14.tekstratego.common.resource.StrategyResource;
import com.jarias14.tekstratego.common.resource.StudyResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.StudyFactory;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractOperatorStudy;
import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;

public class DefaultRestThinkerServiceImpl implements RestThinkerService{

    private ThinkerService thinkerService;
    
    public DefaultRestThinkerServiceImpl() {
        
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
        
        resource.getLinks().put("self", LinksUtility.getThinkerHypothesisLink(model.getId()));
        
        return resource;
    }

    @Override
    public HypothesisResource runHypothesis(String hypothesisId) {
        
        boolean isGoodRequest = thinkerService.runHypothesis(hypothesisId);
        
        return isGoodRequest ? getHypothesis(hypothesisId) : null;
    }

    @Override
    public StrategyResource addStrategy(StrategyResource resource, String hypothesisId) {
        
        Strategy model = thinkerService.addStrategy(hypothesisId, new Strategy(resource));
        
        return getStrategy(hypothesisId, model.getId());
    }

    @Override
    public StrategyResource getStrategy(String hypothesisId, String strategyId) {
        
        Strategy model = thinkerService.getStrategy(hypothesisId, strategyId);
        
        StrategyResource resource = model.toResource();
        
        resource.getLinks().put("hypothesis", LinksUtility.getThinkerHypothesisLink(hypothesisId));
        resource.getLinks().put("self", LinksUtility.getThinkerStrategyLink(hypothesisId, model.getId()));
        resource.setStudy(LinksUtility.getThinkerStudyLink(hypothesisId, strategyId, "root"));
        
        return resource;
    }

    @Override
    public StudyResource addStudy(StudyResource resource, String hypothesisId, String strategyId, String studyId) {
        
        Study model = thinkerService.addStudy(hypothesisId, strategyId, studyId, StudyFactory.getInstance(resource));
        
        return getStudy(hypothesisId, strategyId, model.getId());
    }

    @Override
    public StudyResource getStudy(String hypothesisId, String strategyId, String studyId) {
        
        Study model = thinkerService.getStudy(hypothesisId, strategyId, studyId);
        
        StudyResource resource = model.toResource();
        
        resource.getLinks().put("hypothesis", LinksUtility.getThinkerHypothesisLink(hypothesisId));
        resource.getLinks().put("strategy", LinksUtility.getThinkerStrategyLink(hypothesisId, strategyId));
        resource.getLinks().put("self", LinksUtility.getThinkerStudyLink(hypothesisId, strategyId, model.getId()));
        
        if (model.getId() != null && !model.getId().equals("root")) {
            resource.getLinks().put("parent", LinksUtility.getThinkerStudyLink(hypothesisId, strategyId, model.getParentId()));
        }
        
        if (model instanceof AbstractOperatorStudy) {
            for (Study study : ((AbstractOperatorStudy)model).getStudies()) {
                resource.getStudies().put(study.getId(), LinksUtility.getThinkerStudyLink(hypothesisId, strategyId, study.getId()));
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
