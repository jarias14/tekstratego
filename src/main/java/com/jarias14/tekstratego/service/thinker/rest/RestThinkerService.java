package com.jarias14.tekstratego.service.thinker.rest;

import com.jarias14.tekstratego.service.manager.models.Trade;
import com.jarias14.tekstratego.service.thinker.rest.model.DecisionNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/thinker-service")
public interface RestThinkerService {


    /**
     * Creates an hypothesis object.
     * @return HypothesisResource - the created hypothesis object with a selfLink the client can use for reference.
     */
    @POST
    @Path("/strategies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DecisionNode createDecisionNode(DecisionNode hypothesis);

    /**
     * Requests strategy decision for a given time.
     * @return Boolean - whether the requested decision is true or not.
     */
    @GET
    @Path("/strategies/{strategy-id}/exchanges/{stock-exchange}/stocks/{stock-symbol}/times/{epoch-time}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Trade getDecision(
            @PathParam("strategy-id") String strategyId,
            @PathParam("stock-exchange") String stockExchange,
            @PathParam("stock-symbol") String stockSymbol,
            @PathParam("epoch-time") String epochTime);

}
