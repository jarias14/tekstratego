package com.jarias14.tekstratego.service.thinker.rest.model;

import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.common.models.Identifiable;
import lombok.Data;

import java.util.List;

/**
 * Created by jarias14 on 3/28/2015.
 */
@Data
public class DecisionNode extends Identifiable {

    private DecisionNodeType decisionNodeType;
    private List<DataPointDetails> decisionNodeIndicators;
    private List<DecisionNodeComparison> decisionNodeComparisons;
    private Boolean decision;


}
