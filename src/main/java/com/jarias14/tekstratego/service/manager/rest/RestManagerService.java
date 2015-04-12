package com.jarias14.tekstratego.service.manager.rest;

import com.jarias14.tekstratego.service.manager.rest.models.AccountRequestResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/manager-service")
public interface RestManagerService {

    /**
     * Creates an account.
     * @return AccountRequestResource - the requested account.
     */
    @POST
    @Path("/accounts/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountRequestResource createAccount(AccountRequestResource request);

    @GET
    @Path("/accounts/{account-id}/trades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountRequestResource retrieveAccountTrades(@PathParam("account-id") String accountId);

    
}
