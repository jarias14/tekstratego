package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Stock implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String symbol;
    private StockExchange exchange;

}
