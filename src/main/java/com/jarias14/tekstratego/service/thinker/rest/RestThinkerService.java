package com.jarias14.tekstratego.service.thinker.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jarias14.tekstratego.common.resource.HypothesisResource;
import com.jarias14.tekstratego.common.resource.StrategyResource;
import com.jarias14.tekstratego.common.resource.StudyResource;

@Path("/thinker-service")
public interface RestThinkerService {

    /**
     * Creates an instance of a hypothesis.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public HypothesisResource createHypothesis(HypothesisResource hypothesis);
    
    /**
     * Returns the resource for the requested hypothesis.
     * @param hypothesisId is the identifier for the requested hypothesis
     * @return the hypothesis
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public HypothesisResource getHypothesis(@PathParam("hypothesis-id") String hypothesisId);
    
    /**
     * Makes the hypothesis do all the necessary calculations. This will place the hypothesis
     * in PROCESSING status until it completes all the calculations.
     * @param hypothesisId is the identifier for the requested hypothesis
     * @return the hypothesis
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/process")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public HypothesisResource runHypothesis(@PathParam("hypothesis-id") String hypothesisId);
    
    /**
     * Creates a strategy for the given hypothesis.
     * @param hypothesisId - the identifier for the hypothesis
     * @return
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StrategyResource addStrategy(StrategyResource strategy, @PathParam("hypothesis-id") String hypothesisId);

    /**
     * Provides a list of strategies in the hypothesis.
     * @param hypothesisId identifier for the hypothesis
     * @return the list of strategies part of the hypothesis
     
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<BaseResource> getStrategies(@PathParam("hypothesis-id") String hypothesisId);
    */
    
    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StrategyResource getStrategy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId);

    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}/studies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StudyResource addStudy(StudyResource study, @PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId, @QueryParam("parent-study-id") String studyId);

    /**
     * Provides the requested study.
     * @param hypothesisId - is the hypothesis id where the study lives in
     * @param strategyId - is the strategy id that owns the study
     * @param studyId - is the study id to look for
     * @return the requested study
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}/studies/{study-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StudyResource getStudy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId, @PathParam("study-id") String studyId);
}
