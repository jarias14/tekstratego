package com.jarias14.tekstratego.service.pricer.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

@Service("restPricerService")
public interface RestPricerService {
    
    /**
     * Gets the list of prices for the given stock and indicator.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/prices/{stock-id}/indicator/{indicator-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getPrices();
    
}
