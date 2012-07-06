package com.jarias14.tekstratego.service.thinker.biz;

import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;

public interface ThinkerService {
    
    public Hypothesis createHypothesis(Hypothesis resource);
    public Hypothesis getHypothesis(String hypothesisId);
    public Strategy addStrategy(String hypothesisId, Strategy strategy);
    public Strategy getStrategy(String hypothesisId, String strategyId);
    public Study addStudy(String hypothesisId, String strategyId, String studyId, Study study);
    public Study getStudy(String hypothesisId, String strategyId, String studyId);
    public boolean runHypothesis(String hypothesisId);
    
}
