package com.jarias14.tekstratego.service.thinker.biz.transactionanagers;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.thinker.cache.DecisionNodeDataStore;
import com.jarias14.tekstratego.service.thinker.rest.model.*;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class PerformDecisionTransactionManager implements TransactionManager<DecisionRequest, Boolean> {

    private DecisionNodeDataStore decisionNodeDataStore;
    private DataStore indicatorDataStore;

    @Override
    public Boolean process(DecisionRequest decisionRequest) {

        DecisionNode decisionNode = decisionNodeDataStore.retrieve(decisionRequest.getDecisionNodeId());

        Boolean decision;

        if (DecisionNodeType.LEAF.equals(decisionNode.getDecisionNodeType())) {
            decision = decisionNode.getDecision();
        } else {

            List<DataPointCollection> dataPointCollection =
                    decisionNode.getDecisionNodeIndicators().stream()
                    .map(dataPointDetail -> this.getDataPointCollection(decisionRequest.getStock(), decisionRequest.getEpochTime(), dataPointDetail))
                    .collect(Collectors.toList());

            String nextDecisionNode =
                    decisionNode.getDecisionNodeComparisons().stream()
                    .filter(dnc -> getDecision(dnc, dataPointCollection))
                    .findFirst()
                    .get()
                    .getNextDecisionNodeId();

            decision = process(new DecisionRequest(decisionRequest.getStock(), nextDecisionNode, decisionRequest.getEpochTime()));
        }

        return decision;
    }

    private boolean getDecision(DecisionNodeComparison decisionNodeComparison, List<DataPointCollection> dataPointCollection) {

        DecisionNodeComparisonRange decisionNodeComparisonRange = decisionNodeComparison.getComparison();

        if (DecisionNodeComparisonType.NUMERIC_DIFFERENCE.equals(decisionNodeComparison.getComparison().getType())) {

            DataPoint<Double> firstDataPoint = (DataPoint<Double>) dataPointCollection.get(0).getDataPoints().get(0);
            DataPoint<Double> secondDataPoint = (DataPoint<Double>) dataPointCollection.get(1).getDataPoints().get(0);
            Double difference = firstDataPoint.getValue() - secondDataPoint.getValue();
            return difference >= decisionNodeComparisonRange.getMin() && difference <= decisionNodeComparisonRange.getMax();

        } else if (DecisionNodeComparisonType.NUMERIC_RANGE.equals(decisionNodeComparison.getComparison().getType())) {

            DataPoint<Double> dataPoint = (DataPoint<Double>) dataPointCollection.get(0).getDataPoints().get(0);
            Double dataPointValue = dataPoint.getValue();
            return dataPointValue >= decisionNodeComparisonRange.getMin() && dataPointValue <= decisionNodeComparisonRange.getMax();

        } else if (DecisionNodeComparisonType.PERCENT_DIFFERENCE.equals(decisionNodeComparison.getComparison().getType())) {
            throw new NotImplementedException();
        } else {
            throw new NotImplementedException();
        }
    }

    private DataPointCollection getDataPointCollection(Stock stock, long epochTime, DataPointDetails dataPointDetails) {
        return indicatorDataStore.getDataPoints(stock, dataPointDetails.getIndicator(), epochTime, 1);
    }

    @Required
    public void setDecisionNodeDataStore(DecisionNodeDataStore decisionNodeDataStore) {
        this.decisionNodeDataStore = decisionNodeDataStore;
    }

    @Required
    public void setIndicatorDataStore(DataStore indicatorDataStore) {
        this.indicatorDataStore = indicatorDataStore;
    }
}
