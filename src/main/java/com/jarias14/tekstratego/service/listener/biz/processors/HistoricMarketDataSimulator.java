package com.jarias14.tekstratego.service.listener.biz.processors;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.skeleton.Processor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */

@EnableAsync
public class HistoricMarketDataSimulator<DATA_TYPE> implements Processor<Set<DataPointCollection<DATA_TYPE>>>{

    private DataStore rawDataStore;
    private static long WAIT_TIME_IN_SECONDS = (long)0.5;

    @Override
    @Async("historicMarketDataSimulatorExecutor")
    public void process(Set<DataPointCollection<DATA_TYPE>> historicalMarketData) {

        Integer min = historicalMarketData.stream().map(DataPointCollection::getDataPoints).map(List::size).min(Comparator.comparing(i -> i.intValue())).get();

        for (int i = 0 ; i < min; i++) {

            final int index = i;

            historicalMarketData.stream()
                    .forEach(dataPointCollection -> {
                        if (CollectionUtils.isNotEmpty(dataPointCollection.getDataPoints())) {
                            DataPoint dataPoint = dataPointCollection.getDataPoints().get(index);
                            rawDataStore.putDataPoint(dataPointCollection.getStock(), dataPoint, dataPointCollection.getDetails());
                        }
                    });

            // make call to pricer to notify new price has been added

            waitTimer(WAIT_TIME_IN_SECONDS);
        }
    }

    private void waitTimer(long seconds) {
        try {
            Thread.sleep(seconds * (long)1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRawDataStore(DataStore rawDataStore) {
        this.rawDataStore = rawDataStore;
    }
}
