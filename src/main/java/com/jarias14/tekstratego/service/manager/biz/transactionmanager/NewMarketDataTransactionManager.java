package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.MarketDataNotification;
import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.dao.resources.PricerServiceTradeRequest;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.models.Trade;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class NewMarketDataTransactionManager implements TransactionManager<MarketDataNotification, Boolean> {

    private ManagedAccountStore managedAccountStore;
    private DataAccessObject<PricerServiceTradeRequest, Trade> thinkerServiceDao;
    private DataAccessObject<TradeRequest, Position> traderServiceDao;

    @Override
    public Boolean process(MarketDataNotification marketDataNotification) {

        List<ManagedAccount> managedAccounts = managedAccountStore.getManagedAccounts(marketDataNotification.getStock());

        managedAccounts.stream()
                .forEach(managedAccount -> {

                        managedAccount.getStrategies().stream()
                                .filter(strategies ->
                                        strategies.getStocks().contains(marketDataNotification.getStock()))
                                .filter(strategy ->
                                        requestDecision(marketDataNotification, strategy.getStrategyId()).getStrategyDecision())
                                .forEach(strategy -> {
                                    Trade trade = new Trade();
                                    trade.setStrategy(strategy);
                                    trade.setShares(strategy.getSharesToInvest());
                                    trade.setStrategyDecision(true);
                                    trade.setStock(marketDataNotification.getStock());
                                    trade.setTime(marketDataNotification.getTime());
                                    trade.setCost((Double) marketDataNotification.getDataPoints().stream().filter(dataPoint -> DataPointIndicator.RAW_CLOSE.equals(dataPoint.getDetails().getIndicator())).findFirst().get().getDataPoints().get(0).getValue());
                                    managedAccount.getPotentialTrades().add(trade);
                                });

                        managedAccount.getPotentialTrades().stream().forEach( potentialTrade -> {
                                    TradeRequest tradeRequest = new TradeRequest();
                                    tradeRequest.setStock(potentialTrade.getStock());
                                    tradeRequest.setPrice(potentialTrade.getCost());
                                    tradeRequest.setQuantity(potentialTrade.getShares());
                                    tradeRequest.setSimulated(managedAccount.isSimulated());

                                    Position position = traderServiceDao.request(tradeRequest);

                                    potentialTrade.setPosition(position);

                                    managedAccount.getExecutedTrades().add(potentialTrade);
                                    managedAccount.getPotentialTrades().remove(potentialTrade);
                                }
                        );
                });

        return true;
    }

    private Trade requestDecision(MarketDataNotification marketDataNotification, String strategyId) {
        return thinkerServiceDao.request(new PricerServiceTradeRequest(strategyId, marketDataNotification.getStock(), marketDataNotification.getTime()));
    }


    @Required
    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }

    @Required
    public void setThinkerServiceDao(DataAccessObject<PricerServiceTradeRequest, Trade> thinkerServiceDao) {
        this.thinkerServiceDao = thinkerServiceDao;
    }

    @Required
    public void setTraderServiceDao(DataAccessObject<TradeRequest, Position> traderServiceDao) {
        this.traderServiceDao = traderServiceDao;
    }
}