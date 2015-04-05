package com.jarias14.tekstratego.service.pricer.rest;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.DataPointTimableDescription;
import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class RestPricerServiceImpl implements RestPricerService {

    private ApplicationService<DataPointDescription, DataPointDescription> requestIndicatorApplicationService;
    private ApplicationService<DataPointTimableDescription, Set<DataPointDescription>> updateIndicatorApplicationService;

    @Override
    public DataPointDescription requestIndicator(DataPointDescription request) {
        return requestIndicatorApplicationService.serviceRequest(request);
    }

    @Override
    public Set<DataPointDescription> updateIndicator(DataPointTimableDescription request) {
        return updateIndicatorApplicationService.serviceRequest(request);
    }

    @Required
    public void setRequestIndicatorApplicationService(ApplicationService<DataPointDescription, DataPointDescription> requestIndicatorApplicationService) {
        this.requestIndicatorApplicationService = requestIndicatorApplicationService;
    }

    @Required
    public void setUpdateIndicatorApplicationService(ApplicationService<DataPointTimableDescription, Set<DataPointDescription>> updateIndicatorApplicationService) {
        this.updateIndicatorApplicationService = updateIndicatorApplicationService;
    }
}
