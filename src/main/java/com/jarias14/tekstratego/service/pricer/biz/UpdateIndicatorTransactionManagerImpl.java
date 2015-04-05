package com.jarias14.tekstratego.service.pricer.biz;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.pricer.biz.processor.UpdateSimpleIndicatorRequest;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogDao;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/5/2015.
 */
public class UpdateIndicatorTransactionManagerImpl implements TransactionManager<DataPointTimableDescription, Set<DataPointDescription>> {

    private DataStore rawDataStore;
    private IndicatorCatalogDao indicatorCatalogDao;
    private Processor<UpdateSimpleIndicatorRequest> updateSimpleIndicatorProcessor;

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
        Map<DataPointIndicator, DataPointCollection> dataPointIndicatorDataPointCollectionMap = new HashMap<>();
        necessaryRawDataPointIndicators.stream()
                .forEach(
                        necessaryRawDataPointIndicator -> {
                            DataPointCollection dataPointCollection =
                                    rawDataStore.getDataPoints(dataPointDescription.getStock(), necessaryRawDataPointIndicator, dataPointDescription.getTime(), necessaryNumberOfDataPoints);
                            dataPointIndicatorDataPointCollectionMap.put(necessaryRawDataPointIndicator, dataPointCollection);
                        });

        // process transaction for all indicators
        indicators.stream()
                .forEach(indicator -> {
                    DataPointTimableDescription indicatorRequest = (DataPointTimableDescription) indicator;
                    indicatorRequest.setTime(dataPointDescription.getTime());
                    updateSimpleIndicatorProcessor.process(new UpdateSimpleIndicatorRequest(indicatorRequest, dataPointIndicatorDataPointCollectionMap));
                });

        return indicators;
    }

    @Required
    public void setIndicatorCatalogDao(IndicatorCatalogDao indicatorCatalogDao) {
        this.indicatorCatalogDao = indicatorCatalogDao;
    }

    @Required
    public void setRawDataStore(DataStore rawDataStore) {
        this.rawDataStore = rawDataStore;
    }

    @Required
    public void setUpdateSimpleIndicatorProcessor(Processor<UpdateSimpleIndicatorRequest> updateSimpleIndicatorProcessor) {
        this.updateSimpleIndicatorProcessor = updateSimpleIndicatorProcessor;
    }
}
