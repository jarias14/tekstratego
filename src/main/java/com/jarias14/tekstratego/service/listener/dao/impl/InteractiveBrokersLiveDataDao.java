package com.jarias14.tekstratego.service.listener.dao.impl;

import com.ib.controller.ApiController;
import com.ib.controller.NewContract;
import com.ib.controller.Types;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class InteractiveBrokersLiveDataDao implements DataAccessObject<RawDataRequest, Boolean> {

    private Processor<Set<DataPointCollection>> rawDataProcessor;
    private ApiController ibController;

    @Override
    public Boolean request(RawDataRequest ibRequest) {

        InteractiveBrokersLiveDataCallbackHandler ibCallbackHandler = new InteractiveBrokersLiveDataCallbackHandler();
        ibCallbackHandler.setStock(ibRequest.getStock());
        ibCallbackHandler.setDataPointSize(ibRequest.getDataPointSize());
        ibCallbackHandler.setRawDataProcessor(rawDataProcessor);


        NewContract ibContract = new NewContract();
        ibContract.secType(Types.SecType.STK);
        ibContract.currency("USD");
        ibContract.exchange(ibRequest.getStock().getExchange().name());
        ibContract.symbol(ibRequest.getStock().getSymbol());

        ibController.reqRealTimeBars(ibContract, Types.WhatToShow.TRADES, false, ibCallbackHandler);

        return true;
    }

    @Required
    public void setIbController(ApiController ibController) {
        this.ibController = ibController;
    }

    @Required
    public void setRawDataProcessor(Processor<Set<DataPointCollection>> rawDataProcessor) {
        this.rawDataProcessor = rawDataProcessor;
    }
}
