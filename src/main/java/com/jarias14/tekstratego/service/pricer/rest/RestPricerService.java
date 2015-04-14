package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.service.manager.models.MarketDataNotification;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/pricer-service")
public interface RestPricerService {

    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @POST
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DataPointDescription createIndicator(DataPointDescription request);


    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<DataPointDescription> addDataPoint(MarketDataNotification request);

}