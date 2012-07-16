package com.jarias14.tekstratego.common.resource;

import java.util.Map;

/**
 * 
 * {
 *   "type": "price",
 *   "sizeOfBars": "ONE_DAY",
 *   "priceOfBars": "OPEN"
 * }
 * 
 * 
 * {
 *   "type": "simple_moving_average",
 *   "sizeOfBars": "ONE_DAY",
 *   "priceOfBars": "OPEN",
 *   "details": {
 *       "period": "3"
 *   }
 * }
 * 
 * @author jarias14
 *
 */
public class IndicatorResource extends BaseResource {
    
    private static final long serialVersionUID = 3749994184149195859L;
    
    private String type;
    private String sizeOfBars;
    private String priceOfBars;

    private Map<String, String> details;
    
    public IndicatorResource() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    
    public String getPriceOfBars() {
        return priceOfBars;
    }

    public void setPriceOfBars(String priceOfBars) {
        this.priceOfBars = priceOfBars;
    }
}
