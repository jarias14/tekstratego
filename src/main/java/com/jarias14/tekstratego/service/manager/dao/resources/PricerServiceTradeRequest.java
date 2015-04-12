package com.jarias14.tekstratego.service.manager.dao.resources;

import com.jarias14.tekstratego.common.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricerServiceTradeRequest {
    private String strategyId;
    private Stock stock;
    private long time;
}
