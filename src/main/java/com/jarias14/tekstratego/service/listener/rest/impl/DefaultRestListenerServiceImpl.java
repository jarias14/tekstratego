package com.jarias14.tekstratego.service.listener.rest.impl;

import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.listener.models.ListenerRequest;
import com.jarias14.tekstratego.service.listener.models.ListenerResponse;
import com.jarias14.tekstratego.service.listener.rest.RestListenerService;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class DefaultRestListenerServiceImpl implements RestListenerService {

    private ApplicationService<ListenerRequest, ListenerResponse> applicationService;

    @Override
    public ListenerResponse subscribe(ListenerRequest request) {
        request.setRequestType(ListenerRequest.RequestType.SUBSCRIBE);
        ListenerResponse response = applicationService.serviceRequest(request);
        return response;
    }

    @Override
    public ListenerResponse unsubscribe(String stockSymbol) {
        ListenerRequest request = new ListenerRequest();
        request.setSymbol(stockSymbol);
        request.setRequestType(ListenerRequest.RequestType.UNSUBSCRIBE);
        ListenerResponse response = applicationService.serviceRequest(request);
        return response;
    }

    public void setApplicationService(ApplicationService<ListenerRequest, ListenerResponse> applicationService) {
        this.applicationService = applicationService;
    }
}
