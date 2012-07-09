package com.jarias14.tekstratego.service.manager.biz;

import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;

import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;

public interface ManagerService {

    public Portfolio createPortfolio(Portfolio model);

    public Portfolio getPortfolio(String portfolioId);

    public void addAlerts(String portfolioId, SortedMap<Calendar, List<Alert>> model);

    public SortedMap<Calendar, List<Alert>> getAlerts(String portfolioId, String sortBy);

    public Portfolio submitPortfolio(Portfolio portfolio, Calendar startDate, Calendar endDate);

}
