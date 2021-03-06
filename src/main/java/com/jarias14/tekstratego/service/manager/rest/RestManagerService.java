package com.jarias14.tekstratego.service.manager.rest;

import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.common.models.MarketDataNotification;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/manager-service")
public interface RestManagerService {

    @POST
    @Path("/accounts/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ManagedAccount createAccount(ManagedAccount request);

    @GET
    @Path("/accounts/{account-id}/trades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ManagedAccount retrieveAccountTradeHistory(@PathParam("account-id") String accountId);

    @HEAD
    @Path("/accounts/{account-id}/trades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ManagedAccount retrieveAccountTradeStatus(@PathParam("account-id") String accountId);

    @POST
    @Path("/data/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean processMarketData(MarketDataNotification marketDataNotification);

    
}
