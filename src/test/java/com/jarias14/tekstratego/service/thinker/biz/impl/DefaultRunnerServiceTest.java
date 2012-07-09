package com.jarias14.tekstratego.service.thinker.biz.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.model.TradeTypeEnum;
import com.jarias14.tekstratego.common.resource.AlertCollectionResource;
import com.jarias14.tekstratego.common.resource.AlertResource;
import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.common.resource.PortfolioResource;
import com.jarias14.tekstratego.common.utilities.ConstantsUtility;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.thinker.dao.impl.DefaultThinkerDAO;
import com.jarias14.tekstratego.service.thinker.biz.ThinkerService;
import com.jarias14.tekstratego.service.thinker.dao.ThinkerDAO;
import com.jarias14.tekstratego.service.thinker.model.Hypothesis;
import com.jarias14.tekstratego.service.thinker.model.HypothesisStatusEnum;
import com.jarias14.tekstratego.service.thinker.model.Position;
import com.jarias14.tekstratego.service.thinker.model.Strategy;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.model.TradeAlert;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;
import com.jarias14.tekstratego.service.thinker.model.study.GreaterThanStudy;
import com.jarias14.tekstratego.service.thinker.model.study.LessThanStudy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class DefaultRunnerServiceTest {

    @Resource(name="thinkerService")
    private ThinkerService thinkerService;
    
    private MembaseConnector connector;
    
    private Hypothesis hypothesis;
    
    private String indicatorId;
    
    @Before
    public void setUp() {

        connector = EasyMock.createMock(MembaseConnector.class);
        ((DefaultThinkerDAO)(((DefaultThinkerService)thinkerService).getThinkerDAO())).setMemory(connector);
        
        indicatorId = createTestIndicator();
        
        hypothesis = new Hypothesis();
        hypothesis.setPortfolioId(createTestPortfolio());
        hypothesis.setStrategies(new HashMap<String,Strategy>());
        hypothesis.setStocks(new ArrayList<Stock>());
        hypothesis.setStartDate(Calendar.getInstance());
        hypothesis.getStartDate().set(2010, 02, 01, 0, 0, 0);
        hypothesis.setEndDate(Calendar.getInstance());
        hypothesis.getEndDate().set(2010, 9, 01, 23, 59, 59);
        hypothesis.setStatus(HypothesisStatusEnum.PROCESSING);
        
        hypothesis.getStocks().add(new Stock("","ED"));
        
        Strategy strategy = new Strategy();
        hypothesis.getStrategies().put("1234", strategy);
        strategy.setMaxSecurityInvestment(new BigDecimal("10000.00"));
        strategy.setMaxStrategyInvestment(new BigDecimal("2222.33"));
        strategy.setType(TradeTypeEnum.ENTRY);
        strategy.setStudy(new AndStudy());
        strategy.setId("1234");
        
        AndStudy rootStudy = (AndStudy) strategy.getStudy();
        rootStudy.setId("root");
        rootStudy.setStudies(new ArrayList<Study>());
        
        GreaterThanStudy smaStudy = new GreaterThanStudy();
        smaStudy.setIndicatorId(indicatorId);
        smaStudy.setNumberOfBarsBeforeCurrent(0);
        smaStudy.setParentId("root");
        smaStudy.setStudyValue(48.36);

        rootStudy.getStudies().add(smaStudy);
        
        LessThanStudy smaStudy2 = new LessThanStudy();
        smaStudy2.setIndicatorId(indicatorId);
        smaStudy2.setNumberOfBarsBeforeCurrent(0);
        smaStudy2.setParentId("root");
        smaStudy2.setStudyValue(48.38);
        
        rootStudy.getStudies().add(smaStudy2);
        
    }
    
    @Test
    public void testGetAlerts() throws ParseException {
        
        SimpleDateFormat fmtr = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        EasyMock.expect(connector.read("123")).andReturn(hypothesis).times(2);
        EasyMock.replay(connector);
        
        List<TradeAlert> alerts = null;
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(fmtr.parse("2010-09-22T04:00:00").getTime());
        alerts = thinkerService.getAlerts("123", cal, new ArrayList<Position>());
        Assert.assertEquals(1, alerts.size());
        
        cal.setTimeInMillis(fmtr.parse("2010-09-23T04:00:00").getTime());
        alerts = thinkerService.getAlerts("123", cal, new ArrayList<Position>());
        Assert.assertEquals(0, alerts.size());
        
        EasyMock.verify(connector);
    }
    
    private String createTestIndicator() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        String postUrl = "http://localhost:8080/tekstratego/pricer-service/indicators";
        
        IndicatorResource request = new IndicatorResource();
        request.setDetails(new HashMap<String, String>());
        request.getDetails().put("period", "3");
        request.setPriceOfBars("OPEN");
        request.setType("simple_moving_average");
        request.setSizeOfBars("ONE_DAY");
        
        IndicatorResource postResult = restTemplate.postForObject(postUrl, request, IndicatorResource.class);
        
        return postResult.getId();
    }

    
    private String createTestPortfolio() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        
        String postUrl = "http://localhost:8080/tekstratego/manager-service/portfolio";
        
        PortfolioResource postResult = restTemplate.postForObject(postUrl, new PortfolioResource(), PortfolioResource.class);
        
        return postResult.getId();
    }

}
