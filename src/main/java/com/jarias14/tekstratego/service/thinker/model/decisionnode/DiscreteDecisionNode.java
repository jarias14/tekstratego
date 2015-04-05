package com.jarias14.tekstratego.service.thinker.model.decisionnode;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.service.thinker.model.DecisionNode;

import java.util.Map;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class DiscreteDecisionNode<TYPE extends Comparable> implements DecisionNode<TYPE> {

    private String id;
    private Map<TYPE, String> decisionNodes;
    private DataPointDetails dataPointIndicator;

    public DiscreteDecisionNode(String id, DataPointDetails dataPointIndicator, Map<TYPE, String> decisionNodes) {
        this.id = id;
        this.decisionNodes = decisionNodes;
        this.dataPointIndicator = dataPointIndicator;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isLeafNode() {
        return false;
    }

    @Override
    public boolean getDecision() {
        return false;
    }

    @Override
    public String getNextDecisionNode(Map<DataPointDetails, DataPoint<TYPE>>... dataPoints) {

        TYPE value = dataPoints[0].get(dataPointIndicator).getValue();

        String nextNodeId = decisionNodes.get(value);

        return nextNodeId;
    }
}
