    package com.jarias14.tekstratego.service.thinker.rest;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

@Service("restThinkerService")
public interface RestThinkerService {
	

    /**
     * Creates an instance of a hypothesis.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public HypothesisResource createHypothesis() throws Exception;
    
    /**
     * Returns the resource for the requested hypothesis.
     * @param hypothesisId is the identifier for the requested hypothesis
     * @return the hypothesis
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getHypothesis(@PathParam("hypothesis-id") String hypothesisId);
    
    /**
     * Creates a strategy for the given hypothesis.
     * @param hypothesisId - the identifier for the hypothesis
     * @return
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource addStrategy(@PathParam("hypothesis-id") String hypothesisId, @QueryParam("type") String strategyType, @RequestBody String body)  throws JsonParseException, JsonMappingException, IOException;

    /**
     * Provides a list of strategies in the hypothesis.
     * @param hypothesisId identifier for the hypothesis
     * @return the list of strategies part of the hypothesis
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Set<BaseResource> getStrategies(@PathParam("hypothesis-id") String hypothesisId);
    
    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @GET
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource getStrategy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId);

    /**
     * Provides the requested strategy.
     * @param type - is the indicator to be created.
     * @return the created indicator with a selfLink the client can use.
     */
    @POST
    @Path("/hypothesis/{hypothesis-id}/strategies/{strategy-id}/studies")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public BaseResource addStudy(@PathParam("hypothesis-id") String hypothesisId, @PathParam("strategy-id") String strategyId);
}
