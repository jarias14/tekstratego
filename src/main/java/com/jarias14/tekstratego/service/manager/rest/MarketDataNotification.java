package com.jarias14.tekstratego.service.manager.rest;

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
public class MarketDataNotification {
    private Stock stock;
    private long time;

}
