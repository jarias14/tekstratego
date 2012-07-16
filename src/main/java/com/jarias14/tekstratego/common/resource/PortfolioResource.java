package com.jarias14.tekstratego.common.resource;

import java.util.Map;

/**
 * 
 * {
 *   "hypothesisId":"731c749f-7a3b-4c2d-9d83-4c069153792f",
 *   "initialCash":"100000.00",
 *   "availableCash":"100000.00",
 *   "startDate":"2010-01-01T00:00:00",
 *   "endDate":"2011-01-01T00:00:00"
 * }
 * 
 * @author jarias14
 *
 */
public class PortfolioResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private String hypothesisId;
    private String status;
    private String initialCash;
    private String availableCash;
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

    public String getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(String availableCash) {
        this.availableCash = availableCash;
    }

    public String getHypothesisId() {
        return hypothesisId;
    }

    public void setHypothesisId(String hypothesisId) {
        this.hypothesisId = hypothesisId;
    }

}
