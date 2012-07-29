package com.jarias14.tekstratego.service.thinker.model.study;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.resource.StudyResource;
import com.jarias14.tekstratego.service.thinker.model.Study;

public class OrStudy extends AbstractOperatorStudy {

    private static final long serialVersionUID = 1L;

    public OrStudy() {
        super();
    }
    
    public OrStudy(StudyResource resource){
        super(resource);
    }
    
    @Override
    public boolean execute(Map<String, SortedMap<Calendar, BigDecimal>> indicatorValues) {
        
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
