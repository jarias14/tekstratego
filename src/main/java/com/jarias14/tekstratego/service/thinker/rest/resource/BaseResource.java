package com.jarias14.tekstratego.service.thinker.rest.resource;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "baseResource")
public class BaseResource implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private String selfLink;

	public String getSelfLink() {
		return selfLink;
	}
	
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

}
