package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.manager.models.MarketDataNotification;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class RestPricerServiceImpl implements RestPricerService {

    private ApplicationService<DataPointDescription, DataPointDescription> newIndicatorApplicationService;
    private ApplicationService<MarketDataNotification, Set<DataPointDescription>> newDataPointApplicationService;

    @Override
    public DataPointDescription createIndicator(DataPointDescription request) {
        return newIndicatorApplicationService.serviceRequest(request);
    }

    @Override
    public Set<DataPointDescription> addDataPoint(MarketDataNotification request) {
        return newDataPointApplicationService.serviceRequest(request);
    }

    @Required
    public void setNewIndicatorApplicationService(ApplicationService<DataPointDescription, DataPointDescription> newIndicatorApplicationService) {
        this.newIndicatorApplicationService = newIndicatorApplicationService;
    }

    @Required
    public void setNewDataPointApplicationService(ApplicationService<MarketDataNotification, Set<DataPointDescription>> newDataPointApplicationService) {
        this.newDataPointApplicationService = newDataPointApplicationService;
    }
}
