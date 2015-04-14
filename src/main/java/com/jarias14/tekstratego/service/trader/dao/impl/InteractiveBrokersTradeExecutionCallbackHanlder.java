package com.jarias14.tekstratego.service.trader.dao.impl;

import com.ib.controller.ApiController;
import com.ib.controller.NewOrderState;
import com.ib.controller.OrderStatus;

/**
 * Created by jarias14 on 4/14/15.
 */
public class InteractiveBrokersTradeExecutionCallbackHanlder implements ApiController.IOrderHandler {

    @Override
    public void orderState(NewOrderState orderState) {

    }

    @Override
    public void orderStatus(OrderStatus status, int filled, int remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {

    }

    @Override
    public void handle(int errorCode, String errorMsg) {

    }
}
