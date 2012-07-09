package com.jarias14.tekstratego.common.resource;


public class StrategyResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String type;
    private String amount;
    private LinksResource study;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public LinksResource getStudy() {
        return study;
    }
    
    public void setStudy(LinksResource study) {
        this.study = study;
    }
}
