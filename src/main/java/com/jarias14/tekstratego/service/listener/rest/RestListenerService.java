package com.jarias14.tekstratego.service.listener.rest;

import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/listener-service")
public interface RestListenerService {
    
    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @POST
    @Path("/subscriptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RawDataResponse subscribe(RawDataRequest request);
    
    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @DELETE
    @Path("/subscriptions/{subscription-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RawDataRequest unsubscribe(@PathParam("subscription-id") String subscriptionId);

}
