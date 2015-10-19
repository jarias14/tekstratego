package com.jarias14.tekstratego.service.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.service.manager.dao.resources.PricerServiceTradeRequest;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.models.ManagedAccountStrategy;
import com.jarias14.tekstratego.service.manager.models.Trade;
import com.jarias14.tekstratego.service.manager.models.TradeType;
import com.jarias14.tekstratego.service.thinker.rest.model.*;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 4/15/2015.
 */
public class IntegratedTest {

    private static final String THINKER_STRATEGY_DECISION_URL_BASE = "http://localhost:8082/thinker-service/strategies/{strategy-id}/exchanges/{stock-exchange}/stocks/{stock-symbol}/times/{epoch-time}/";

    private static final String THINKER_CREATE_STRATEGY = "http://localhost:8082/tekstratego/thinker-service/strategies";
    private static final String PRICER_CREATE_INDICATOR = "http://localhost:8082/tekstratego/pricer-service/indicators";
    private static final String MANAGER_CREATE_ACCOUNT = "http://localhost:8082/tekstratego/manager-service/accounts";


    @Test
    public void testOne() {

        String simpleMovingAverageIndicator = createSimpleMovingAverageIndicator(9);
        String closeIndicator = createCloseIndicator();
        String stochasticOscillatorK = createStochasticOscillator(DataPointIndicator.STOCHASTIC_OSCILLATOR_K, 14, 3, 3);
        String stochasticOscillatorD = createStochasticOscillator(DataPointIndicator.STOCHASTIC_OSCILLATOR_D, 14, 3, 3);

        String trueDecisionNodeLeafId = createLeafDecisionNode(true);
        String falseDecisionNodeLeafId = createLeafDecisionNode(false);


        // BUY STRATEGY

        String turningStochasticDecisionNode =
                createIndicatorDifferenceDecisionNode(Arrays.asList(stochasticOscillatorK, stochasticOscillatorD), trueDecisionNodeLeafId);

        String oversoldStochasticDecisionNode =
                createIndicatorRangeDecisionNode(Arrays.asList(stochasticOscillatorK), 0, 20, turningStochasticDecisionNode);

        String smaOverPriceIndicator =
                createSimpleMovingAverageOverClosePriceDecisionNode(Arrays.asList(simpleMovingAverageIndicator, closeIndicator), oversoldStochasticDecisionNode);


        // SELL STRATEGY

        String overboughtStochasticDecisionNode =
                createIndicatorRangeDecisionNode(Arrays.asList(stochasticOscillatorK), 80, 100, trueDecisionNodeLeafId);


        // MANAGER

        String managerId = createManager(smaOverPriceIndicator, overboughtStochasticDecisionNode);

    }

    private String createManager(String smaOverPriceIndicator, String overboughtStochasticDecisionNode) {


        ManagedAccountStrategy sellStrategy = new ManagedAccountStrategy();
        sellStrategy.setSharesToInvest(-100);
        sellStrategy.setStocks(Arrays.asList(new Stock("MCD", StockExchange.NYSE), new Stock("MSFT", StockExchange.NASDAQ), new Stock("GE", StockExchange.NYSE)));
        sellStrategy.setStrategyId(overboughtStochasticDecisionNode);
        sellStrategy.setTradeType(TradeType.SELL);

        ManagedAccountStrategy buyStrategy = new ManagedAccountStrategy();
        buyStrategy.setSharesToInvest(100);
        buyStrategy.setStocks(Arrays.asList(new Stock("MCD", StockExchange.NYSE), new Stock("MSFT", StockExchange.NASDAQ), new Stock("GE", StockExchange.NYSE)));
        buyStrategy.setStrategyId(smaOverPriceIndicator);
        buyStrategy.setTradeType(TradeType.BUY);

        ManagedAccount managedAccount = new ManagedAccount();
        managedAccount.setStrategies(new ArrayList<>());
        managedAccount.getStrategies().add(buyStrategy);
        managedAccount.getStrategies().add(sellStrategy);
        managedAccount.setSimulated(false);

        ManagedAccount response = getRestTemplate().postForObject(MANAGER_CREATE_ACCOUNT, managedAccount, ManagedAccount.class);

        return response.getId();

    }

    private String createIndicatorRangeDecisionNode(List<String> decisionNodeComparisonIds, int min, int max, String nextDecisionNodeId) {

        DecisionNodeComparison decisionNodeComparison = createDecisionNodeComparison(new Double(min), new Double(max), DecisionNodeComparisonType.NUMERIC_RANGE, nextDecisionNodeId);

        DecisionNode decisionNode = createDecisionNode(DecisionNodeType.CONTINUOUS);
        decisionNode.getDecisionNodeComparisons().add(decisionNodeComparison);
        decisionNode.setDecisionNodeIndicators(decisionNodeComparisonIds);

        DecisionNode response = getRestTemplate().postForObject(THINKER_CREATE_STRATEGY, decisionNode, DecisionNode.class);

        return response.getId();
    }

    private String createSimpleMovingAverageOverClosePriceDecisionNode(List<String> decisionNodeComparisonIds, String nextDecisionNodeId) {

        DecisionNodeComparison decisionNodeComparison = createDecisionNodeComparison(new Double(0), new Double(100), DecisionNodeComparisonType.NUMERIC_DIFFERENCE, nextDecisionNodeId);

        DecisionNode decisionNode = createDecisionNode(DecisionNodeType.CONTINUOUS);
        decisionNode.getDecisionNodeComparisons().add(decisionNodeComparison);
        decisionNode.setDecisionNodeIndicators(decisionNodeComparisonIds);

        DecisionNode response = getRestTemplate().postForObject(THINKER_CREATE_STRATEGY, decisionNode, DecisionNode.class);

        return response.getId();
    }

