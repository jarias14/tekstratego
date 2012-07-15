package com.jarias14.tekstratego.service.thinker.model;

import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.StudyResource;

/**
 * {
 *   "type":"and",
 *   "studyValue":"25.01",
 *   "barUnderTest":"0",
 *   "indicatorId":"ec393711-a289-47b3-9fb8-0d4c3834c85a"
 * }
 * 
 * @author jarias14
 *
 */
public interface Study {
    
    public void setId(String id);
    public String getId();
    public boolean execute(Map<String,SortedMap<Calendar, Double>> indicatorValues);
    public StudyResource toResource();
    public String getParentId();
    public void setParentId(String id);

}
