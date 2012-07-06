package com.jarias14.tekstratego.service.trader.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jarias14.tekstratego.common.resource.IndicatorResource;

@Path("trader-service")
public interface RestTraderService {

    /**
     * Creates an indicator given the stock and the body.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IndicatorResource createIndicator(IndicatorResource indicator);

}
