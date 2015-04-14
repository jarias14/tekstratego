package com.jarias14.tekstratego.service.trader.biz.decisionmaker;

import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.TransactionDecisionMaker;

/**
 * Created by jarias14 on 4/14/2015.
 */
public class TradeExecutionTransactionDecisionMaker implements TransactionDecisionMaker<TradeRequest> {
    @Override
    public String retrieveTransactionManagerId(TradeRequest tradeRequest) {
        return tradeRequest.isBacktesting() ? "BACKTESTING" : "LIVE";
    }
}
