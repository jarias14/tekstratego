package com.jarias14.tekstratego.service.thinker.rest.model;

import lombok.Data;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class DecisionNodeComparisonRange {
    private Double min;
    private Double max;
    private DecisionNodeComparisonType type;
}
