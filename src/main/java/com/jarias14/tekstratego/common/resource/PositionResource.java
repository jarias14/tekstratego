package com.jarias14.tekstratego.common.resource;

public class PositionResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String stock;
    private String numberOfShares;

    public PositionResource() {
        
    }

    public String getStock() {
        return stock;
    }
    
    public void setStock(String stock) {
        this.stock = stock;
    }
    
    public String getNumberOfShares() {
        return numberOfShares;
    }
    
    public void setNumberOfShares(String numberOfShares) {
        this.numberOfShares = numberOfShares;
    }
    
}
