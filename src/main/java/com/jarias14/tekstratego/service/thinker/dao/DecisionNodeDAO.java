package com.jarias14.tekstratego.service.thinker.dao;

import com.jarias14.tekstratego.service.thinker.model.DecisionNode;

/**
 * Created by jarias14 on 3/28/2015.
 */
public interface DecisionNodeDAO {

    public boolean putDecisionNode(String id, DecisionNode node);

    public DecisionNode getDecisionNode(String id);

}
