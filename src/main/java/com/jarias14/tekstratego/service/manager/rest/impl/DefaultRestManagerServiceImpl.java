package com.jarias14.tekstratego.service.manager.rest.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.jarias14.tekstratego.common.model.StatusEnum;
import com.jarias14.tekstratego.common.resource.AlertCollectionResource;
import com.jarias14.tekstratego.common.resource.PortfolioResource;
import com.jarias14.tekstratego.common.resource.TransactionCollectionResource;
import com.jarias14.tekstratego.common.utilities.LinksUtility;
import com.jarias14.tekstratego.service.manager.biz.ManagerService;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.manager.rest.RestManagerService;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class DefaultRestManagerServiceImpl implements RestManagerService {
    
    private ManagerService managerService;

    @Override
    public PortfolioResource createPortfolio(PortfolioResource resource) {
        
        Portfolio model = new Portfolio(resource);
        
        managerService.createPortfolio(model);
        
        return getPortfolio(model.getId());
    }

    @Override
    public PortfolioResource submitPortfolio(String portfolioId) {
        
        Portfolio model = managerService.getPortfolio(portfolioId);
        
        managerService.submitPortfolio(model, model.getStartDate(), model.getEndDate());
        
        return getPortfolio(portfolioId);
    }

    @Override
    public PortfolioResource getPortfolio(String portfolioId) {
        
        Portfolio model = managerService.getPortfolio(portfolioId);
        
        PortfolioResource resource = model.toResource();
        
        resource.getLinks().put("self", LinksUtility.getManagerPortfolioLink(portfolioId));
        resource.getLinks().put("alerts", LinksUtility.getManagerAlertsLink(portfolioId));
        resource.getLinks().put("signals", LinksUtility.getManagerSignalsLink(portfolioId));
        resource.getLinks().put("trades", LinksUtility.getManagerTradesLink(portfolioId));
        
        if (StatusEnum.READY.equals(model.getStatus())) {
            resource.getLinks().put("transactions", LinksUtility.getManagerTransactionsLink(portfolioId));
        } else {
            resource.getLinks().put("submit", LinksUtility.getManagerSubmitLink(portfolioId));
        }
        
        return resource;
    }

    @Override
    public AlertCollectionResource addAlerts(String portfolioId, AlertCollectionResource alerts) {
        
        SortedMap<Calendar,List<Alert>> model = alerts.toModel();
        
        managerService.addAlerts(portfolioId, model);
        
        return getAlerts(portfolioId, "date");
    }
    
    @Override
    public AlertCollectionResource getAlerts(String portfolioId, String sortBy) {
        // NICETOHAVE implement search by symbol
        
        SortedMap<Calendar,List<Alert>> model = managerService.getAlerts(portfolioId, sortBy);
        
        AlertCollectionResource resource = new AlertCollectionResource(model);
        
        resource.getLinks().put("self", LinksUtility.getManagerAlertsLink(portfolioId));
        resource.getLinks().put("portfolio", LinksUtility.getManagerPortfolioLink(portfolioId));
        
        return resource;
    }

    @Override
    public AlertCollectionResource getSignals(String portfolioId, String sortBy) {
        
        SortedMap<Calendar,List<Alert>> model = managerService.getSignals(portfolioId, sortBy);
        
        AlertCollectionResource resource = new AlertCollectionResource(model);
        
        resource.getLinks().put("self", LinksUtility.getManagerSignalsLink(portfolioId));
        resource.getLinks().put("portfolio", LinksUtility.getManagerPortfolioLink(portfolioId));
        
        return resource;
    }

    @Override
    public TransactionCollectionResource getTrades(String portfolioId, String sortBy) {
        
        Map<Calendar, List<Position>> model = managerService.getTrades(portfolioId, sortBy);
        
        TransactionCollectionResource resource = new TransactionCollectionResource(model);
        
        resource.getLinks().put("self", LinksUtility.getManagerSignalsLink(portfolioId));
        resource.getLinks().put("portfolio", LinksUtility.getManagerPortfolioLink(portfolioId));
        
        return resource;
    }
    
    public ManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }
}
