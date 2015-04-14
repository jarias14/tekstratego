package com.jarias14.tekstratego.service.trader.rest;

import com.jarias14.tekstratego.common.models.Position;
import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/14/15.
 */
public class RestTraderServiceImpl implements RestTraderService {

    private ApplicationService<TradeRequest, Position> tradeExecutionApplicationService;

    @Override
    public Position executeTrade(TradeRequest tradeRequest) {
        return tradeExecutionApplicationService.serviceRequest(tradeRequest);
    }

    @Required
    public void setTradeExecutionApplicationService(ApplicationService<TradeRequest, Position> tradeExecutionApplicationService) {
        this.tradeExecutionApplicationService = tradeExecutionApplicationService;
    }
}
