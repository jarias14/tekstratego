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
        
        StrategyResource buyStrategy = createStrategy(restTemplate, hypothesis.getId());
        
        StudyResource buyStudy = createStudy(restTemplate, indicator.getId(), hypothesis.getId(), buyStrategy.getId());
        
        PortfolioResource portfolio = createPortfolio(restTemplate, hypothesis.getId());
        
        System.out.println(portfolio.getId());
    }
    
    private PortfolioResource createPortfolio(RestTemplate restTemplate, String hypothesisId) {
        String postUrl = "http://localhost:8080/tekstratego/manager-service/portfolio/";
        
        PortfolioResource request = new PortfolioResource();
        request.setAvailableCash("10000.00");
        request.setInitialCash("10000.00");
        request.setStartDate("2010-07-01T00:00:00");
        request.setEndDate("2011-01-01T00:00:00");
        request.setHypothesisId(hypothesisId);
        
        PortfolioResource portfolio = restTemplate.postForObject(postUrl, request, PortfolioResource.class);
        
        return portfolio;
    }

    private StudyResource createStudy(RestTemplate restTemplate, String indicatorId, String hypothesisId, String strategyId) {

        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis/"+hypothesisId+"/strategies/"+strategyId+"/studies?parent-study-id=root";
        
        StudyResource request = new StudyResource();
        request.setType("lt");
        request.setStudyValue("25.01");
        request.setBarUnderTest("0");
        request.setIndicatorId(indicatorId);
        
        StudyResource study = restTemplate.postForObject(postUrl, request, StudyResource.class);
        
        return study;
    }

    private StrategyResource createStrategy(RestTemplate restTemplate, String hypothesisId) {
        
        String postUrl = "http://localhost:8080/tekstratego/thinker-service/hypothesis/".concat(hypothesisId).concat("/strategies");
        
        StrategyResource request = new StrategyResource();
        request.setType("ENTRY");
        request.setDescription("buy sma > 80");
        request.setMaxStrategyInvestment("5000");
        request.setMaxSecurityInvestment("1000");
        request.setMaxPerTradeInvestment("500");
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
        
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "4");
        request.setPriceOfBars("OPEN");
        request.setType("simple_moving_average");
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


