package com.jarias14.tekstratego.service.pricer.rest.resource;

import java.util.Map;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

public class IndicatorResource extends BaseResource {
    
    private static final long serialVersionUID = 3749994184149195859L;
    
    private String indicatorId;
    private String indicatorType;
    private String sizeOfBars;
    private String numberOfBars;
    private Map<String, String> details;
    
    public IndicatorResource() {
        super();
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }

    public String getSizeOfBars() {
        return sizeOfBars;
    }

    public void setSizeOfBars(String sizeOfBars) {
        this.sizeOfBars = sizeOfBars;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public String getNumberOfBars() {
        return numberOfBars;
    }

    public void setNumberOfBars(String numberOfBars) {
        this.numberOfBars = numberOfBars;
    }

}
