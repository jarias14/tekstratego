package com.jarias14.tekstratego.service.thinker.model;

import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public interface Study {
    
    public void setId(String id);
    public String getId();
    public boolean execute(Map<String,SortedMap<Calendar, Double>> indicatorValues);
    public StudyResource toResource();
    public String getParentId();
    public void setParentId(String id);

}
