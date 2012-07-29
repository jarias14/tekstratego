package com.jarias14.tekstratego.service.thinker.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.core.task.TaskExecutor;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.dao.ThinkerDAO;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.Position;
import com.jarias14.tekstratego.service.thinker.model.TradeAlert;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractCalculationStudy;
import com.jarias14.tekstratego.service.thinker.model.study.AbstractOperatorStudy;

public class DefaultThinkerService implements ThinkerService {
    
    private ThinkerDAO thinkerDAO;
    private TaskExecutor taskExecutor;

    @Override
    public Hypothesis createHypothesis(Hypothesis hypothesis) {
        
        // get a unique id
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
        hypothesis.getStrategies().put(strategy.getId(), strategy);
        
        // save to memory
        thinkerDAO.createHypothesis(hypothesis);
        
        // return new strategy from memory
        return getStrategy(hypothesisId, strategy.getId());
    }

    @Override
    public List<TradeAlert> getAlerts(String hypothesisId, Calendar date, List<Position> positions) {

        // get the hypothesis from memory
        Hypothesis hypothesis = getHypothesis(hypothesisId);

        // spoon process
        List<TradeAlert> alerts = getDecision(positions, hypothesis, date);

        // send those babies back!
        return alerts;
    }

    private List<TradeAlert> getDecision(List<Position> positions,
            Hypothesis hypothesis, Calendar today) {
        
        // init return variable
        List<TradeAlert> alerts = new ArrayList<TradeAlert>();
        
        // get the indicator data needed for all studies (do this once, here, for performance)
        Map<String, Map<String,SortedMap<Calendar, BigDecimal>>> data = getRequiredData(hypothesis, today);
        
        // get the decisions from each strategy
        for (Strategy strategy : hypothesis.getStrategies().values()) {
            for (Stock stock : strategy.getStocks()) {
                
                TradeAlert alert = getDecision(strategy, stock, data.get(stock.getSymbol()));
                
                if (alert != null) {
                    alerts.add(alert);
                }
            }
        }
        
        return alerts;
    }
    
    private TradeAlert getDecision(Strategy strategy, Stock stock, Map<String,SortedMap<Calendar, BigDecimal>> data) {
        
        // init return variable
        TradeAlert alert = null;
        
        // if the study returns true, we have a trade alert
        if (strategy.getStudy().execute(data)) {
            alert = new TradeAlert(stock, strategy);
        }
        
        return alert;
    }

    private Map<String, Map<String, SortedMap<Calendar, BigDecimal>>> getRequiredData(
            Hypothesis hypothesis, Calendar today) {
        
        // init return variable
        Map<String, Map<String, SortedMap<Calendar, BigDecimal>>> data = new HashMap<String, Map<String, SortedMap<Calendar, BigDecimal>>>();
        
        // init variable to hold number of bars needed from each indicator
        Map<String, Integer> numOfNecessaryBars = new TreeMap<String, Integer>();
        
        // get the number of bars needed for each indicator
        for (Strategy strategy : hypothesis.getStrategies().values()) {
            populateNumberOfNecessaryBars(hypothesis, strategy.getStudy(), numOfNecessaryBars);
        }
        
        // get <numOfNecessaryBars> for each indicator
        for (String indicatorId : numOfNecessaryBars.keySet()) {
            for (Strategy strategy : hypothesis.getStrategies().values()) {
                for (Stock stock : strategy.getStocks()) {
                    
                    if (data.get(stock.getSymbol()) == null) {
                        data.put(stock.getSymbol(), new HashMap<String, SortedMap<Calendar, BigDecimal>>());
                    }
                    
                    if (data.get(stock.getSymbol()).get(indicatorId) == null) {
                        data.get(stock.getSymbol()).put(indicatorId, thinkerDAO.getIndicatorValues(indicatorId, stock, today, numOfNecessaryBars.get(indicatorId)));
                    }
                }
            }
        }
        
        return data;
    }
    
    private void populateNumberOfNecessaryBars(Hypothesis hypothesis, Study parentStudy, Map<String, Integer> numOfNecessaryBars) {
        
        // if current study is a operator, then recurse
        if (parentStudy instanceof AbstractOperatorStudy) {
            for (Study childStudy : ((AbstractOperatorStudy) parentStudy).getStudies()) {
                populateNumberOfNecessaryBars(hypothesis, childStudy, numOfNecessaryBars);
            }
        }

        // if rootStudy is a calculation, then get the values for it
        if (parentStudy instanceof AbstractCalculationStudy) {
            
            String indicatorId = ((AbstractCalculationStudy) parentStudy).getIndicatorId();
            
            Integer indicatorBars = ((AbstractCalculationStudy) parentStudy).getNumberOfBarsBeforeCurrent();
            
            if (!numOfNecessaryBars.containsKey(indicatorId) || numOfNecessaryBars.get(indicatorId) < indicatorBars) {
                numOfNecessaryBars.put(indicatorId, indicatorBars);
            }
        }
    }

    @Override
    public Strategy getStrategy(String hypothesisId, String strategyId) {
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // get strategy from hypothesis
        Strategy strategy = hypothesis.getStrategies().get(strategyId);
        
        // return the strategy found
        return strategy;
    }

    @Override
    public Study addStudy(String hypothesisId, String strategyId, String studyId, Study study) {
        
        // set an unique id for the new study
        study.setId(UUID.randomUUID().toString());
        
        // get hypothesis from memory
        Hypothesis hypothesis = thinkerDAO.readHypothesis(hypothesisId);
        
        // get strategy from hypothesis
        Strategy strategy = hypothesis.getStrategies().get(strategyId);
        
        // get study to add to
        AbstractOperatorStudy parentStudy = (AbstractOperatorStudy)findStudy(studyId, strategy.getStudy());
        
        // add study to parentStudy
        parentStudy.getStudies().add(study);
        
        // assign parent study id
        study.setParentId(parentStudy.getId());
        
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
        Strategy strategy = hypothesis.getStrategies().get(strategyId);
        
        // get study to add to
        Study study = findStudy(studyId, strategy.getStudy());
        
        // return the study found
        return study;
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

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}
