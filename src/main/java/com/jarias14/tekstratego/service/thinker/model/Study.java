package com.jarias14.tekstratego.service.thinker.model;

import java.util.Date;
import java.util.SortedMap;

public interface Study {
    
    public void setId(String id);
    public String getId();
    public boolean execute(SortedMap<Date, Double> indicatorValues);

}
