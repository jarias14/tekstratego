package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class RestPricerServiceImpl implements RestPricerService {

    private ApplicationService<DataPointDescription, DataPointDescription> newIndicatorApplicationService;
    private ApplicationService<MarketDataNotification, Set<DataPointDescription>> marketDataNotificationApplicationService;
    private ApplicationService<MarketDataRequest, DataPoint> marketDataRequestApplicationService;

    @Override
    public DataPointDescription createIndicator(DataPointDescription request) {
        return newIndicatorApplicationService.serviceRequest(request);
    }

    @Override
    public DataPoint requestMarketData(String indicatorId, String stockExchange, String stockSymbol, String epochTime) {
        MarketDataRequest marketDataRequest = new MarketDataRequest();
        marketDataRequest.setId(indicatorId);
        marketDataRequest.setStock(new Stock(stockSymbol, StockExchange.valueOf(stockExchange)));
        marketDataRequest.setTime(Long.valueOf(epochTime));

        return marketDataRequestApplicationService.serviceRequest(marketDataRequest);
    }

    @Override
    public Set<DataPointDescription> addDataPoint(MarketDataNotification request) {
        return marketDataNotificationApplicationService.serviceRequest(request);
    }

    @Required
    public void setNewIndicatorApplicationService(ApplicationService<DataPointDescription, DataPointDescription> newIndicatorApplicationService) {
        this.newIndicatorApplicationService = newIndicatorApplicationService;
    }

    @Required
    public void setMarketDataRequestApplicationService(ApplicationService<MarketDataRequest, DataPoint> marketDataRequestApplicationService) {
        this.marketDataRequestApplicationService = marketDataRequestApplicationService;
    }

    @Required
    public void setMarketDataNotificationApplicationService(ApplicationService<MarketDataNotification, Set<DataPointDescription>> marketDataNotificationApplicationService) {
        this.marketDataNotificationApplicationService = marketDataNotificationApplicationService;
    }
}
