package com.jarias14.tekstratego.service.manager.biz.processors;

import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.service.manager.dao.resources.PricerServiceTradeRequest;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.models.Trade;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class ManagedAccountStrategyProcessor implements Processor<ManagedAccount> {

    private DataAccessObject<PricerServiceTradeRequest, Trade> thinkerServiceDao;

    @Override
    public void process(ManagedAccount managedAccount) {

        List<Trade> newTrades = new ArrayList<>();

        newTrades.addAll(
                managedAccount.getBuyStrategyIds().stream().flatMap(strategyId ->
                                managedAccount.getStocks().stream().map(stock ->
                                        thinkerServiceDao.request(new PricerServiceTradeRequest(strategyId, stock, System.currentTimeMillis() / 1000))).filter(Trade::getStrategyDecision)
                ).collect(Collectors.toList()));

        newTrades.addAll(
                managedAccount.getSellStrategyIds().stream().flatMap(strategyId ->
                                managedAccount.getStocks().stream().map(stock ->
                                        thinkerServiceDao.request(new PricerServiceTradeRequest(strategyId, stock, System.currentTimeMillis() / 1000))).filter(Trade::getStrategyDecision)
                ).collect(Collectors.toList()));

        managedAccount.getPotentialTrades().addAll(newTrades);
    }


    @Required
    public void setThinkerServiceDao(DataAccessObject<PricerServiceTradeRequest, Trade> thinkerServiceDao) {
        this.thinkerServiceDao = thinkerServiceDao;
    }
}
