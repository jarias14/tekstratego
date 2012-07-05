package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public class GreaterThanStudy extends AbstractCalculationStudy {
    
    private static final long serialVersionUID = 1L;

    public GreaterThanStudy() {
        super();
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        Object[] keyList = indicatorValues.keySet().toArray();
        
        Double indicatorValue = indicatorValues.get(keyList[this.getNumberOfBarsBeforeCurrent()]);
        
        return indicatorValue > this.getStudyValue();
    }
    
    @Override
    public StudyResource toResource() {
        return super.toResource("gt");
    }

}
