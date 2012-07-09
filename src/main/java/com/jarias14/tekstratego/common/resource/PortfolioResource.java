package com.jarias14.tekstratego.common.resource;

import java.util.Map;

public class PortfolioResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String status;
    private String initialCash;
    private String startDate;
    private String endDate;
    private Map<String, String> results;
    
    public PortfolioResource() {
        
    }
    
    public String getInitialCash() {
        return initialCash;
    }
    
    public void setInitialCash(String initialCash) {
        this.initialCash = initialCash;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
