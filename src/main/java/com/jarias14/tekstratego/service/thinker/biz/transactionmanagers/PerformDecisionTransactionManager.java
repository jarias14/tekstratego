package com.jarias14.tekstratego.service.thinker.biz.transactionmanagers;

import com.jarias14.tekstratego.common.models.MarketDataRequest;
import com.jarias14.tekstratego.common.skeleton.DataAccessObject;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.thinker.cache.DecisionNodeDataStore;
import com.jarias14.tekstratego.service.thinker.rest.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class PerformDecisionTransactionManager implements TransactionManager<DecisionRequest, Boolean> {

    private DataAccessObject<MarketDataRequest, Double> marketDataRequestDao;
    private DecisionNodeDataStore decisionNodeDataStore;

    @Override
    public Boolean process(DecisionRequest decisionRequest) {

        DecisionNode decisionNode = decisionNodeDataStore.retrieve(decisionRequest.getDecisionNodeId());

        Boolean decision;

        if (DecisionNodeType.LEAF.equals(decisionNode.getDecisionNodeType())) {
            decision = decisionNode.getDecision();
        } else {

            List<DecisionNodeComparison> decisionNodeComparisons =
                    decisionNode.getDecisionNodeComparisons().stream()
                    .filter(dnc -> getDecision(decisionRequest, decisionNode.getDecisionNodeIndicators(), dnc))
                    .filter(Objects::nonNull).collect(Collectors.toList());

            String nextDecisionNode = CollectionUtils.isNotEmpty(decisionNodeComparisons) ? decisionNodeComparisons.get(0).getNextDecisionNodeId() : null;

            decision = StringUtils.isEmpty(nextDecisionNode) ? false : process(new DecisionRequest(decisionRequest.getStock(), nextDecisionNode, decisionRequest.getEpochTime()));
        }

        return decision;
    }

    private boolean getDecision(DecisionRequest decisionRequest, List<String> decisionNodeIndicators, DecisionNodeComparison decisionNodeComparison) {

        DecisionNodeComparisonRange decisionNodeComparisonRange = decisionNodeComparison.getComparison();

        if (DecisionNodeComparisonType.NUMERIC_DIFFERENCE.equals(decisionNodeComparison.getComparison().getType())) {

            MarketDataRequest marketDataRequest = new MarketDataRequest();
            marketDataRequest.setStock(decisionRequest.getStock());
            marketDataRequest.setTime(decisionRequest.getEpochTime());

            marketDataRequest.setId(decisionNodeIndicators.get(0));
            Double firstValue = marketDataRequestDao.request(marketDataRequest);

            marketDataRequest.setId(decisionNodeIndicators.get(1));
            Double secondValue = marketDataRequestDao.request(marketDataRequest);

            if (null == firstValue || null == secondValue) {
                return false;
            }

            Double difference = firstValue - secondValue;

            return difference >= decisionNodeComparisonRange.getMin() && difference <= decisionNodeComparisonRange.getMax();

        } else if (DecisionNodeComparisonType.NUMERIC_RANGE.equals(decisionNodeComparison.getComparison().getType())) {

            MarketDataRequest marketDataRequest = new MarketDataRequest();
            marketDataRequest.setStock(decisionRequest.getStock());
            marketDataRequest.setTime(decisionRequest.getEpochTime());

            marketDataRequest.setId(decisionNodeIndicators.get(0));
            Double value = marketDataRequestDao.request(marketDataRequest);

            if (null == value) {
                return false;
            }

            return value >= decisionNodeComparisonRange.getMin() && value <= decisionNodeComparisonRange.getMax();

        } else if (DecisionNodeComparisonType.PERCENT_DIFFERENCE.equals(decisionNodeComparison.getComparison().getType())) {
            throw new NotImplementedException();
        } else {
            throw new NotImplementedException();
        }
    }

    @Required
    public void setDecisionNodeDataStore(DecisionNodeDataStore decisionNodeDataStore) {
        this.decisionNodeDataStore = decisionNodeDataStore;
    }

    @Required
    public void setMarketDataRequestDao(DataAccessObject<MarketDataRequest, Double> marketDataRequestDao) {
        this.marketDataRequestDao = marketDataRequestDao;
    }
}
