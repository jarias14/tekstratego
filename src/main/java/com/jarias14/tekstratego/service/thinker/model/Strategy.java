package com.jarias14.tekstratego.service.thinker.model;

import java.math.BigDecimal;

import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;
import com.jarias14.tekstratego.service.thinker.rest.resource.HypothesisResource;
import com.jarias14.tekstratego.service.thinker.rest.resource.StrategyResource;

public class Strategy extends AbstractBase {
    
    private static final long serialVersionUID = 1L;
    
    private StrategyTypeEnum type;
    private BigDecimal amount;
    private Study study;
    
    public Strategy() {
        
    }

    public Strategy(StrategyResource resource) {
        super(resource);
        this.type = StrategyTypeEnum.valueOf(resource.getType());
        this.amount = BigDecimal.valueOf(Double.valueOf((resource.getAmount())));
        this.study = new AndStudy();
        this.study.setId("root");
    }

    public StrategyResource toResource() {
        StrategyResource resource = new StrategyResource();
        
        resource.setId(super.getId());
        resource.setType(type.toString());
        resource.setAmount(amount.toString());
        
        return resource;
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
