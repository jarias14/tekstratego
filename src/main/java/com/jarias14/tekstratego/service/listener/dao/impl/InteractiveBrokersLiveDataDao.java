package com.jarias14.tekstratego.service.listener.dao.impl;

import com.ib.controller.ApiController;
import com.ib.controller.NewContract;
import com.ib.controller.Types;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointSize;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class InteractiveBrokersLiveDataDao implements DataAccessObject<RawDataRequest, Boolean>{

    private Processor<Set<DataPointCollection>> ibHistoricBarProcessor;
    private ApiController ibController;

    @Override
    public Boolean request(RawDataRequest ibRequest) {

        InteractiveBrokersLiveDataCallbackHandler ibCallbackHandler = new InteractiveBrokersLiveDataCallbackHandler();
        ibCallbackHandler.setStock(ibRequest.getStock());
        ibCallbackHandler.setDataPointSize(ibRequest.getDataPointSize());
        ibCallbackHandler.setHistoricDataProcessor(ibHistoricBarProcessor);


        NewContract ibContract = new NewContract();
        ibContract.secType(Types.SecType.STK);
        ibContract.currency("USD");
        ibContract.exchange(ibRequest.getStock().getExchange().name());
        ibContract.symbol(ibRequest.getStock().getSymbol());

        ibController.reqRealTimeBars(ibContract, Types.WhatToShow.TRADES, false, ibCallbackHandler);

        return true;
    }

    private String constructIbBarSizeName(DataPointSize dataPointSize) {

        String ibBarSizeName = "_" + dataPointSize.getQuantity() + "_";

        if (TimeUnit.SECONDS.equals(dataPointSize.getUnit())) {
            ibBarSizeName = ibBarSizeName.concat("secs");
        } else if (TimeUnit.MINUTES.equals(dataPointSize.getUnit())) {
            ibBarSizeName = ibBarSizeName.concat("min");
        } else if (TimeUnit.HOURS.equals(dataPointSize.getUnit())) {
            ibBarSizeName = ibBarSizeName.concat("hour");
        } else if (TimeUnit.DAYS.equals(dataPointSize.getUnit())) {
            ibBarSizeName = ibBarSizeName.concat("day");
        }

        return ibBarSizeName;
    }

    @Required
    public void setIbController(ApiController ibController) {
        this.ibController = ibController;
    }

    @Required
    public void setIbHistoricBarProcessor(Processor<Set<DataPointCollection>> ibHistoricBarProcessor) {
        this.ibHistoricBarProcessor = ibHistoricBarProcessor;
    }
}
