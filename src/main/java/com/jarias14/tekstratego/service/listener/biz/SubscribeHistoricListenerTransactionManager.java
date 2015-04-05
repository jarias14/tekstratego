package com.jarias14.tekstratego.service.listener.biz;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.dao.impl.YahooMarketDataDAO;
import com.jarias14.tekstratego.service.listener.models.ListenerRequest;
import com.jarias14.tekstratego.service.listener.models.ListenerResponse;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeHistoricListenerTransactionManager implements TransactionManager<ListenerRequest, ListenerResponse> {

    private YahooMarketDataDAO marketDataDAO;
    private Processor<Set<DataPointCollection<Double>>> simulator;

    @Override
    public ListenerResponse process(ListenerRequest listenerRequest) {

        ListenerResponse response = new ListenerResponse();

        Set<DataPointCollection<Double>> historicalMarketData = marketDataDAO.getPriceBars(listenerRequest.getSymbol(), listenerRequest.getStartDate(), listenerRequest.getEndDate());


        simulator.process(historicalMarketData);

        response.setStockSymbol(listenerRequest.getSymbol());
        response.setData(historicalMarketData);

        return response;
    }

    public void setMarketDataDAO(YahooMarketDataDAO marketDataDAO) {
        this.marketDataDAO = marketDataDAO;
    }

    public void setSimulator(Processor<Set<DataPointCollection<Double>>> simulator) {
        this.simulator = simulator;
    }
}
