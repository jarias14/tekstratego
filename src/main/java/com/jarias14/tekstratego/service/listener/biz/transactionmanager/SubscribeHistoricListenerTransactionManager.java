package com.jarias14.tekstratego.service.listener.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeHistoricListenerTransactionManager implements TransactionManager<RawDataRequest, RawDataResponse> {

    private DataAccessObject<RawDataRequest, Boolean> historicDataDao;

    @Override
    public RawDataResponse process(RawDataRequest rawDataRequest) {

        Boolean response = historicDataDao.request(rawDataRequest);
        return new RawDataResponse(rawDataRequest, response);
    }

    @Required
    public void setHistoricDataDao(DataAccessObject<RawDataRequest, Boolean> historicDataDao) {
        this.historicDataDao = historicDataDao;
    }
}
