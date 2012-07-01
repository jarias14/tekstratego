package com.jarias14.tekstratego.service.pricer.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

//@Service("restPricerService")
public interface RestPricerService {
    
    /**
     * Creates an indicator given the stock and the body.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/indicators")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource createIndicator(@RequestBody IndicatorResource indicator);
    
    /**
     * Creates an indicator given the stock and the body.
     * @return the list of prices for the request.
     */
    @GET
    @Path("/indicators/{indicator-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getIndicator(@PathParam("indicator-id") String indicatorId);
    
    /**
     * Gets the list of prices for the given stock and indicator.
     * @return the list of prices for the request.
     */
    @GET
    @Path("/indicators/{indicator-id}/prices/{stock-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getValues(@PathParam("indicator-id") String indicatorId, @PathParam("stock-id") String stockId,
            @QueryParam("sizeOfBars") String sizeOfBars, @QueryParam("startDate") String startDate, @QueryParam("numberOfBars") String numberOfBars);
    
}
