package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;
import java.util.List;

public class Strategy {
    
    private String id;
    private StrategyTypeEnum type;
    private BigDecimal amount;
    private List<Study> studies;
    
    public Strategy() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StrategyTypeEnum getType() {
        return type;
    }

    public void setType(StrategyTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

}
