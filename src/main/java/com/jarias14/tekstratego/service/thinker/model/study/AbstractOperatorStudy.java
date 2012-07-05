package com.jarias14.tekstratego.service.thinker.model.study;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.service.thinker.model.Study;
import com.jarias14.tekstratego.service.thinker.rest.resource.StudyResource;

public abstract class AbstractOperatorStudy implements Study , Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private List<Study> studies;
    
    public AbstractOperatorStudy() {
        studies = new ArrayList<Study>();
    }
    
    public AbstractOperatorStudy(StudyResource resource) {
        this.id = resource.getId();
        this.studies = new ArrayList<Study>(); // a new study shouldn't have child studies
    }
    
    protected StudyResource toResource(String type) {
        
        StudyResource resource = new StudyResource();
        resource.setType(type);
        resource.setId(this.id);
        return resource;
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
