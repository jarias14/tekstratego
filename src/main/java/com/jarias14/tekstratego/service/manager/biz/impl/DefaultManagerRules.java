package com.jarias14.tekstratego.service.manager.biz.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.service.manager.biz.ManagerRules;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.manager.model.Signal;

public class DefaultManagerRules implements ManagerRules {

    @Override
    public List<Signal> filter(Portfolio portfolio, List<Alert> alerts) {
        
        List<Signal> filtered = new ArrayList<Signal>();
        
        for (Alert alert : alerts) {
            
            // if no available cash
            if (portfolio.getAvailableCash().compareTo(BigInteger.ZERO) <= 0) {
                continue;
            }
            
            // if we have put more money into the stock than the limit per stock
            if (portfolio.getPositions().get(alert.getStock().getSymbol()).getPurchaseValue().compareTo(alert.getLimitPerStock()) >= 0) {
                continue;
            }
            
            // if we get here, we do want to buy...
            Signal signal = new Signal();
            
            signal.setAmountToTrade(alert.getLimitPerStock());
        }
        
        return filtered;
    }

}
