package com.jarias14.tekstratego.common.resource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class BaseResource implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private Map<String, LinksResource> links;
    private String id;
    
    public BaseResource() {
        links = new HashMap<String, LinksResource>();
    }

    public Map<String, LinksResource> getLinks() {
        return links;
    }

    public void setLinks(Map<String, LinksResource> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
