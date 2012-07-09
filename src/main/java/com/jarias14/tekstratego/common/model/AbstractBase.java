package com.jarias14.tekstratego.common.model;

import java.io.Serializable;

import com.jarias14.tekstratego.common.resource.BaseResource;

public abstract class AbstractBase implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    public AbstractBase() {
        
    }
    
    public AbstractBase(BaseResource resource) {
        
        this.id = resource.getId();
    }
    
    public abstract BaseResource toResource();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
