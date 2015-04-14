package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.manager.models.Trade;
import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionNode;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionRequest;
import org.springframework.beans.factory.annotation.Required;

public class RestThinkerServiceImpl implements RestThinkerService {

    private ApplicationService<DecisionNode, DecisionNode> newDecisionNodeApplicationService;
    private ApplicationService<DecisionRequest, Boolean> performDecisionApplicationService;

    @Override
    public DecisionNode createDecisionNode(DecisionNode decisionNode) {
        return newDecisionNodeApplicationService.serviceRequest(decisionNode);
    }

    @Override
    public Trade getDecision(String decisionNodeId, String symbol, String exchange, String epochTime) {

        DecisionRequest decisionRequest = new DecisionRequest(new Stock(symbol, null), decisionNodeId, Long.valueOf(epochTime));

        Trade trade = new Trade();
        trade.setStrategyDecision(performDecisionApplicationService.serviceRequest(decisionRequest));
        trade.setStock(decisionRequest.getStock());
        trade.setTime(decisionRequest.getEpochTime());

        return trade;
    }

    @Required
    public void setNewDecisionNodeApplicationService(ApplicationService<DecisionNode, DecisionNode> newDecisionNodeApplicationService) {
        this.newDecisionNodeApplicationService = newDecisionNodeApplicationService;
    }

    @Required
    public void setPerformDecisionApplicationService(ApplicationService<DecisionRequest, Boolean> performDecisionApplicationService) {
        this.performDecisionApplicationService = performDecisionApplicationService;
    }
}
