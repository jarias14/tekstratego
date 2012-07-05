package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.rest.StudyResource;

public class OrStudy extends AbstractOperatorStudy {

    public OrStudy() {
        super();
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        boolean result = false;
        
        for (Study study : this.getStudies()) {
            result = result || study.execute(indicatorValues);
        }
        
        return result;
    }

    @Override
    public StudyResource toResource() {
        return super.toResource("or");
    }

}
