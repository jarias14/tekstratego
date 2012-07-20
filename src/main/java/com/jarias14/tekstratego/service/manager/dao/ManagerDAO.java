package com.jarias14.tekstratego.service.manager.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.thinker.model.Position;

public interface ManagerDAO {

    public Portfolio readPortfolio(String portfolioId);

    public void writePortfolio(Portfolio portfolio);

    public SortedSet<Calendar> getMarketDates(Calendar startDate, Calendar endDate);

    public List<Alert> getAlerts(String hypothesisId, Calendar today, Map<String, Position> positions);

    public List<Position> transact(List<Alert> signals, Calendar today);

}
