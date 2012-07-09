package com.jarias14.tekstratego.service.manager.model;

import com.jarias14.tekstratego.common.resource.AlertResource;

public class Signal extends Alert {

    private static final long serialVersionUID = 1L;

    public Signal() {
        super();
    }
    
    public Signal(AlertResource resource) {
        super(resource);
    }

}
