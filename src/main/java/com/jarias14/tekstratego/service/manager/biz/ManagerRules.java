package com.jarias14.tekstratego.service.manager.biz;

import java.util.List;

import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.manager.model.Portfolio;
import com.jarias14.tekstratego.service.manager.model.Signal;

public interface ManagerRules {

    public List<Signal> filter(Portfolio portfolio, List<Alert> hypothesisAlerts);
    
}
