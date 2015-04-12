package com.jarias14.tekstratego.service.listener.biz.transactionmanager;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeHistoricListenerTransactionManager implements TransactionManager<RawDataRequest, RawDataResponse> {

    private DataAccessObject<RawDataRequest, RawDataResponse> historicDataDao;
    private Processor<Set<DataPointCollection>> simulator;

    @Override
    public RawDataResponse process(RawDataRequest rawDataRequest) {

        RawDataResponse rawDataResponse = historicDataDao.request(rawDataRequest);

        if (rawDataResponse.isProcessable()) {
            simulator.process(rawDataResponse.getDataPointCollectionSet());
        }

        return rawDataResponse;
    }

    public void setSimulator(Processor<Set<DataPointCollection>> simulator) {
        this.simulator = simulator;
    }

    public void setHistoricDataDao(DataAccessObject<RawDataRequest, RawDataResponse> historicDataDao) {
        this.historicDataDao = historicDataDao;
    }
}