    private String createLeafDecisionNode(boolean leafValue) {

        DecisionNode decisionNode = createDecisionNode(DecisionNodeType.LEAF);
        decisionNode.setDecision(leafValue);

        DecisionNode response = getRestTemplate().postForObject(THINKER_CREATE_STRATEGY, decisionNode, DecisionNode.class);

        return response.getId();
    }


    private String createIndicatorDifferenceDecisionNode(List<String> decisionNodeComparisonIds, String nextDecisionNodeId) {

        DecisionNodeComparison decisionNodeComparison = createDecisionNodeComparison(new Double(0), new Double(20), DecisionNodeComparisonType.NUMERIC_DIFFERENCE, nextDecisionNodeId);

        DecisionNode decisionNode = createDecisionNode(DecisionNodeType.CONTINUOUS);
        decisionNode.getDecisionNodeComparisons().add(decisionNodeComparison);
        decisionNode.setDecisionNodeIndicators(decisionNodeComparisonIds);

        DecisionNode response = getRestTemplate().postForObject(THINKER_CREATE_STRATEGY, decisionNode, DecisionNode.class);

        return response.getId();
    }

    private DecisionNode createDecisionNode(DecisionNodeType decisionNodeType) {

        DecisionNode decisionNode = new DecisionNode();
        decisionNode.setDecisionNodeType(decisionNodeType);
        decisionNode.setDecisionNodeComparisons(new ArrayList<>());

        return decisionNode;
    }

    private DecisionNodeComparison createDecisionNodeComparison(Double min, Double max, DecisionNodeComparisonType comparisonType, String nextDecisionNodeId) {

        DecisionNodeComparison decisionNodeComparison = new DecisionNodeComparison();
        decisionNodeComparison.setComparison(new DecisionNodeComparisonRange(min, max, comparisonType));
        decisionNodeComparison.setNextDecisionNodeId(nextDecisionNodeId);

        return decisionNodeComparison;
    }


    private String createStochasticOscillator(DataPointIndicator indicator, int kPeriods, int kSlowPeriods, int dPeriods) {
        DataPointDetails dataPointDetails = new DataPointDetails();
        dataPointDetails.setIndicator(indicator);
        dataPointDetails.setSize(new DataPointSize(TimeUnit.DAYS, 1));
        dataPointDetails.setParameters(new HashMap<>());
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.REQUIRED_PERIODS, kPeriods + dPeriods + 1);
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_K_PERIODS, kPeriods);
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_K_SLOWING_PERIODS, kSlowPeriods);
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.STOCHASTIC_OSCILLATOR_D_PERIODS, dPeriods);

        DataPointDescription dataPointDescription = new DataPointDescription();
        dataPointDescription.setDetails(dataPointDetails);

        DataPointDescription response = getRestTemplate().postForObject(PRICER_CREATE_INDICATOR, dataPointDescription, DataPointDescription.class);

        return response.getId();
    }

    private String createSimpleMovingAverageIndicator(int periods) {

        DataPointDetails dataPointDetails = new DataPointDetails();
        dataPointDetails.setIndicator(DataPointIndicator.SIMPLE_MOVING_AVERAGE);
        dataPointDetails.setSize(new DataPointSize(TimeUnit.DAYS, 1));
        dataPointDetails.setParameters(new HashMap<>());
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.REQUIRED_PERIODS, periods);
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.SIMPLE_MOVING_AVERAGE_PERIODS, periods);

        DataPointDescription dataPointDescription = new DataPointDescription();
        dataPointDescription.setDetails(dataPointDetails);

        DataPointDescription response = getRestTemplate().postForObject(PRICER_CREATE_INDICATOR, dataPointDescription, DataPointDescription.class);

        return response.getId();
    }

    private String createCloseIndicator() {

        DataPointDetails dataPointDetails = new DataPointDetails();
        dataPointDetails.setIndicator(DataPointIndicator.CLOSE);
        dataPointDetails.setSize(new DataPointSize(TimeUnit.DAYS, 1));
        dataPointDetails.setParameters(new HashMap<>());
        dataPointDetails.getParameters().put(DataPointIndicatorParameter.REQUIRED_PERIODS, 1);

        DataPointDescription dataPointDescription = new DataPointDescription();
        dataPointDescription.setDetails(dataPointDetails);

        DataPointDescription response = getRestTemplate().postForObject(PRICER_CREATE_INDICATOR, dataPointDescription, DataPointDescription.class);

        return response.getId();

    }


    @Test
    public void initIndicators() {


    }



    public Trade request(PricerServiceTradeRequest request) {
        Trade trade = getRestTemplate().getForObject(THINKER_STRATEGY_DECISION_URL_BASE, Trade.class, getUriVariablesMap(request.getStrategyId(), request.getStock(), request.getTime()));
        return trade;
    }

    private RestTemplate getRestTemplate(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jacksonHttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jacksonHttpMessageConverter);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

    private Map<String, String> getUriVariablesMap(String strategyId, Stock stock, long time) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("strategy-id", strategyId);
        uriVariables.put("stock-symbol", stock.getSymbol());
        uriVariables.put("stock-exchange", stock.getExchange().name());
        uriVariables.put("epoch-time", String.valueOf(time));
        return uriVariables;
    }


}
