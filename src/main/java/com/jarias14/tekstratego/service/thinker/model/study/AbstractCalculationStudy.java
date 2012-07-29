package com.jarias14.tekstratego.service.thinker.model.study;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.StudyResource;
import com.jarias14.tekstratego.common.utilities.ConverterUtility;
import com.jarias14.tekstratego.common.utilities.MembaseConnector;
import com.jarias14.tekstratego.service.thinker.model.Study;

/**
 *  {
        "type":"lt",
        "studyValue":"25.01",
        "barUnderTest":"0",
        "indicatorId":"ec393711-a289-47b3-9fb8-0d4c3834c85a"
    }
 * @author jarias14
 *
 */
public abstract class AbstractCalculationStudy implements Study, Serializable {
    
    private static final long serialVersionUID = 1L;

    private MembaseConnector memory;
    
    private String id;
    private String parentId;

    private String indicatorId;
    private BigDecimal studyValue;
    private int numberOfBarsBeforeCurrent;
    
    public AbstractCalculationStudy() {
        
    }
    
    public AbstractCalculationStudy(StudyResource resource) {
        this.id = resource.getId();
        this.indicatorId = resource.getIndicatorId();
        this.studyValue = ConverterUtility.toBigDecimal(resource.getStudyValue());
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
    
    public BigDecimal getValueFromData(Map<String, SortedMap<Calendar, BigDecimal>> data) {
        
        SortedMap<Calendar, BigDecimal> indicatorValues = data.get(this.getIndicatorId());
        
        Object[] keyList = indicatorValues.keySet().toArray();
        
        int keyIndex = keyList.length - 1 - this.getNumberOfBarsBeforeCurrent();
        
        BigDecimal indicatorValue = indicatorValues.get(keyList[keyIndex]);
        
        return indicatorValue;
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

    public BigDecimal getStudyValue() {
        return studyValue;
    }

    public void setStudyValue(BigDecimal studyValue) {
        this.studyValue = studyValue;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
