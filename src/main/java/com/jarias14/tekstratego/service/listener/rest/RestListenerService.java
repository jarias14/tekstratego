package com.jarias14.tekstratego.service.listener.rest;

import com.jarias14.tekstratego.service.listener.models.ListenerRequest;
import com.jarias14.tekstratego.service.listener.models.ListenerResponse;

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
    public ListenerResponse subscribe(ListenerRequest request);
    
    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @DELETE
    @Path("/subscriptions/{stock-symbol}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ListenerResponse unsubscribe(@PathParam("stock-symbol") String stockSymbol);

}
