package com.jarias14.tekstratego.service.trader.dao.impl;

import com.ib.controller.*;
import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/14/15.
 */
public class InteractiveBrokersTradeExecutionDao implements DataAccessObject<TradeRequest, Position> {

    private ApiController ibController;

    @Override
    public Position request(TradeRequest tradeRequest) {

        InteractiveBrokersTradeExecutionCallbackHanlder hanlder = new InteractiveBrokersTradeExecutionCallbackHanlder();

        NewContract contract = new NewContract();
        contract.currency("USD");
        contract.secType(Types.SecType.STK);
        contract.symbol(tradeRequest.getStock().getSymbol());
        contract.exchange(tradeRequest.getStock().getExchange().name());
        contract.primaryExch("NASDAQ");

        NewOrder order = new NewOrder();
        order.action(tradeRequest.getQuantity() > 0 ? Types.Action.BUY : Types.Action.SELL);
        order.totalQuantity(tradeRequest.getQuantity());
        order.orderType(OrderType.MKT);

        ibController.placeOrModifyOrder(contract, order, hanlder);



        return null;
    }

    @Required
    public void setIbController(ApiController ibController) {
        this.ibController = ibController;
    }
}
