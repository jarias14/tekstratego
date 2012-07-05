package com.jarias14.tekstratego.common.resources;

public class LinksResource implements Comparable<LinksResource>{
    
    private String type;
    private String href;
    
    public LinksResource() {
        
    }
    
    public LinksResource(String type, String href) {
        this.type = type;
        this.href = href;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int compareTo(LinksResource arg0) {
        if (arg0.getType().equalsIgnoreCase(this.type)) {
            return 0;
        }
        return 1;
    }
}