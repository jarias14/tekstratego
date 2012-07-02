package com.jarias14.tekstratego.service.thinker.rest.resource;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;

public class BaseResource implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @XmlElement
    private HashMap<String, HashMap<String, String>> links;
    
    public BaseResource() {
        
    }

	public HashMap<String, HashMap<String, String>> getLink() {
		return links;
	}
	
	public void addSelfLink(String key, HashMap<String, String> link) {
	    if (links == null) {
	        links = new HashMap<String, HashMap<String, String>>();
	    }
	    links.put(key, link);
	}

}
