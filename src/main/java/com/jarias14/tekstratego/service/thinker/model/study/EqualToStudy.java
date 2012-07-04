package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

public class EqualToStudy extends AbstractCalculationStudy {
    
    public EqualToStudy() {
        super();
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        Object[] keyList = indicatorValues.keySet().toArray();
        
        Double indicatorValue = indicatorValues.get(keyList[this.getNumberOfBarsBeforeCurrent()]);
        
        return indicatorValue == this.getStudyValue();
    }

}
