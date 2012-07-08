package com.jarias14.tekstratego.service.thinker.biz;

import java.util.Calendar;
import java.util.List;

import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Position;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.TradeAlert;

public interface ThinkerService {
    
    public Hypothesis createHypothesis(Hypothesis resource);
    public Hypothesis getHypothesis(String hypothesisId);
    public Strategy addStrategy(String hypothesisId, Strategy strategy);
    public Strategy getStrategy(String hypothesisId, String strategyId);
    public Study addStudy(String hypothesisId, String strategyId, String studyId, Study study);
    public Study getStudy(String hypothesisId, String strategyId, String studyId);
    public List<TradeAlert> getAlerts(String hypothesisId, Calendar date, List<Position> positions);
    
}
