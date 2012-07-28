package com.jarias14.tekstratego.service.manager.biz.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.UUID;

import com.jarias14.tekstratego.common.model.StatusEnum;
import com.jarias14.tekstratego.service.manager.biz.ManagerRules;
import com.jarias14.tekstratego.service.manager.biz.ManagerService;
import com.jarias14.tekstratego.service.manager.dao.ManagerDAO;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class DefaultManagerService implements ManagerService {

    private ManagerDAO managerDAO;
    private ManagerRules managerRules;
    
    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        
        // create unique id for new indicator
        String portfolioId = UUID.randomUUID().toString();
        portfolio.setId(portfolioId);
        
        // save to memory
        managerDAO.writePortfolio(portfolio);
        
        // send it back
        return getPortfolio(portfolioId);
    }

    @Override
    public Portfolio submitPortfolio(Portfolio portfolio, Calendar startDate, Calendar endDate) {
        
        if (!StatusEnum.READY.equals(portfolio.getStatus())) {
            return null;
        }
        
        // mark portfolio as in progress
        portfolio.setStatus(StatusEnum.PROCESSING);
        managerDAO.writePortfolio(portfolio);
        
        // analyze portfolio
        SortedSet<Calendar> dates = managerDAO.getMarketDates(startDate, endDate);
        
        for (Calendar today : dates) {
            
            // get all the alerts for the day
            List<Alert> alerts = managerDAO.getAlerts(portfolio.getHypothesisId(), today, portfolio.getPositions());
            portfolio.getAlerts().put(today, alerts);
            
            // filter out what to trade with the manager rules
            List<Alert> signals = managerRules.filter(portfolio, alerts);
            portfolio.getSignals().put(today, signals);
            
            // try to make the transactions
            List<Position> trades = managerDAO.transact(signals, today);
            portfolio.getTrades().put(today, trades);
            
            updatePortfolio(portfolio, trades);
        }
        
        //mark portfolio as executed and save all to memory
        portfolio.setStatus(StatusEnum.EXECUTED);
        managerDAO.writePortfolio(portfolio);
        
        // send back the portfolio
        return getPortfolio(portfolio.getId());
    }

    private void updatePortfolio(Portfolio portfolio, List<Position> trades) {
        
        Map<String, Position> positions = portfolio.getPositions();
        
        for (Position trade : trades) {
            
            BigDecimal tradeNumberOfShares = new BigDecimal(trade.getNumberOfShares());
            BigDecimal tradePriceOfShares = trade.getPurchaseValue();
            
            // update position 
            if (positions.containsKey(trade.getStock().getSymbol())) {
                BigDecimal curNumberOfShares = new BigDecimal(positions.get(trade.getStock().getSymbol()).getNumberOfShares());
                BigDecimal curPriceOfShares = positions.get(trade.getStock().getSymbol()).getPurchaseValue();
                
                BigDecimal newNumberOfShares = curNumberOfShares.add(tradeNumberOfShares);
                BigDecimal newPriceOfShares =
                        ((curNumberOfShares.multiply(curPriceOfShares))
                        .add(tradeNumberOfShares.multiply(tradePriceOfShares)))
                        .divide(newNumberOfShares);
                
                if (newNumberOfShares.compareTo(BigDecimal.ZERO) != 0) { 
                    positions.get(trade.getStock().getSymbol()).setNumberOfShares(newNumberOfShares.intValueExact());
                    positions.get(trade.getStock().getSymbol()).setPurchaseValue(newPriceOfShares);
                } else {
                    positions.remove(trade.getStock().getSymbol());
                }
                
            } else {
                Position pos = new Position();
                pos.setStock(trade.getStock());
                pos.setNumberOfShares(tradeNumberOfShares.intValueExact());
                pos.setPurchaseValue(tradePriceOfShares);
                positions.put(trade.getStock().getSymbol(), pos);
            }
            
            // update available cash
            portfolio.setAvailableCash(portfolio.getAvailableCash().subtract(tradeNumberOfShares.multiply(tradePriceOfShares)));
            
        }
        
    }

    @Override
    public Portfolio getPortfolio(String portfolioId) {
        return managerDAO.readPortfolio(portfolioId);
    }

    @Override
    public void addAlerts(String portfolioId, SortedMap<Calendar, List<Alert>> alerts) {
        
        // retrieve my portfolio
        Portfolio portfolio = getPortfolio(portfolioId);
        
        // add alerts to portfolio
        portfolio.addAlerts(alerts);
        
        // save to memory
        managerDAO.writePortfolio(portfolio);
    }

    @Override
    public SortedMap<Calendar,List<Alert>> getAlerts(String portfolioId, String sortBy) {
        return getPortfolio(portfolioId).getAlerts();
    }

    @Override
    public SortedMap<Calendar, List<Alert>> getSignals(String portfolioId, String sortBy) {
        return getPortfolio(portfolioId).getSignals();
    }

    @Override
    public SortedMap<Calendar, List<Position>> getTrades(String portfolioId, String sortBy) {
        return getPortfolio(portfolioId).getTrades();
    }

    public ManagerDAO getManagerDAO() {
        return managerDAO;
    }

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    public ManagerRules getManagerRules() {
        return managerRules;
    }

    public void setManagerRules(ManagerRules managerRules) {
        this.managerRules = managerRules;
    }

}
