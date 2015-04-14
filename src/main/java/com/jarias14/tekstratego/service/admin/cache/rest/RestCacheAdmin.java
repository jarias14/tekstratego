package com.jarias14.tekstratego.service.admin.cache.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by jarias14 on 4/13/2015.
 */
@Path("/admin")
public interface RestCacheAdmin {

    @GET
    @Path("/cache")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<CacheResource> getAllCacheElements();



}
