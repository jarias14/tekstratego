package com.jarias14.tekstratego.service.trader.biz.transactionmanager;

import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;

/**
 * Created by jarias14 on 4/14/15.
 */
public class TradeExecutionTransactionManager implements TransactionManager<TradeRequest, Position> {

    private DataAccessObject<TradeRequest, Position> tradeExecutionDao;

    @Override
    public Position process(TradeRequest tradeRequest) {
        return tradeExecutionDao.request(tradeRequest);
    }

    public void setTradeExecutionDao(DataAccessObject<TradeRequest, Position> tradeExecutionDao) {
        this.tradeExecutionDao = tradeExecutionDao;
    }
}
