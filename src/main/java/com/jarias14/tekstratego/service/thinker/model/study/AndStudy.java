package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public class AndStudy extends AbstractOperatorStudy {

    private static final long serialVersionUID = 1L;

    public AndStudy() {
        super();
    }
    
    public AndStudy(StudyResource resource) {
        super(resource);
    }
    
    @Override
    public boolean execute(SortedMap<Date, Double> indicatorValues) {
        
        boolean result = true;
        
        for (Study study : this.getStudies()) {
            result = result && study.execute(indicatorValues);
        }
        
        return result;
    }
    
    @Override
    public StudyResource toResource() {
        return super.toResource("and");
    }


}
