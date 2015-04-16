package com.jarias14.tekstratego.service.thinker.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionNodeComparisonRange {
    private Double min;
    private Double max;
    private DecisionNodeComparisonType type;

}
