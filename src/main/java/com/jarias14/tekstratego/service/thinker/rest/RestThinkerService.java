package com.jarias14.tekstratego.service.thinker.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestBody;

import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.StrategyResource;

public interface RestThinkerService {

    /**
     * Creates an instance of a hypothesis.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public HypothesisResource createHypothesis(HypothesisResource hypothesis);
    
    /**
     * Returns the resource for the requested hypothesis.
     * @param hypothesisId is the identifier for the requested hypothesis
     * @return the hypothesis
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public HypothesisResource getHypothesis(@PathParam("hypothesis-id") String hypothesisId);
    
    /**
     * Creates a strategy for the given hypothesis.
     * @param hypothesisId - the identifier for the hypothesis
     * @return
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public StrategyResource addStrategy(@PathParam("hypothesis-id") String hypothesisId, @RequestBody StrategyResource strategy);

    /**
     * Provides a list of strategies in the hypothesis.
     * @param hypothesisId identifier for the hypothesis
     * @return the list of strategies part of the hypothesis
     
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Set<BaseResource> getStrategies(@PathParam("hypothesis-id") String hypothesisId);
    */
    
    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public StrategyResource getStrategy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId);

    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}/studies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public StudyResource addStudy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId, @QueryParam("parent-study-id") String studyId, @RequestBody StudyResource study);
}
