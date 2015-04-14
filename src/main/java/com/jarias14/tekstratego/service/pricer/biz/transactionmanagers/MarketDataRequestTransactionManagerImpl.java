package com.jarias14.tekstratego.service.pricer.biz.transactionmanagers;

import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;

import java.util.Set;

/**
 * Created by jarias14 on 4/5/2015.
 */
public class MarketDataRequestTransactionManagerImpl extends MarketDataBaseTransactionManager implements TransactionManager<MarketDataRequest, DataPoint> {

    @Override
    public DataPoint process(MarketDataRequest marketDataRequest) {

        // get list of indicators that should process this stock
        DataPointDescription indicator = getIndicator(marketDataRequest.getId());

        // get a list of raw data indicators necessary for this stock
        Set<DataPointIndicator> requiredRawIndicators = getRawIndicators(indicator);

        // get the max number of bars needed for this operation
        Integer requiredNumberOfPeriods = getRequiredNumberOfPeriods(indicator);

        // get all the raw data necessary for this transaction
        Set<DataPointCollection> dataPointCollectionSet = getRequiredPeriods(marketDataRequest, requiredRawIndicators, requiredNumberOfPeriods);

        // process transaction for all indicators
        DataPointTimableDescription indicatorRequest = new DataPointTimableDescription();
        indicatorRequest.setDetails(indicator.getDetails());
        indicatorRequest.setTime(marketDataRequest.getTime());

        // make market data request
        DataPoint dataPoint = requestMarketData(indicatorRequest, dataPointCollectionSet);

        return dataPoint;
    }



}
