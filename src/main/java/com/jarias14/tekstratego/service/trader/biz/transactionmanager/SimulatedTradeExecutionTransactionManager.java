package com.jarias14.tekstratego.service.trader.biz.transactionmanager;

import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;

/**
 * Created by jarias14 on 4/14/2015.
 */
public class SimulatedTradeExecutionTransactionManager implements TransactionManager<TradeRequest, Position> {

    @Override
    public Position process(TradeRequest tradeRequest) {

        Position position = new Position();
        position.setAverageCost(tradeRequest.getPrice());
        position.setPosition(tradeRequest.getQuantity());

        return position;
    }
}
