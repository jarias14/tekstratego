package com.jarias14.tekstratego.service.thinker.rest;

import com.jarias14.tekstratego.service.thinker.rest.model.StrategyRequestResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/thinker-service")
public interface RestThinkerService {


    /**
     * Creates an hypothesis object.
     * @return HypothesisResource - the created hypothesis object with a selfLink the client can use for reference.
     */
    @POST
    @Path("/strategy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createNode(StrategyRequestResource hypothesis);


    /**
     * Creates an hypothesis object.
     * @return HypothesisResource - the created hypothesis object with a selfLink the client can use for reference.
     */
    @POST
    @Path("/strategy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object addDecisionTreeNode(StrategyRequestResource hypothesis);

}
