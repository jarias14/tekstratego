package com.jarias14.tekstratego.common.utilities;

import java.util.Map;

import com.jarias14.tekstratego.common.resource.LinksResource;

public class LinksUtility {
    
    private static String protocol = "http";
    private static String host = "localhost";
    private static String port = "8080";
    private static String location = "tekstratego";
    
    public final static String THINKER_HYPOTHESIS_ALERTS = "/thinker-service/hypothesis/{hypothesis}/alerts/{date}";
    public final static String PRICER_INDICATOR_VALUES = "/pricer-service/indicators/{indicator}/prices/{symbol}";
    public static final String MANAGER_PORTFOLIO_LINK = "/manager-service/portfolio/{portfolio}";
    public static final String MANAGER_PORTFOLIO_SUMBMIT_LINK = "/manager-service/portfolio/{portfolio}/submit";
    public static final String MANAGER_ALERTS_LINK = "/manager-service/portfolio/{portfolio}/alerts";
    public static final String MANAGER_ALERT_LINK = "/manager-service/portfolio/{portfolio}/alert/{alert}";
    public static final String MANAGER_TRANSACTIONS_LINK = "/manager-service/portfolio/{portfolio}/transactions";
    public static final String TRADER_TRANSACT = "/trader-service/trade/symbol/{symbol}/shares/{shares}";
    public static final String PRICER_INDICATOR = "/pricer-service/indicators";

    public static String getUrl(String template, Map<String,String> replacements, Map<String,String> parameters) {
        String url = getBaseLink().concat(template);
        
        for (String key : replacements.keySet()) {
            url = url.replace("{"+key+"}", replacements.get(key));
        }
        
        if (parameters.size() > 0) {
            url = url.concat("?");
            for (String key : parameters.keySet()) {
                url = url.concat(key + "=" + parameters.get(key) + "&");
            }
        }
        
        return url;
    }
    
    private static String getBaseLink() {
        return new String (protocol + "://" + host + ":" + port + "/" + location);
    }
    
    public static LinksResource getPricerIndicatorLink(String indicatorId) {
        
        String link = getBaseLink().concat("/pricer-service/indicators/{INDICATOR-ID}");
        link = link.replace("{INDICATOR-ID}", indicatorId);
        
        return new LinksResource(link);
    }

    public static LinksResource getThinkerHypothesisLink(String hypothesisId) {
        
        String link = getBaseLink().concat("/thinker-service/hypothesis/{HYPOTHESIS-ID}");
        link = link.replace("{HYPOTHESIS-ID}", hypothesisId);
        
        return new LinksResource(link);
    }

    public static LinksResource getThinkerStrategyLink(String hypothesisId, String strategyId) {
        
        String link = getBaseLink().concat("/thinker-service/hypothesis/{HYPOTHESIS-ID}/strategies/{STRATEGY-ID}");
        link = link.replace("{HYPOTHESIS-ID}", hypothesisId);
        link = link.replace("{STRATEGY-ID}", strategyId);
        
        return new LinksResource(link);
    }

    public static LinksResource getThinkerStudyLink(String hypothesisId, String strategyId, String studyId) {
            
        String link = getBaseLink().concat("/thinker-service/hypothesis/{HYPOTHESIS-ID}/strategies/{STRATEGY-ID}/studies/{STUDY-ID}");
        link = link.replace("{HYPOTHESIS-ID}", hypothesisId);
        link = link.replace("{STRATEGY-ID}", strategyId);
        link = link.replace("{STUDY-ID}", studyId);
        
        return new LinksResource(link);
    }

    public static LinksResource getManagerPortfolioLink(String portfolioId) {
            
        String link = getBaseLink().concat("/manager-service/portfolio/{PORTFOLIO-ID}");
        link = link.replace("{PORTFOLIO-ID}", portfolioId);
        
        return new LinksResource(link);
    }

    public static LinksResource getManagerAlertsLink(String portfolioId) {
        
        String link = getBaseLink().concat("/manager-service/portfolio/{PORTFOLIO-ID}/alerts");
        link = link.replace("{PORTFOLIO-ID}", portfolioId);
    
        return new LinksResource(link);
    }

    public static LinksResource getManagerTransactionsLink(String portfolioId) {
        
        String link = getBaseLink().concat("/manager-service/portfolio/{PORTFOLIO-ID}/transactions");
        link = link.replace("{PORTFOLIO-ID}", portfolioId);
    
        return new LinksResource(link);
    }

    public static LinksResource getManagerSubmitLink(String portfolioId) {
        
        String link = getBaseLink().concat("/manager-service/portfolio/{PORTFOLIO-ID}/submit");
        link = link.replace("{PORTFOLIO-ID}", portfolioId);
        
        return new LinksResource(link);
    }
    
    
    
}
