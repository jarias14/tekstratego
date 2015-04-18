package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/14/2015.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private Stock stock;
    private Integer position;
    private Double averageCost;
    private Double marketPrice;
    private Double marketValue;
    private Double realizedProfitAndLoss;
    private Double unrealizedProfitAndLoss;
}
