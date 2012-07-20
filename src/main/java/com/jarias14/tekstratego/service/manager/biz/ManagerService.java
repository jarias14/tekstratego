package com.jarias14.tekstratego.service.manager.biz;

import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.thinker.model.Position;

public interface ManagerService {

    public Portfolio createPortfolio(Portfolio model);

    public Portfolio getPortfolio(String portfolioId);

    public void addAlerts(String portfolioId, SortedMap<Calendar, List<Alert>> model);

    public Portfolio submitPortfolio(Portfolio portfolio, Calendar startDate, Calendar endDate);

    public SortedMap<Calendar, List<Alert>> getAlerts(String portfolioId, String sortBy);

    public SortedMap<Calendar, List<Alert>> getSignals(String portfolioId, String sortBy);

    public SortedMap<Calendar, List<Position>> getTrades(String portfolioId, String sortBy);

}
