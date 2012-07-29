package com.jarias14.tekstratego.common.resource;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedMap;


public class IndicatorValuesResource extends BaseResource {
    
    private static final long serialVersionUID = -5221140640190751254L;
    
    private SortedMap<Calendar, BigDecimal> values;
    
    public IndicatorValuesResource() {
        super();
    }

    public IndicatorValuesResource(SortedMap<Calendar, BigDecimal> values2) {
        this();
        this.values = values2;
    }

    public SortedMap<Calendar, BigDecimal> getValues() {
        return values;
    }

    public void setValues(SortedMap<Calendar, BigDecimal> values) {
        this.values = values;
    }

}
