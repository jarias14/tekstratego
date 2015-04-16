package com.jarias14.tekstratego.service.manager.models;

import com.jarias14.tekstratego.common.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jarias14 on 4/15/2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagedAccountStrategy {
    private TradeType tradeType;
    private List<Stock> stocks;
    private String strategyId;
    private Integer sharesToInvest;
}
