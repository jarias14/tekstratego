package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.StudyResource;

public class LessThanStudy extends AbstractCalculationStudy {
    
    private static final long serialVersionUID = 1L;

    public LessThanStudy() {
        super();
    }
    
    public LessThanStudy(StudyResource resource) {
        super(resource);
    }
    
    @Override
    public boolean execute(Map<String, SortedMap<Calendar, Double>> data) {
        
        return super.getValueFromData(data) < this.getStudyValue();
    }

    @Override
    public StudyResource toResource() {
        return super.toResource("lt");
    }

}
