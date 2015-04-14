package com.jarias14.tekstratego.service.trader.dao.impl;

import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.controller.*;
import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;

import java.util.Random;

/**
 * Created by jarias14 on 4/14/15.
 */
public class InteractiveBrokersTradeExecutionDao implements DataAccessObject<TradeRequest, Position> {

    private ApiController ibController;

    @Override
    public Position request(TradeRequest tradeRequest) {

        InteractiveBrokersTradeExecutionCallbackHanlder hanlder = new InteractiveBrokersTradeExecutionCallbackHanlder();


        int id = new Random().nextInt((0 - Integer.MAX_VALUE) + 1) + 10;

        NewContract contract = new NewContract();
        contract.currency("USD");
        contract.secType(Types.SecType.STK);
        contract.symbol(tradeRequest.getStock().getSymbol());
        contract.exchange(tradeRequest.getStock().getExchange().name());

        NewOrder order = new NewOrder();
        order.action(tradeRequest.getQuantity() > 0 ? Types.Action.BUY : Types.Action.SELL);
        order.totalQuantity(tradeRequest.getQuantity());
        order.orderType(OrderType.MKT);

        ibController.placeOrModifyOrder(contract, order, hanlder);



        return null;
    }

    public void setIbController(ApiController ibController) {
        this.ibController = ibController;
    }
}
