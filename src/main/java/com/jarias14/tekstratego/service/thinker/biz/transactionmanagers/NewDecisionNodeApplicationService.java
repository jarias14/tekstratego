package com.jarias14.tekstratego.service.thinker.biz.transactionmanagers;

import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.thinker.cache.DecisionNodeDataStore;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionNode;
import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class NewDecisionNodeApplicationService implements TransactionManager<DecisionNode, DecisionNode> {

    private DecisionNodeDataStore decisionNodeDataStore;

    @Override
    public DecisionNode process(DecisionNode decisionNode) {

        decisionNode.setId(UUID.randomUUID().toString());
        decisionNodeDataStore.store(decisionNode);
        return decisionNodeDataStore.retrieve(decisionNode.getId());
    }

    @Required
    public void setDecisionNodeDataStore(DecisionNodeDataStore decisionNodeDataStore) {
        this.decisionNodeDataStore = decisionNodeDataStore;
    }
}
