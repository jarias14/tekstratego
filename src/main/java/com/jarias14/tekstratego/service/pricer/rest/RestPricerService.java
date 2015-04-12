package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.DataPointTimableDescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/pricer-service")
public interface RestPricerService {

    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @POST
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DataPointDescription createIndicator(DataPointDescription request);


    /**
     * Creates an indicator given the stock and the body.
     * @return IndicatorResource - the list of prices for the request.
     */
    @POST
    @Path("/data-points")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<DataPointDescription> addDataPoint(DataPointTimableDescription request);




}
