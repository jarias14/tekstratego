package com.jarias14.tekstratego.service.trader.dao.impl;

import com.ib.controller.ApiController;
import com.ib.controller.NewOrderState;
import com.ib.controller.OrderStatus;

/**
 * Created by jarias14 on 4/14/15.
 */
public class InteractiveBrokersTradeExecutionCallbackHanlder implements ApiController.IOrderHandler {

    private NewOrderState newOrderState;
    private OrderStatus orderStatus;
    private int filled;
    private int remaining;
    private double lastFillPrice;
    private double avgFillPrice;

    @Override
    public void orderState(NewOrderState orderState) {
        this.newOrderState = orderState;
    }

    @Override
    public void orderStatus(OrderStatus status, int filled, int remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
        this.orderStatus = status;
        this.filled = filled;
        this.remaining = remaining;
        this.avgFillPrice = avgFillPrice;
        this.lastFillPrice = lastFillPrice;
    }

    @Override
    public void handle(int errorCode, String errorMsg) {
        System.out.println(errorCode + " " + errorMsg);
    }

    public NewOrderState getNewOrderState() {
        return newOrderState;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getAvgFillPrice() {
        return avgFillPrice;
    }

    public double getLastFillPrice() {
        return lastFillPrice;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getFilled() {
        return filled;
    }
}
