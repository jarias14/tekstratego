package com.jarias14.tekstratego.service.thinker.biz.impl;

import java.util.List;
import java.util.UUID;

import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.dao.ThinkerDAO;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractCalculationStudy;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractOperatorStudy;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;
import com.jarias14.tekstratego.service.thinker.model.study.EqualToStudy;
import com.jarias14.tekstratego.service.thinker.model.study.GreaterThanStudy;
import com.jarias14.tekstratego.service.thinker.model.study.LessThanStudy;
import com.jarias14.tekstratego.service.thinker.model.study.OrStudy;

public class DefaultThinkerService implements ThinkerService {
    
    private ThinkerDAO thinkerDAO;

    @Override
    public Hypothesis createHypothesis(Hypothesis resource) {
        
        // get a unique id
        Hypothesis hypothesis = new Hypothesis();
        hypothesis.setId(UUID.randomUUID().toString());
        
        // save hypothesis to memory
        thinkerDAO.createHypothesis(hypothesis);
        
        // return hypothesis from memory
        return getHypothesis(hypothesis.getId());
    }

    @Override
    public Hypothesis getHypothesis(String hypothesisId) {
        
        // return hypothesis from memory
        return thinkerDAO.readHypothesis(hypothesisId);
    }

    @Override
    public Strategy addStrategy(String hypothesisId, Strategy strategy) {
        
        // set an unique id for the new study
        strategy.setId(UUID.randomUUID().toString());
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // add strategy to hypothesis
        hypothesis.getStrategies().add(strategy);
        
        // return new strategy
        return getStrategy(hypothesisId, strategy.getId());
    }

    @Override
    public Strategy getStrategy(String hypothesisId, String strategyId) {
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // get strategy from hypothesis
        Strategy strategy = findStrategy(strategyId, hypothesis.getStrategies());
        
        // return the strategy found
        return strategy;
    }

    @Override
    public Study addStudy(String hypothesisId, String strategyId, String studyId, String studyType) {
        
        // instantiate new study
        Study study = null;
        // TODO create factory for studies http://code.google.com/p/tekstratego/issues/detail?id=4
        if (studyType.equalsIgnoreCase("or")) {
            study = new OrStudy();
        } else if (studyType.equalsIgnoreCase("and")) {
            study = new AndStudy();
        } else if (studyType.equalsIgnoreCase("gt")) {
            study = new GreaterThanStudy();
        } else if (studyType.equalsIgnoreCase("lt")) {
            study = new LessThanStudy();
        } else if (studyType.equalsIgnoreCase("et")) {
            study = new EqualToStudy();
        }
        
        // set an unique id for the new study
        study.setId(UUID.randomUUID().toString());
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // get strategy from hypothesis
        Strategy strategy = findStrategy(strategyId, hypothesis.getStrategies());
        
        // get study to add to
        AbstractOperatorStudy parentStudy = (AbstractOperatorStudy)findStudy(studyId, strategy.getStudy());
        
        // add study to parentStudy
        parentStudy.getStudies().add(study);
        
        // save hypothesis to memory
        thinkerDAO.createHypothesis(hypothesis);

        // return hypothesis from memory
        return getStudy(hypothesisId, strategyId, study.getId());
    }
    
    @Override
    public Study getStudy(String hypothesisId, String strategyId, String studyId) {
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // get strategy from hypothesis
        Strategy strategy = findStrategy(strategyId, hypothesis.getStrategies());
        
        // get study to add to
        Study study = findStudy(studyId, strategy.getStudy());
        
        // return the study found
        return study;
    }

    private Strategy findStrategy(String strategyId, List<Strategy> strategies) {
        
        for (Strategy strategy : strategies) {
            if (strategy.getId().equalsIgnoreCase(strategyId)) {
                return strategy;
            }
        }
        
        return null;
    }
    
    private Study findStudy(String studyId, Study study) {
        
        if (study.getId().equals(studyId)) {
            return study;
        }
        
        if (study instanceof AbstractCalculationStudy) {
            return null;
        }
        
        for (Study childStudy : ((AbstractOperatorStudy)study).getStudies()) {
            
            Study returnedStudy = findStudy(studyId, childStudy);
            
            if (returnedStudy != null) {
                return returnedStudy;
            }
        }
        
        return null;
    }

    public ThinkerDAO getThinkerDAO() {
        return thinkerDAO;
    }

    public void setThinkerDAO(ThinkerDAO thinkerDAO) {
        this.thinkerDAO = thinkerDAO;
    }

}
