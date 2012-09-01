package com.jarias14.tekstratego.service.pricer.rest.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import org.junit.Test;

import com.jarias14.tekstratego.common.resource.HypothesisResource;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.common.resource.PortfolioResource;
import com.jarias14.tekstratego.common.resource.StrategyResource;
import com.jarias14.tekstratego.common.resource.StudyResource;

public class QuickTest {

    @Test
    public void test() {
        
        RestTemplate restTemplate = getRestTemplate();
        
        IndicatorResource indicator = createIndicator(restTemplate);
        
        HypothesisResource hypothesis = createHypothesis(restTemplate);
        
        StrategyResource buyStrategy = createBuyStrategy(restTemplate, hypothesis.getId());
        
        StrategyResource sellStrategy = createSellStrategy(restTemplate, hypothesis.getId());
        
        StudyResource buyStudy = createStudy("lt", "10.00", "0", restTemplate, indicator.getId(), hypothesis.getId(), buyStrategy.getId());
        
        StudyResource sellStudy = createStudy("gt", "90.00", "0", restTemplate, indicator.getId(), hypothesis.getId(), sellStrategy.getId());
        
        PortfolioResource portfolio = createPortfolio(restTemplate, hypothesis.getId());
        
        System.out.println(portfolio.getId());
    }
    
    private PortfolioResource createPortfolio(RestTemplate restTemplate, String hypothesisId) {
        String postUrl = "http://localhost:8080/tekstratego/manager-service/portfolio/";
        
        PortfolioResource request = new PortfolioResource();
        request.setAvailableCash("10000.00");
        request.setInitialCash("10000.00");
        request.setStartDate("2010-01-01T00:00:00");
        request.setEndDate("2010-12-31T00:00:00");
        request.setHypothesisId(hypothesisId);
        
        PortfolioResource portfolio = restTemplate.postForObject(postUrl, request, PortfolioResource.class);
        
        return portfolio;
    }

    private StudyResource createStudy(String type, String value, String barUnderTest, RestTemplate restTemplate, String indicatorId, String hypothesisId, String strategyId) {

        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis/"+hypothesisId+"/strategies/"+strategyId+"/studies?parent-study-id=root";
        
        StudyResource request = new StudyResource();
        request.setType(type);
        request.setStudyValue(value);
        request.setBarUnderTest(barUnderTest);
        request.setIndicatorId(indicatorId);
        
        StudyResource study = restTemplate.postForObject(postUrl, request, StudyResource.class);
        
        return study;
    }

    private StrategyResource createBuyStrategy(RestTemplate restTemplate, String hypothesisId) {
        
        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis/".concat(hypothesisId).concat("/strategies");
        
        StrategyResource request = new StrategyResource();
        request.setType("ENTRY");
        request.setDescription("buy sma < price AND STO < 10");
        request.setMaxStrategyInvestment("100000");
        request.setMaxSecurityInvestment("5000");
        request.setMaxPerTradeInvestment("5000");
        request.setIsStrategyExclusive("true");
        request.setStocks(new ArrayList<String>());
        request.getStocks().add("MSFT");
        request.getStocks().add("GOOG");
        
        StrategyResource strategy = restTemplate.postForObject(postUrl, request, StrategyResource.class);
        
        return strategy;
    }
    
    private StrategyResource createSellStrategy(RestTemplate restTemplate, String hypothesisId) {
        
        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis/".concat(hypothesisId).concat("/strategies");
        
        StrategyResource request = new StrategyResource();
        request.setType("EXIT");
        request.setDescription("sell sma > price AND STO > 80");
        request.setMaxStrategyInvestment("100000");
        request.setMaxSecurityInvestment("5000");
        request.setMaxPerTradeInvestment("5000");
        request.setIsStrategyExclusive("true");
        request.setStocks(new ArrayList<String>());
        request.getStocks().add("MSFT");
        request.getStocks().add("GOOG");
        
        StrategyResource strategy = restTemplate.postForObject(postUrl, request, StrategyResource.class);
        
        return strategy;
    }

    private HypothesisResource createHypothesis(RestTemplate restTemplate) {
        
        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis";
        
        HypothesisResource request = new HypothesisResource();
        
        HypothesisResource hypothesis = restTemplate.postForObject(postUrl, request, HypothesisResource.class);
        
        return hypothesis;
    }

    private IndicatorResource createIndicator(RestTemplate restTemplate) {
        
        String postUrl = "http://localhost:8080/tekstratego/pricer-service/indicators";
        
        /*
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "4");
        request.setPriceOfBars("OPEN");
        request.setType("simple_moving_average");
        request.setSizeOfBars("ONE_DAY");
        */
        /*
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "14");
        request.getDetails().put("smoothing", "3");
        request.setPriceOfBars("OPEN");
        request.setType("stochastic_oscillator_d");
        request.setSizeOfBars("ONE_DAY");
        */
        
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "14");
        request.getDetails().put("smoothing", "3");
        request.setPriceOfBars("OPEN");
        request.setType("stochastic_oscillator_d");
        request.setSizeOfBars("ONE_DAY");
        
        IndicatorResource indicator = restTemplate.postForObject(postUrl, request, IndicatorResource.class);
        
        return indicator;
    }
    
    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}


