package com.jarias14.tekstratego.service.thinker.rest.model;

import com.jarias14.tekstratego.common.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionRequest {

    private Stock stock;
    private String decisionNodeId;
    private long epochTime;
}
