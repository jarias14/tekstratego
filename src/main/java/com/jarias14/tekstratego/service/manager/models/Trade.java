package com.jarias14.tekstratego.service.manager.models;

import com.jarias14.tekstratego.common.models.Stock;
import lombok.Data;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class Trade {
    private Stock stock;
    private long time;
    private TradeType type;
    private Integer shares;
    private Double cost;
    private Boolean strategyDecision;
}
