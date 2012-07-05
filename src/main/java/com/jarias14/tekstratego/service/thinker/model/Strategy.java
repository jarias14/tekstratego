package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;

public class Strategy {
    
    private String id;
    private StrategyTypeEnum type;
    private BigDecimal amount;
    private Study study;
    
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

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

}
