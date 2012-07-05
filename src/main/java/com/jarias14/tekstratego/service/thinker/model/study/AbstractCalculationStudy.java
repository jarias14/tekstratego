package com.jarias14.tekstratego.service.thinker.model.study;

import java.io.Serializable;

import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public abstract class AbstractCalculationStudy implements Study, Serializable {
    
    private static final long serialVersionUID = 1L;

    private MembaseConnector memory;
    
    private String id;
    private String parentId;

    private String indicatorId;
    private Double studyValue;
    private int numberOfBarsBeforeCurrent;
    
    public AbstractCalculationStudy() {
        
    }
    
    public AbstractCalculationStudy(StudyResource resource) {
        this.id = resource.getId();
        this.indicatorId = resource.getIndicatorId();
        this.studyValue = Double.valueOf(resource.getStudyValue());
        this.numberOfBarsBeforeCurrent = Integer.parseInt(resource.getBarUnderTest());
    }
    
    protected StudyResource toResource(String type) {
        
        StudyResource resource = new StudyResource();
        resource.setType(type);
        resource.setStudyValue(String.valueOf(this.studyValue));
        resource.setId(this.id);
        resource.setIndicatorId(this.indicatorId);
        resource.setBarUnderTest(String.valueOf(this.numberOfBarsBeforeCurrent));
        return resource;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
