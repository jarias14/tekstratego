package com.jarias14.tekstratego.common.utilities;

import com.jarias14.tekstratego.common.resources.LinksResource;

public class LinksUtility {
    
    private static String protocol = "http";
    private static String host = "localhost";
    private static String port = "8080";
    private static String location = "tekstratego";
    
    private static String getBaseLink() {
        return new String (protocol + "://" + host + ":" + port + "/" + location);
    }
    
    public static LinksResource getPricerIndicatorLink(String type, String indicatorId) {
        
        String link = getBaseLink().concat("/pricer-service/indicators/{INDICATOR-ID}");
        link = link.replace("{INDICATOR-ID}", indicatorId);
        
        return new LinksResource(type, link);
    }

    public static LinksResource getPricerHypothesisLink(String type, String hypothesisId) {
        
        String link = getBaseLink().concat("/thinker-service/hypothesis/{HYPOTHESIS-ID}");
        link = link.replace("{HYPOTHESIS-ID}", hypothesisId);
        
        return new LinksResource(type, link);
    }

    public static LinksResource getPricerHypothesisLink(String type, String hypothesisId, String strategyId) {
        
        String link = getBaseLink().concat("/thinker-service/hypothesis/{HYPOTHESIS-ID}/strategies/{STRATEGY-ID}");
        link = link.replace("{HYPOTHESIS-ID}", hypothesisId);
        link = link.replace("{STRATEGY-ID}", strategyId);
        
        return new LinksResource(type, link);
    }
}
