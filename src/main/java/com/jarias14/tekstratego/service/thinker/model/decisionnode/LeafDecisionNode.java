package com.jarias14.tekstratego.service.thinker.model.decisionnode;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.service.thinker.model.DecisionNode;

import java.util.Map;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class LeafDecisionNode implements DecisionNode<Object> {

    private boolean decision;

    public LeafDecisionNode(boolean decision) {
        this.decision = decision;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isLeafNode() {
        return true;
    }

    @Override
    public boolean getDecision() {
        return decision;
    }

    @Override
    public String getNextDecisionNode(Map<DataPointDetails, DataPoint<Object>>... dataPoints) {
        return null;
    }
}
