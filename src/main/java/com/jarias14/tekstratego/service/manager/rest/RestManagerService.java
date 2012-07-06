package com.jarias14.tekstratego.service.manager.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;

import com.jarias14.tekstratego.service.manager.rest.resource.AlertCollectionResource;
import com.jarias14.tekstratego.service.manager.rest.resource.PortfolioResource;
import com.jarias14.tekstratego.service.manager.rest.resource.TransactionCollectionResource;

@Service("restManagerService")
public interface RestManagerService {
    
    /**
     * Creates a portfolio for the client.
     * @return a portfolio instance.
     */
    @POST
    @Path("/portfolio")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public PortfolioResource createPortfolio(PortfolioResource portfolio);

    /**
     * Returns an existing portfolio object.
     * @return a portfolio object.
     */
    @GET
    @Path("/portfolio/{portfolio-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public PortfolioResource getPortfolio(@PathParam("portfolio-id") String portfolioId);
    
    /**
     * Adds a trade alert for the portfolio to consider.
     * @return list of trade alerts
     */
    @POST
    @Path("/portfolio/{portfolio-id}/alerts")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public AlertCollectionResource addAlerts(@PathParam("portfolio-id") String portfolioId, AlertCollectionResource alerts);

    /**
     * Gets trade alerts from the portfolio.
     * @return a list of trade alerts
     */
    @GET
    @Path("/portfolio/{portfolio-id}/alerts")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public AlertCollectionResource getAlerts(@PathParam("portfolio-id") String portfolioId);
    
    /**
     * Gets trade alerts from the portfolio.
     * @return a list of trade alerts
     */
    @GET
    @Path("/portfolio/{portfolio-id}/alerts")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public AlertCollectionResource getAlerts(@PathParam("portfolio-id") String portfolioId, @QueryParam("sort-by") String sortBy);
    
    
    /**
     * Trades an alert and moves it to the transactions resource.
     * @return a transaction object
     */
    @POST
    @Path("/portfolio/{portfolio-id}/alerts/{alert-id}/trade")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public TransactionCollectionResource addTransaction(@PathParam("portfolio-id") String portfolioId, @PathParam("alert-id") String alertId);
    
    /**
     * Gets transactions executed by the portfolio.
     * @return a transaction object
     */
    @GET
    @Path("/portfolio/{portfolio-id}/transactions")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public TransactionCollectionResource getTransactions(@PathParam("portfolio-id") String portfolioId, @QueryParam("sort-by") String sortBy);
}
