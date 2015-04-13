package com.jarias14.tekstratego.service.listener.biz.processors;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.manager.models.MarketDataNotification;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */

public class RawDataProcessor implements Processor<Set<DataPointCollection>> {

    private DataAccessObject<MarketDataNotification, Boolean> managerServiceNewMarketDataNotificationDao;
    private DataStore rawDataStore;

    @Override
    public void process(Set<DataPointCollection> historicalMarketData) {

        Stock stock = historicalMarketData.stream().map(DataPointCollection::getStock).findFirst().get();
        DataPointDetails dataPointDetails = historicalMarketData.stream().map(DataPointCollection::getDetails).findFirst().get();
        long time = historicalMarketData.stream().map(DataPointCollection::getDataPoints).map(List::stream).findFirst().get().findFirst().get().getTime();

        historicalMarketData.stream()
                .map(DataPointCollection::getDataPoints)
                .flatMap(List::stream)
                .forEach(dataPoint ->
                        rawDataStore.putDataPoint(stock, dataPoint, dataPointDetails));

        // make call to manager to notify new price has been added
        managerServiceNewMarketDataNotificationDao.request(new MarketDataNotification(stock, time));
    }


    @Required
    public void setRawDataStore(DataStore rawDataStore) {
        this.rawDataStore = rawDataStore;
    }

    @Required
    public void setManagerServiceNewMarketDataNotificationDao(DataAccessObject<MarketDataNotification, Boolean> managerServiceNewMarketDataNotificationDao) {
        this.managerServiceNewMarketDataNotificationDao = managerServiceNewMarketDataNotificationDao;
    }
}
