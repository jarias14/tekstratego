package com.jarias14.tekstratego.service.thinker.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.AlertCollectionResource;
import com.jarias14.tekstratego.common.resource.IndicatorValuesResource;
import com.jarias14.tekstratego.common.utilities.ConstantsUtility;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.thinker.dao.ThinkerDAO;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.TradeAlert;

public class DefaultThinkerDAO implements ThinkerDAO {
    
    private MembaseConnector memory;
    private int timeToLiveInSeconds;
    
    @Override
    public void createHypothesis(Hypothesis hypothesis) {
        
        memory.save(hypothesis.getId(), hypothesis, timeToLiveInSeconds);
    }

    @Override
    public Hypothesis readHypothesis(String hypothesisId) {
        
        return ((Hypothesis) memory.read(hypothesisId));
    }
    
    @Override
    public SortedMap<Calendar, Double> getIndicatorValues(String indicatorId, Stock stock, Calendar startDate, Calendar endDate) {
        
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("indicator", indicatorId);
        replacements.put("symbol", stock.getSymbol());
        
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("startDate", (new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).format(startDate.getTime()));
        parameters.put("endDate", (new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).format(endDate.getTime()));
        
        String url = LinksUtility.getUrl(LinksUtility.PRICER_INDICATOR_VALUES, replacements, parameters);
        
        IndicatorValuesResource response = getRestTemplate().getForObject(url, IndicatorValuesResource.class);

        return response.getValues();
    }

    @Override
    public SortedMap<Calendar, Double> getIndicatorValues(String indicatorId, Stock stock, Calendar date, Integer numberOfBars) {
        
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("indicator", indicatorId);
        replacements.put("symbol", stock.getSymbol());
        
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("endDate", (new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT)).format(date.getTime()));
        parameters.put("numberOfBars", String.valueOf(numberOfBars));
        
        String url = LinksUtility.getUrl(LinksUtility.PRICER_INDICATOR_VALUES, replacements, parameters);
        
        IndicatorValuesResource response = getRestTemplate().getForObject(url, IndicatorValuesResource.class);

        return response.getValues();
    }

    @Override
    public void postTradeAlers(String portfolioId, SortedMap<Calendar, List<TradeAlert>> tradeAlerts) {
        
        AlertCollectionResource resource = new AlertCollectionResource(tradeAlerts);
        

        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("portfolio", portfolioId);
        
        Map<String,String> parameters = new HashMap<String,String>();
        
        String url = LinksUtility.getUrl(LinksUtility.MANAGER_ALERTS_LINK, replacements, parameters);
        
        getRestTemplate().postForObject(url, resource, AlertCollectionResource.class);
    }
    
    private RestTemplate getRestTemplate(){
        
        RestTemplate restTemplate = new RestTemplate();
        
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        return restTemplate;
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
}
