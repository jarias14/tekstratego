package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.MarketDataNotification;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/pricer-service")
public interface RestPricerService {

    @POST
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DataPointDescription createIndicator(DataPointDescription request);

    @GET
    @Path("/indicators/{indicator-id}/exchanges/{stock-exchange}/stocks/{stock-symbol}/times/{epoch-time}")
    @Produces(MediaType.APPLICATION_JSON)
    public DataPoint requestMarketData(
            @PathParam("indicator-id") String indicatorId,
            @PathParam("stock-exchange") String stockExchange,
            @PathParam("stock-symbol") String stockSymbol,
            @PathParam("epoch-time") String epochTime);

    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<DataPointDescription> addDataPoint(MarketDataNotification request);

}