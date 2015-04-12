package com.jarias14.tekstratego.service.listener.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeLiveListenerTransactionManager implements TransactionManager<RawDataRequest, RawDataResponse> {

    private DataAccessObject<RawDataRequest, RawDataResponse> liveDataDao;

    @Override
    public RawDataResponse process(RawDataRequest request) {
        return liveDataDao.request(request);
    }

    public void setLiveDataDao(DataAccessObject<RawDataRequest, RawDataResponse> liveDataDao) {
        this.liveDataDao = liveDataDao;
    }
}
