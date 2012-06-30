package com.jarias14.tekstratego.service.manager.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

@Service("restManagerService")
public interface RestManagerService {
    
    /**
     * Creates a portfolio for the client.
     * @return the list of prices for the request.
     */
    @POST
    @Path("/portfolio")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource createPortfolio(@QueryParam("suggested-id") String suggestedId);

    /**
     * Returns an existing portfolio object.
     * @return a portfolio object.
     */
    @GET
    @Path("/portfolio/{portfolio-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getPortfolio(@PathParam("portfolio-id") String portfolioId);
    
    /**
     * Adds a transaction to the portfolio.
     * @return a transaction object
     */
    @POST
    @Path("/portfolio/{portfolio-id}/transactions")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource addTransaction(@PathParam("portfolio-id") String portfolioId);
    
    /**
     * Gets transactions from the portfolio.
     * @return a transaction object
     */
    @GET
    @Path("/portfolio/{portfolio-id}/transactions")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getTransactions(@PathParam("portfolio-id") String portfolioId);
}
