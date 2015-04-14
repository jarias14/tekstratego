package com.jarias14.tekstratego.service.pricer.biz.transactionmanagers;

import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.TransactionCache;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;

/**
 * Created by jarias14 on 4/5/2015.
 */
public class MarketDataNotificationTransactionManagerImpl extends MarketDataBaseTransactionManager implements TransactionManager<MarketDataNotification, Set<DataPointDescription>> {

    private TransactionCache<MarketDataRequest, DataPoint> marketDataRequestTransactionCache;

    @Override
    public Set<DataPointDescription> process(MarketDataNotification marketDataNotification) {

        // get list of indicators that should process this stock
        Set<DataPointDescription> indicators = super.getIndicators(marketDataNotification);

        // get a list of raw data indicators necessary for this stock
        Set<DataPointIndicator> requiredRawIndicators = super.getRawIndicators(indicators);

        // get the max number of bars needed for this operation
        Integer requiredNumberOfPeriods = super.getRequiredNumberOfPeriods(indicators);

        // get all the raw data necessary for this transaction
        Set<DataPointCollection> dataPointCollectionSet = super.getRequiredPeriods(marketDataNotification, requiredRawIndicators, requiredNumberOfPeriods);

        // process transaction for all indicators
        indicators.stream()
                .forEach(indicator -> {

                    DataPointTimableDescription indicatorRequest = new DataPointTimableDescription();
                    indicatorRequest.setDetails(indicator.getDetails());
                    indicatorRequest.setTime(marketDataNotification.getTime());
                    DataPoint dataPoint = super.requestMarketData(indicatorRequest, dataPointCollectionSet);


                    MarketDataRequest marketDataRequest = new MarketDataRequest();
                    marketDataRequest.setId(indicator.getId());
                    marketDataRequest.setTime(marketDataNotification.getTime());
                    marketDataRequest.setStock(marketDataNotification.getStock());
                    marketDataRequestTransactionCache.putDataPoint(marketDataRequest, dataPoint);
                });

        return indicators;
    }

    @Required
    public void setMarketDataRequestTransactionCache(TransactionCache<MarketDataRequest, DataPoint> marketDataRequestTransactionCache) {
        this.marketDataRequestTransactionCache = marketDataRequestTransactionCache;
    }
}
