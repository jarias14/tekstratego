package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public class OrStudy extends AbstractOperatorStudy {

    private static final long serialVersionUID = 1L;

    public OrStudy() {
        super();
    }
    
    public OrStudy(StudyResource resource){
        super(resource);
    }
    
    @Override
    public boolean execute(Map<String, SortedMap<Date, Double>> indicatorValues) {
        
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
