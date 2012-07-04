package com.jarias14.tekstratego.service.thinker.model.study;

import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.service.thinker.model.Study;

public abstract class AbstractOperatorStudy implements Study {
    
    private String id;
    private List<Study> studies;
    
    public AbstractOperatorStudy() {
        studies = new ArrayList<Study>();
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }
    
    public List<Study> getStudies() {
        return studies;
    }
    
    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

}
