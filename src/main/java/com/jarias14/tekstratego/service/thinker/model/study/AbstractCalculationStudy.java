package com.jarias14.tekstratego.service.thinker.model.study;

import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.thinker.model.Study;

public abstract class AbstractCalculationStudy implements Study {
    
    private MembaseConnector memory;
    
    private String id;
    private String indicatorId;
    private Double studyValue;
    private int numberOfBarsBeforeCurrent;
    
    public AbstractCalculationStudy() {
        
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public MembaseConnector getMemory() {
        return memory;
    }

    public void setMemory(MembaseConnector memory) {
        this.memory = memory;
    }

    public int getNumberOfBarsBeforeCurrent() {
        return numberOfBarsBeforeCurrent;
    }

    public void setNumberOfBarsBeforeCurrent(int numberOfBarsBeforeCurrent) {
        this.numberOfBarsBeforeCurrent = numberOfBarsBeforeCurrent;
    }

    public Double getStudyValue() {
        return studyValue;
    }

    public void setStudyValue(Double studyValue) {
        this.studyValue = studyValue;
    }

}
