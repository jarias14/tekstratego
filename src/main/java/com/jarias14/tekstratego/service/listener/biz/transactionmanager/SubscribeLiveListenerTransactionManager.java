package com.jarias14.tekstratego.service.listener.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeLiveListenerTransactionManager implements TransactionManager<RawDataRequest, RawDataResponse> {

    private DataAccessObject<RawDataRequest, Boolean> liveDataDao;

    @Override
    public RawDataResponse process(RawDataRequest request) {
        return new RawDataResponse(request, liveDataDao.request(request));
    }

    public void setLiveDataDao(DataAccessObject<RawDataRequest, Boolean> liveDataDao) {
        this.liveDataDao = liveDataDao;
    }
}
