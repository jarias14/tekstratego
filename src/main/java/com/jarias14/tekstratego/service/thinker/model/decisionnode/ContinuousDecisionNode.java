package com.jarias14.tekstratego.service.thinker.model.decisionnode;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.service.thinker.model.ComparableRange;
import com.jarias14.tekstratego.service.thinker.model.DecisionNode;

import java.util.Map;

/**
 * Created by jarias14 on 3/28/2015.
 */
public class ContinuousDecisionNode<TYPE  extends Comparable> implements DecisionNode<TYPE> {

    private String id;
    private DataPointDetails dataPointKey;
    private Map<ComparableRange, String> descendantNodes;

    public ContinuousDecisionNode(String id, DataPointDetails indicator, Map<ComparableRange, String> decisionNodes) {
        this.id = id;
        this.dataPointKey = indicator;
        this.descendantNodes = decisionNodes;
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

        TYPE value = dataPoints[0].get(dataPointKey).getValue();

        String nextDecisionNode = null;
        for (ComparableRange<TYPE> range : descendantNodes.keySet()) {
            if (isWithinRange(range, value)) {
                nextDecisionNode = descendantNodes.get(range);
            }
        }

        return nextDecisionNode;
    }

    private boolean isWithinRange(ComparableRange<TYPE> range, TYPE value) {
        return isEqualTo(range.getStart(), value)
                || isEqualTo(range.getEnd(), value)
                || ( isGreaterThan(range.getStart(), value) && isLessThan(range.getEnd(), value));
    }

    private boolean isGreaterThan(TYPE a, TYPE b) {
        return a.compareTo(b) > 0;
    }

    private boolean isLessThan(TYPE a, TYPE b) {
        return a.compareTo(b) < 0;
    }

    private boolean isEqualTo(TYPE a, TYPE b) {
        return a.compareTo(b) == 0;
    }


    public void setDataPointKey(DataPointDetails dataPointKey) {
        this.dataPointKey = dataPointKey;
    }
}
