package com.jarias14.tekstratego.service.thinker.rest.impl;

import java.io.IOException;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import com.jarias14.tekstratego.service.thinker.rest.RestThinkerService;
import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;

public class RestThinkerServiceImpl implements RestThinkerService{

    public RestThinkerServiceImpl() {
        
    }
    
    @Override
    public HypothesisResource createHypothesis() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getHypothesis(String hypothesisId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource addStrategy(String hypothesisId, String strategyType,
            String body) throws JsonParseException, JsonMappingException,
            IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<BaseResource> getStrategies(String hypothesisId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource getStrategy(String hypothesisId, String strategyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResource addStudy(String hypothesisId, String strategyId) {
        // TODO Auto-generated method stub
        return null;
    }

}
