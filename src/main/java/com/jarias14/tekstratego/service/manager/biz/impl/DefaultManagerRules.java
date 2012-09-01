package com.jarias14.tekstratego.service.manager.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jarias14.tekstratego.common.model.TradeTypeEnum;
import com.jarias14.tekstratego.service.manager.biz.ManagerRules;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;

public class DefaultManagerRules implements ManagerRules {

    @Override
    public List<Alert> filter(Portfolio portfolio, List<Alert> alerts) {
        
        List<Alert> filtered = new ArrayList<Alert>();
        
        for (Alert alert : alerts) {
            
            if (TradeTypeEnum.ENTRY.equals(alert.getStrategyType())) {
                
                if (isCashAvailable(portfolio)
                        && !doesPortfolioContainStock(portfolio, alert)
                        && isCashAvailableForThisTrade(portfolio, alert)) {
                    
                    filtered.add(getSignal(alert));
                }
                
            } else if (TradeTypeEnum.EXIT.equals(alert.getStrategyType())) {
                
                if (doesPortfolioContainStock(portfolio, alert)) {
                    
                    filtered.add(getSignal(alert));
                }
                
            }/* else if (TradeTypeEnum.SCALE.equals(alert.getStrategyType())) {
                
                if (doesPortfolioContainStock(portfolio, alert)
                        && isLimitInvestmentPerStockReached(portfolio, alert)) {
                    
                    //filtered.add(filterEntryTrade(alert));
                    filtered.add(getSignal(alert));
                }
                
            }*/
            
        }
        
        return filtered;
    }

    private boolean doesPortfolioContainStock(Portfolio portfolio, Alert alert) {
        
        if (portfolio.getPositions().get(alert.getStock().getSymbol()) != null) {
            return true;
        }
        
        return false;
    }

    private boolean isCashAvailableForThisTrade(Portfolio portfolio, Alert alert) {
        
        // if would exceed our max investment
        if (portfolio.getAvailableCash().compareTo(alert.getLimitPerTrade()) < 0) {
            return false;
        }
        return true;
    }
    
    private boolean isLimitInvestmentPerStockReached(Portfolio portfolio, Alert alert) {
        // if we have put more money into the stock than the limit per stock
        if (portfolio.getPositions().containsKey(alert.getStock().getSymbol())) {
            BigDecimal averagePrice = portfolio.getPositions().get(alert.getStock().getSymbol()).getPurchaseValue();
            BigDecimal totalPrice = averagePrice.multiply(new BigDecimal(portfolio.getPositions().get(alert.getStock().getSymbol()).getNumberOfShares()));
            
            if (totalPrice.compareTo(alert.getLimitPerStock()) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isCashAvailable(Portfolio portfolio) {
        if (portfolio.getAvailableCash().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return true;
    }
    
    private Alert getSignal(Alert alert) {
        
        Alert signal = new Alert();
        
        signal.setLimitPerTrade(alert.getLimitPerStock());
        signal.setStock(alert.getStock());
        signal.setStrategyType(alert.getStrategyType());
        signal.setLimitForStrategy(alert.getLimitForStrategy());
        signal.setLimitPerStock(alert.getLimitPerStock());
        signal.setLimitPerTrade(alert.getLimitPerTrade());
        signal.setDescription(alert.getDescription());
        signal.setHypothesisId(alert.getHypothesisId());
        signal.setStrategyId(alert.getStrategyId());
        signal.setStrategyExclusive(alert.isStrategyExclusive());
        signal.setTradedShares(alert.getTradedShares());
        
        return signal;
    }

}
