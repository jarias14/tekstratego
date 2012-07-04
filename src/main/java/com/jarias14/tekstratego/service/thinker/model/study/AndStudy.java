package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.model.Study;

public class AndStudy extends AbstractOperatorStudy {

    public AndStudy() {
        super();
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        boolean result = true;
        
        for (Study study : this.getStudies()) {
            result = result && study.execute(indicatorValues);
        }
        
        return result;
    }


}
