package com.jarias14.tekstratego.service.manager.biz;

import java.util.List;

import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;

public interface ManagerRules {

    public List<Alert> filter(Portfolio portfolio, List<Alert> hypothesisAlerts);
    
}
