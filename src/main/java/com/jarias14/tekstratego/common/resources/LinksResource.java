package com.jarias14.tekstratego.common.resources;

public class LinksResource implements Comparable<LinksResource>{
    
    private String href;
    
    public LinksResource() {
        
    }
    
    public LinksResource(String href) {
        this.href = href;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int compareTo(LinksResource arg0) {
        if (arg0.getHref().equalsIgnoreCase(this.href)) {
            return 0;
        }
        return 1;
    }
}