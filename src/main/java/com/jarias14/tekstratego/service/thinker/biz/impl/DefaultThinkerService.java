package com.jarias14.tekstratego.service.thinker.biz.impl;

import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.dao.DecisionNodeDAO;
import com.jarias14.tekstratego.service.thinker.model.ComparableRange;
import com.jarias14.tekstratego.service.thinker.model.DecisionNode;
import com.jarias14.tekstratego.service.thinker.model.decisionnode.ContinuousDecisionNode;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

public class DefaultThinkerService implements ThinkerService {

    @Setter
    private DecisionNodeDAO decisionNodeDao;

    @Override
    public String createNode(String indicatorName, String type, Map<ComparableRange, String> decisionNodes) {

        String nodeId = UUID.randomUUID().toString();

        DataPointIndicator indicator = DataPointIndicator.valueOf(indicatorName.toUpperCase());

        DecisionNode decisionNode = new ContinuousDecisionNode<>(nodeId, null, decisionNodes);

        decisionNodeDao.putDecisionNode(nodeId, decisionNode);

        return nodeId;
    }
}