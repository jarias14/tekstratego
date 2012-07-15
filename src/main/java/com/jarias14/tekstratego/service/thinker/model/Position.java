package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.Stock;
import com.jarias14.tekstratego.common.resource.PositionResource;

public class Position extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private Stock stock;
    private int numberOfShares;
    private BigDecimal purchaseValue;

    public Position() {
        
    }
    
    public Position(PositionResource resource) {
        stock = new Stock("", resource.getStock());
        numberOfShares = Integer.valueOf(resource.getNumberOfShares());
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public PositionResource toResource() {
        
        PositionResource resource = new PositionResource();
        
        resource.setStock(stock.getSymbol());
        resource.setNumberOfShares(String.valueOf(numberOfShares));
        
        return resource;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

}
