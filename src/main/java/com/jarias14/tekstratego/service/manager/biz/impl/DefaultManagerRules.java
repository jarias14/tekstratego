package com.jarias14.tekstratego.service.manager.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.service.manager.biz.ManagerRules;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;

public class DefaultManagerRules implements ManagerRules {

    @Override
    public List<Alert> filter(Portfolio portfolio, List<Alert> alerts) {
        
        List<Alert> filtered = new ArrayList<Alert>();
        
        for (Alert alert : alerts) {
            
            // if no available cash
            if (portfolio.getAvailableCash().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            
            // if we have put more money into the stock than the limit per stock
            if (portfolio.getPositions().containsKey(alert.getStock().getSymbol())) {
                BigDecimal averagePrice = portfolio.getPositions().get(alert.getStock().getSymbol()).getPurchaseValue();
                BigDecimal totalPrice = averagePrice.multiply(new BigDecimal(portfolio.getPositions().get(alert.getStock().getSymbol()).getNumberOfShares()));
                
                if (totalPrice.compareTo(alert.getLimitPerStock()) >= 0) {
                    continue;
                }
            }
            
            // if would exceed our max investment
            if (portfolio.getAvailableCash().compareTo(alert.getAmountToTrade()) > 0) {
                continue;
            }
            
            // if we get here, we do want to buy...
            Alert signal = new Alert();
            signal.setAmountToTrade(alert.getLimitPerStock());
            signal.setStock(alert.getStock());
            signal.setStrategyType(alert.getStrategyType());
            signal.setLimitForStrategy(alert.getLimitForStrategy());
            signal.setLimitPerStock(alert.getLimitPerStock());
            signal.setDescription(alert.getDescription());
            signal.setHypothesisId(alert.getHypothesisId());
            signal.setStrategyId(alert.getStrategyId());
            filtered.add(signal);
        }
        
        return filtered;
    }

}
