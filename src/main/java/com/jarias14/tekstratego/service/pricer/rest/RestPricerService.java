package com.jarias14.tekstratego.service.pricer.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.common.resource.IndicatorValuesResource;

@Path("/pricer-service")
public interface RestPricerService {
    
    /**
     * Creates an indicator given the stock and the body.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IndicatorResource createIndicator(IndicatorResource indicator);
    
    /**
     * Creates an indicator given the stock and the body.
     * @return the list of prices for the request.
     */
    @GET
    @Path("/indicators/{indicator-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IndicatorResource getIndicator(@PathParam("indicator-id") String indicatorId);
    
    /**
     * Gets the list of prices for the given stock and indicator.
     * @return the list of prices for the request.
     */
    @GET
    @Path("/indicators/{indicator-id}/prices/{stock-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IndicatorValuesResource getValues(@PathParam("indicator-id") String indicatorId, @PathParam("stock-id") String stockId,
            @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate);
    
}
