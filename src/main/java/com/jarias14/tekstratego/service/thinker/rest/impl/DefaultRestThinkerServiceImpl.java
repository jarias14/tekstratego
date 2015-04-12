package com.jarias14.tekstratego.service.thinker.rest.impl;

import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionNode;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionRequest;
import org.springframework.beans.factory.annotation.Required;

public class DefaultRestThinkerServiceImpl implements RestThinkerService{

    private ApplicationService<DecisionNode, DecisionNode> newDecisionNodeApplicationService;
    private ApplicationService<DecisionRequest, Boolean> performDecisionApplicationService;

    @Override
    public DecisionNode createDecisionNode(DecisionNode decisionNode) {
        return newDecisionNodeApplicationService.serviceRequest(decisionNode);
    }

    @Override
    public Boolean getDecision(String decisionNodeId, String epochTime) {
        DecisionRequest decisionRequest = new DecisionRequest(decisionNodeId, Long.valueOf(epochTime));
        return performDecisionApplicationService.serviceRequest(decisionRequest);
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
