package com.jarias14.tekstratego.service.manager.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.jarias14.tekstratego.common.resource.AlertCollectionResource;
import com.jarias14.tekstratego.common.resource.AlertResource;
import com.jarias14.tekstratego.common.resource.PositionCollectionResource;
import com.jarias14.tekstratego.common.utilities.ConverterUtility;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.common.utilities.RdsConnector;
import com.jarias14.tekstratego.service.manager.dao.ManagerDAO;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.manager.model.Signal;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class DefaultManagerDAO implements ManagerDAO {

    private MembaseConnector memory;
    private RdsConnector rds;
    private int timeToLiveInSeconds;

    @Override
    public Portfolio readPortfolio(String portfolioId) {
        return (Portfolio) memory.read(portfolioId);
    }

    @Override
    public void writePortfolio(Portfolio portfolio) {
        memory.save(portfolio.getId(), portfolio, timeToLiveInSeconds);
    }

    public MembaseConnector getMemory() {
        return memory;
    }

    public void setMemory(MembaseConnector memory) {
        this.memory = memory;
    }

    public int getTimeToLiveInSeconds() {
        return timeToLiveInSeconds;
    }

    public void setTimeToLiveInSeconds(int timeToLiveInSeconds) {
        this.timeToLiveInSeconds = timeToLiveInSeconds;
    }

    @Override
    public SortedSet<Calendar> getMarketDates(Calendar startDate,
            Calendar endDate) {
        return rds.getMarketDates(startDate, endDate);
    }

    @Override
    public List<Alert> getAlerts(String hypothesisId, Calendar today, Map<String, Position> positions) {
        
        // create msg body (resource)
        PositionCollectionResource resource = new PositionCollectionResource(positions);

        // prepare rest call
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("hypothesis", hypothesisId);
        replacements.put("date", ConverterUtility.toString(today));
        
        Map<String,String> parameters = new HashMap<String,String>();
        
        String url = LinksUtility.getUrl(LinksUtility.THINKER_HYPOTHESIS_ALERTS, replacements, parameters);
        
        // make rest call
        AlertCollectionResource responseResource =
                getRestTemplate().postForObject(url, resource, AlertCollectionResource.class);
        
        // convert response to model object
        SortedMap<Calendar, List<Alert>> responseModel = responseResource.toModel();
        
        // there has to only be one object in the map, so we return the first List<Alert>
        return responseModel.get(responseModel.firstKey());
    }

    @Override
    public List<Position> transact(List<Signal> signals, Calendar date) {
        
        List<Position> positions = new ArrayList<Position>();
        
        for (Signal signal : signals) {
            
            // create msg body (resource)
            AlertResource resource = signal.toResource();

            // prepare rest call
            Map<String,String> replacements = new HashMap<String,String>();
            replacements.put("symbol", signal.getStock().getSymbol());
            replacements.put("amount", signal.getAmountToTrade().toString());
            
            Map<String,String> parameters = new HashMap<String,String>();
            parameters.put("is-back-testing", "true");
            parameters.put("bar-size", "ONE_DAY");
            parameters.put("bar-time", ConverterUtility.toString(date));
            
            String url = LinksUtility.getUrl(LinksUtility.TRADER_TRANSACT, replacements, parameters);
            
            // make rest call
            PositionCollectionResource responseResource =
                    getRestTemplate().postForObject(url, resource, PositionCollectionResource.class);
            
            // convert response to model object
            positions.addAll(responseResource.toModel().values());

        }
        
        return positions;
    }
    
    private RestTemplate getRestTemplate(){
        
        RestTemplate restTemplate = new RestTemplate();
        
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        return restTemplate;
    }

    public RdsConnector getRds() {
        return rds;
    }

    public void setRds(RdsConnector rds) {
        this.rds = rds;
    }

}
