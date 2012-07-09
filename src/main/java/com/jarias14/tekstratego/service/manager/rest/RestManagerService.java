package com.jarias14.tekstratego.service.manager.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jarias14.tekstratego.common.resource.AlertCollectionResource;
import com.jarias14.tekstratego.common.resource.PortfolioResource;
import com.jarias14.tekstratego.common.resource.TransactionCollectionResource;

@Path("/manager-service")
public interface RestManagerService {
    
    /**
     * Creates a portfolio for the client.
     * @return a portfolio instance.
     */
    @POST
    @Path("/portfolio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PortfolioResource createPortfolio(PortfolioResource portfolio);
    
    /**
     * Examines portfolio with the given alerts
     * @return a portfolio instance.
     */
    @POST
    @Path("/portfolio/{portfolio-id}/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PortfolioResource submitPortfolio(@PathParam("portfolio-id") String portfolioId);

    /**
     * Returns an existing portfolio object.
     * @return a portfolio object.
     */
    @GET
    @Path("/portfolio/{portfolio-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PortfolioResource getPortfolio(@PathParam("portfolio-id") String portfolioId);
    
    /**
     * Adds a trade alert for the portfolio to consider.
     * @return list of trade alerts
     */
    @POST
    @Path("/portfolio/{portfolio-id}/alerts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AlertCollectionResource addAlerts(@PathParam("portfolio-id") String portfolioId, AlertCollectionResource alerts);
    
    /**
     * Gets trade alerts from the portfolio.
     * @return a list of trade alerts
     */
    @GET
    @Path("/portfolio/{portfolio-id}/alerts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AlertCollectionResource getAlerts(@PathParam("portfolio-id") String portfolioId, @QueryParam("sort-by") String sortBy);
    
}
