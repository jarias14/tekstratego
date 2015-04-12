package com.jarias14.tekstratego.service.listener.rest.impl;

import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.listener.models.RawDataRequest;
import com.jarias14.tekstratego.service.listener.models.RawDataResponse;
import com.jarias14.tekstratego.service.listener.rest.RestListenerService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class DefaultRestListenerServiceImpl implements RestListenerService {

    private ApplicationService<RawDataRequest, RawDataResponse> rawDataSubscriptionApplicationService;
    private ApplicationService<String, RawDataRequest> rawDataUnsubscriptionApplicationService;

    @Override
    public RawDataResponse subscribe(RawDataRequest request) {
        return rawDataSubscriptionApplicationService.serviceRequest(request);
    }

    @Override
    public RawDataRequest unsubscribe(String subscriptionId) {
        return rawDataUnsubscriptionApplicationService.serviceRequest(subscriptionId);
    }

    @Required
    public void setRawDataSubscriptionApplicationService(ApplicationService<RawDataRequest, RawDataResponse> rawDataSubscriptionApplicationService) {
        this.rawDataSubscriptionApplicationService = rawDataSubscriptionApplicationService;
    }

    @Required
    public void setRawDataUnsubscriptionApplicationService(ApplicationService<String, RawDataRequest> rawDataUnsubscriptionApplicationService) {
        this.rawDataUnsubscriptionApplicationService = rawDataUnsubscriptionApplicationService;
    }
}
