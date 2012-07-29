package com.jarias14.tekstratego.service.thinker.model.study;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.StudyResource;

public class EqualToStudy extends AbstractCalculationStudy {
    
    private static final long serialVersionUID = 1L;

    public EqualToStudy() {
        super();
    }
    
    public EqualToStudy(StudyResource resource) {
        super(resource);
    }
    
    @Override
    public boolean execute(Map<String, SortedMap<Calendar, BigDecimal>> data) {
        
        return super.getValueFromData(data) == this.getStudyValue();
    }
    
    @Override
    public StudyResource toResource() {
        return super.toResource("et");
    }

}
