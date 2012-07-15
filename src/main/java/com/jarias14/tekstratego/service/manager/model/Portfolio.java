package com.jarias14.tekstratego.service.manager.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.model.StatusEnum;
import com.jarias14.tekstratego.common.resource.PortfolioResource;
import com.jarias14.tekstratego.common.utilities.ConverterUtility;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class Portfolio extends AbstractBase {

    private static final long serialVersionUID = 1L;
    
    private String hypothesisId;

    private Map<String, Position> positions;
    private SortedMap<Calendar, List<Position>> trades;
    private SortedMap<Calendar, List<Alert>> alerts;
    private StatusEnum status;
    private BigDecimal initialCash;
    private Calendar startDate;
    private Calendar endDate;
    private Map<String, BigDecimal> results;
    private BigDecimal availableCash;
    
    public Portfolio(PortfolioResource resource) {
        super(resource);

        this.hypothesisId = resource.getHypothesisId();
        this.positions = new HashMap<String, Position>();
        this.trades = new TreeMap<Calendar,List<Position>>();
        this.alerts = new TreeMap<Calendar,List<Alert>>();
        this.status = StatusEnum.READY;
        this.initialCash = ConverterUtility.toBigDecimal(resource.getInitialCash());
        this.startDate = ConverterUtility.toCalendar(resource.getStartDate());
        this.endDate = ConverterUtility.toCalendar(resource.getEndDate());
        this.results = new HashMap<String, BigDecimal>();
        this.availableCash = ConverterUtility.toBigDecimal(resource.getInitialCash());
    }

    @Override
    public PortfolioResource toResource() {
        
        PortfolioResource resource = new PortfolioResource();
        
        resource.setId(super.getId());
        resource.setStatus(status.name());
        resource.setHypothesisId(this.hypothesisId);
        resource.setInitialCash(initialCash.toString());
        resource.setStartDate(ConverterUtility.toString(this.startDate));
        resource.setEndDate(ConverterUtility.toString(this.endDate));
        resource.setAvailableCash(this.availableCash.toString());

        resource.setResults(new HashMap<String,String>());
        for (Entry<String,BigDecimal> result : results.entrySet()) {
            resource.getResults().put(result.getKey(), result.getValue().toString());
        }
        
        return resource;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public BigDecimal getInitialCash() {
        return initialCash;
    }

    public void setInitialCash(BigDecimal initialCash) {
        this.initialCash = initialCash;
    }

    public SortedMap<Calendar, List<Alert>> getAlerts() {
        return alerts;
    }

    public void setAlerts(SortedMap<Calendar, List<Alert>> alerts) {
        this.alerts = alerts;
    }
    
    public void addAlerts(SortedMap<Calendar, List<Alert>> alerts) {
        
        for (Calendar cal : alerts.keySet()) {
            
            if (!this.alerts.containsKey(cal)) {
                this.alerts.put(cal, new ArrayList<Alert>());
            }
            
            this.alerts.get(cal).addAll(alerts.get(cal));
        }
    }
    
    public SortedMap<Calendar, List<Position>> getTrades() {
        return trades;
    }

    public void setTrades(SortedMap<Calendar, List<Position>> trades) {
        this.trades = trades;
    }
    
    public Map<String, Position> getPositions() {
        return positions;
    }

    public void setPositions(Map<String, Position> positions) {
        this.positions = positions;
    }
    
    public void addTrades(Calendar date, List<Position> newTrades) {
        
        // for each new trade..
        for (Position newTrade : newTrades) {
            
            // shares = previous number + new number
            int numberOfShares =
                    this.positions.get(newTrade.getStock().getSymbol()).getNumberOfShares()
                    + newTrade.getNumberOfShares();
            
            if (numberOfShares == 0) {
                
                // if shares = 0 then we are not holding a position, thus, remove
                this.positions.remove(newTrade.getStock().getSymbol());
            } else {
                
                // if it is != 0, then set the new number 
                this.positions.get(newTrade.getStock().getSymbol()).setNumberOfShares(numberOfShares);
            }
            
            // add trade to the list of trades
            if (this.trades.get(date) == null) {
                this.trades.put(date, new ArrayList<Position>());
            }
            
            this.trades.get(date).add(newTrade);
        }
        
    }

    public String getHypothesisId() {
        return hypothesisId;
    }

    public void setHypothesisId(String hypothesisId) {
        this.hypothesisId = hypothesisId;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Map<String, BigDecimal> getResults() {
        return results;
    }

    public void setResults(Map<String, BigDecimal> results) {
        this.results = results;
    }

    public BigDecimal getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(BigDecimal availableCash) {
        this.availableCash = availableCash;
    }
}
