package com.jarias14.tekstratego.service.thinker.model;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointDetails;

import java.util.Map;

/**
 * Created by jarias14 on 3/28/2015.
 */
public interface DecisionNode<TYPE> {

    public String getId();

    public boolean isLeafNode();

    public boolean getDecision();

    public String getNextDecisionNode(Map<DataPointDetails, DataPoint<TYPE>> ... dataPoints);

}
