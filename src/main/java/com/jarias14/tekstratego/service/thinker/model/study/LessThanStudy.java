package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.rest.StudyResource;

public class LessThanStudy extends AbstractCalculationStudy {
    
    public LessThanStudy() {
        super();
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        Object[] keyList = indicatorValues.keySet().toArray();
        
        Double indicatorValue = indicatorValues.get(keyList[this.getNumberOfBarsBeforeCurrent()]);
        
        return indicatorValue < this.getStudyValue();
    }

    @Override
    public StudyResource toResource() {
        return super.toResource("lt");
    }

}
