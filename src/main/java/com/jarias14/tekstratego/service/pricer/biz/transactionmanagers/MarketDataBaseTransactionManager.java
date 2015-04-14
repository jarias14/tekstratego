package com.jarias14.tekstratego.service.pricer.biz.transactionmanagers;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogStore;
import org.springframework.beans.factory.annotation.Required;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/14/2015.
 */
public abstract class MarketDataBaseTransactionManager {

    private DataStore rawDataStore;
    private IndicatorCatalogStore indicatorCatalogDao;
    private TransactionManager<NewDataPointIndicatorUpdateRequest, DataPoint> updateSimpleIndicatorProcessor;

    protected Set<DataPointDescription> getIndicators(MarketDataNotification marketDataNotification) {
        // get list of indicators that should process this stock
        Set<DataPointDescription> indicators =
                indicatorCatalogDao.getIndicatorsByStock(marketDataNotification.getStock());

        return indicators;
    }

    protected DataPointDescription getIndicator(String indicatorId) {

        return indicatorCatalogDao.getIndicator(indicatorId);
    }

    protected Set<DataPointIndicator> getRawIndicators(Set<DataPointDescription> indicators) {
        // get a list of raw data indicators necessary for this stock
        Set<DataPointIndicator> necessaryRawDataPointIndicators =
                indicators.stream()
                        .map(DataPointDescription::getDetails)
                        .map(DataPointDetails::getIndicator)
                        .flatMap(t -> {
                            return t.getNecessaryRawDataPoints().stream();
                        })
                        .distinct()
                        .collect(Collectors.toSet());

        return necessaryRawDataPointIndicators;
    }

    protected Set<DataPointIndicator> getRawIndicators(DataPointDescription indicator) {
        return indicator.getDetails().getIndicator().getNecessaryRawDataPoints();
    }

    protected Integer getRequiredNumberOfPeriods(Set<DataPointDescription> indicators) {
        // get the max number of bars needed for this operation
        Integer requiredNumberOfPeriods =
                indicators.stream()
                        .map(DataPointDescription::getDetails)
                        .map(DataPointDetails::getParameters)
                        .map(m -> m.get(DataPointIndicatorParameter.REQUIRED_PERIODS))
                        .max(Comparator.comparing(i -> i.intValue()))
                        .get();

        return requiredNumberOfPeriods;
    }

    protected Integer getRequiredNumberOfPeriods(DataPointDescription indicator) {
        return indicator.getDetails().getParameters().get(DataPointIndicatorParameter.REQUIRED_PERIODS);
    }

    protected Set<DataPointCollection> getRequiredPeriods(MarketDataNotification marketDataNotification, Set<DataPointIndicator> indicators, Integer numberOfPeriods) {
        // get all the raw data necessary for this transaction
        Set<DataPointCollection> dataPointCollectionSet =
                indicators
                        .stream()
                        .distinct()
                        .map(necessaryRawDataPointIndicator ->
                                rawDataStore.getDataPoints(marketDataNotification.getStock(), necessaryRawDataPointIndicator, marketDataNotification.getTime(), numberOfPeriods))
                        .collect(Collectors.toSet());

        return dataPointCollectionSet;
    }

    protected DataPoint requestMarketData(DataPointTimableDescription indicatorRequest, Set<DataPointCollection> dataPointCollectionSet) {
        NewDataPointIndicatorUpdateRequest request = new NewDataPointIndicatorUpdateRequest(indicatorRequest, dataPointCollectionSet);
        DataPoint dataPoint = updateSimpleIndicatorProcessor.process(request);
        return dataPoint;
    }


    @Required
    public void setRawDataStore(DataStore rawDataStore) {
        this.rawDataStore = rawDataStore;
    }

    @Required
    public void setIndicatorCatalogDao(IndicatorCatalogStore indicatorCatalogDao) {
        this.indicatorCatalogDao = indicatorCatalogDao;
    }

    @Required
    public void setUpdateSimpleIndicatorProcessor(TransactionManager<NewDataPointIndicatorUpdateRequest, DataPoint> updateSimpleIndicatorProcessor) {
        this.updateSimpleIndicatorProcessor = updateSimpleIndicatorProcessor;
    }

}
