package com.jarias14.tekstratego.common.utilities;

import com.jarias14.tekstratego.common.resources.LinksResource;

public class LinksUtility {
    
    private String protocol = "http";
    private String host = "localhost";
    private String port = "8080";
    private String location = "tekstratego";
    
    private String getBaseLink() {
        return new String (protocol + "://" + host + ":" + port + "/" + location);
    }
    
    public LinksResource getPricerIndicatorLink(String type, String indicatorId) {
        
        String link = getBaseLink().concat("/pricer-service/indicators/{INDICATOR-ID}");
        link = link.replace("{INDICATOR-ID}", indicatorId);
        
        return new LinksResource(type, link);
    }

}
