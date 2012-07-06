package com.jarias14.tekstratego.common.resource;

import java.util.Calendar;
import java.util.SortedMap;


public class IndicatorValuesResource extends BaseResource {
    
    private static final long serialVersionUID = -5221140640190751254L;
    
    private SortedMap<Calendar, Double> values;
    
    public IndicatorValuesResource() {
        super();
    }

    public IndicatorValuesResource(SortedMap<Calendar, Double> values) {
        this();
        this.values = values;
    }

    public SortedMap<Calendar, Double> getValues() {
        return values;
    }

    public void setValues(SortedMap<Calendar, Double> values) {
        this.values = values;
    }

}
