package com.jarias14.tekstratego.service.manager.dao.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.jarias14.tekstratego.service.manager.dao.ManagerDAO;
import com.jarias14.tekstratego.service.manager.model.Alert;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class AlertSubmitHelper implements Callable<List<Alert>> {

    private ManagerDAO managerDAO;
    private String hypothesisId;
    private Calendar today;
    private Map<String, Position> positions;
    
    public AlertSubmitHelper(ManagerDAO managerDAO, String hypothesisId,
            Calendar today, Map<String, Position> positions) {
        this.managerDAO = managerDAO;
        this.hypothesisId = hypothesisId;
        this.today = today;
        this.positions = positions;
    }

    @Override
    public List<Alert> call() throws Exception {
        return managerDAO.getAlerts(hypothesisId, today, positions);
    }
}
