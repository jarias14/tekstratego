package com.jarias14.tekstratego.service.thinker.rest.resource;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.jarias14.tekstratego.common.resources.LinksResource;

public class BaseResource implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private Set<LinksResource> links;
    private String id;
    
    public BaseResource() {
        links = new TreeSet<LinksResource>();
    }

    public Set<LinksResource> getLinks() {
        return links;
    }

    public void setLinks(Set<LinksResource> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
