package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/14/2015.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TradeRequest {

    private Stock stock;
    private Integer quantity;
}
