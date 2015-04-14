package com.jarias14.tekstratego.service.trader.rest;

import com.jarias14.tekstratego.common.models.TradeRequest;
import com.jarias14.tekstratego.common.models.TradeResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/trader-service")
public interface RestTraderService {

    @POST
    @Path("/trade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TradeResponse executeTrade(TradeRequest tradeRequest);
}
