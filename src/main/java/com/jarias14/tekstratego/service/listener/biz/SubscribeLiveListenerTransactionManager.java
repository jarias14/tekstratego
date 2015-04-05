package com.jarias14.tekstratego.service.listener.biz;

import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.listener.models.ListenerRequest;
import com.jarias14.tekstratego.service.listener.models.ListenerResponse;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class SubscribeLiveListenerTransactionManager implements TransactionManager<ListenerRequest, ListenerResponse> {
    @Override
    public ListenerResponse process(ListenerRequest listenerRequest) {
        return null;
    }
}
