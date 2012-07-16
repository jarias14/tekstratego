package com.jarias14.tekstratego.service.trader.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jarias14.tekstratego.common.resource.TransactionResource;

@Path("trader-service")
public interface RestTraderService {

    /**
     * Performs a trade and returns the executed price after transaction costs.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/trade/{symbol}/shares/{shares}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TransactionResource createIndicator(@PathParam("symbol") String symbol, @PathParam("shares") String shares, @QueryParam("back-testing") String isBackTesting, @QueryParam("barTime") String barTime, @QueryParam("barSize") String barSize);

}
