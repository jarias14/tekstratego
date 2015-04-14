package com.jarias14.tekstratego.service.trader.biz.transactionmanager;

import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/14/15.
 */
public class LiveTradeExecutionTransactionManager implements TransactionManager<TradeRequest, Position> {

    private DataAccessObject<TradeRequest, Position> tradeExecutionDao;

    @Override
    public Position process(TradeRequest tradeRequest) {
        return tradeExecutionDao.request(tradeRequest);
    }

    @Required
    public void setTradeExecutionDao(DataAccessObject<TradeRequest, Position> tradeExecutionDao) {
        this.tradeExecutionDao = tradeExecutionDao;
    }
}
