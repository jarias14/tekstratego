package com.jarias14.tekstratego.common.utilities;

import java.util.HashMap;

public class LinksUtility {
    
    private String protocol = "http";
    private String host = "localhost";
    private String port = "8080";
    private String location = "tekstratego";
    
    private String getBaseLink() {
        return new String (protocol + "://" + host + ":" + port + "/" + location);
    }
    
    public HashMap<String,String> getPricerIndicatorLink(String key, String indicatorId) {
        HashMap<String,String> map = new HashMap<String,String>();
        
        String link = getBaseLink().concat("/pricer-service/indicators/{INDICATOR-ID}");
        link = link.replace("{INDICATOR-ID}", indicatorId);
        
        map.put(key, link);
        return map;
    }

}
