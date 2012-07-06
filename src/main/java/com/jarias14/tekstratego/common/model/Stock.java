package com.jarias14.tekstratego.common.model;

import java.io.Serializable;

public class Stock implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String symbol;
    private String exchange;
    
    public Stock(String exchange, String symbol) {
        this.symbol = symbol;
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

}
