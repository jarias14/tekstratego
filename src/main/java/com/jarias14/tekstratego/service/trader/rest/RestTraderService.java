package com.jarias14.tekstratego.service.trader.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/trader-service")
public interface RestTraderService {

    /**
     * Performs a trade and returns the executed price after transaction costs.
     * @return TransactionResource - the list of prices for the request.
     */
    @POST
    @Path("/trade/{symbol}/type/{type}/amount/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createTrade(@PathParam("symbol") String symbol, @PathParam("type") String type, @PathParam("amount") String amount, @QueryParam("is-back-testing") String isBackTesting, @QueryParam("bar-time") String barTime, @QueryParam("bar-size") String barSize);

}
