package com.jarias14.tekstratego.service.pricer.biz.transactionmanagers;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogStore;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/5/2015.
 */
public class NewDataPointTransactionManagerImpl implements TransactionManager<DataPointTimableDescription, Set<DataPointDescription>> {

    private DataStore rawDataStore;
    private IndicatorCatalogStore indicatorCatalogDao;
    private Processor<NewDataPointIndicatorUpdateRequest> updateSimpleIndicatorProcessor;

    @Override
    public Set<DataPointDescription> process(DataPointTimableDescription dataPointDescription) {

        // get list of indicators that should process this stock
        Set<DataPointDescription> indicators =
                indicatorCatalogDao.getIndicatorsByStock(dataPointDescription.getStock());

        // get a list of raw data indicators necessary for this stock
        List<DataPointIndicator> necessaryRawDataPointIndicators =
                indicators.stream()
                        .map(DataPointDescription::getDetails)
                        .map(DataPointDetails::getIndicator)
                        .flatMap(t -> {
                            return t.getNecessaryRawDataPoints().stream();
                        })
                        .distinct()
                        .collect(Collectors.toList());

        // get the max number of bars needed for this operation
        Integer necessaryNumberOfDataPoints =
                indicators.stream()
                        .map(DataPointDescription::getDetails)
                        .map(DataPointDetails::getNecessaryDataPointBars)
                        .max(Comparator.comparing(i -> i.intValue()))
                        .get();

        // get all the raw data necessary for this transaction
        Set<DataPointCollection> dataPointCollectionSet =
                necessaryRawDataPointIndicators
                        .stream()
                        .distinct()
                        .map(necessaryRawDataPointIndicator ->
                                rawDataStore.getDataPoints(dataPointDescription.getStock(), necessaryRawDataPointIndicator, dataPointDescription.getTime(), necessaryNumberOfDataPoints))
                        .collect(Collectors.toSet());

        // process transaction for all indicators
        indicators.stream()
                .forEach(indicator -> {
                    DataPointTimableDescription indicatorRequest = new DataPointTimableDescription();
                    indicatorRequest.setDetails(indicator.getDetails());
                    indicatorRequest.setTime(dataPointDescription.getTime());
                    updateSimpleIndicatorProcessor.process(new NewDataPointIndicatorUpdateRequest(indicatorRequest, dataPointCollectionSet));
                });

        return indicators;
    }

    @Required
    public void setIndicatorCatalogDao(IndicatorCatalogStore indicatorCatalogDao) {
        this.indicatorCatalogDao = indicatorCatalogDao;
    }

    @Required
    public void setRawDataStore(DataStore rawDataStore) {
        this.rawDataStore = rawDataStore;
    }

    @Required
    public void setUpdateSimpleIndicatorProcessor(Processor<NewDataPointIndicatorUpdateRequest> updateSimpleIndicatorProcessor) {
        this.updateSimpleIndicatorProcessor = updateSimpleIndicatorProcessor;
    }
}
