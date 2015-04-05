package com.jarias14.tekstratego.service.manager.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/manager-service")
public interface RestManagerService {

    /**
     * Gets trades from the portfolio.
     * @return TransactionCollectionResource - a list of trade alerts
     */
    @GET
    @Path("/portfolio/{portfolio-id}/trades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getTrades(@PathParam("portfolio-id") String portfolioId, @QueryParam("sort-by") String sortBy);
    
    
}
