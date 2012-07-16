package com.jarias14.tekstratego.common.resource;

public class TransactionResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String stock;
    private String sharesPrice;
    private String sharesNumber;
    
    public String getStock() {
        return stock;
    }
    
    public void setStock(String stock) {
        this.stock = stock;
    }
    
    public String getSharesPrice() {
        return sharesPrice;
    }
    
    public void setSharesPrice(String sharesPrice) {
        this.sharesPrice = sharesPrice;
    }
    
    public String getSharesNumber() {
        return sharesNumber;
    }
    
    public void setSharesNumber(String sharesNumber) {
        this.sharesNumber = sharesNumber;
    }

}
