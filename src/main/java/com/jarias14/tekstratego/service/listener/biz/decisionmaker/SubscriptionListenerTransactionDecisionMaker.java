package com.jarias14.tekstratego.service.listener.biz.decisionmaker;

import com.jarias14.tekstratego.common.skeleton.TransactionDecisionMaker;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class SubscriptionListenerTransactionDecisionMaker implements TransactionDecisionMaker<RawDataRequest> {

    @Override
    public String retrieveTransactionManagerId(RawDataRequest request) {
        return request.getStartTime() < 10 && request.getEndTime() < 10 ? "LIVE" : "HISTORIC";
    }
}
