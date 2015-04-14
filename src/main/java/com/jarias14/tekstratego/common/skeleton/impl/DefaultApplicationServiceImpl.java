package com.jarias14.tekstratego.common.skeleton.impl;

import com.jarias14.tekstratego.common.skeleton.*;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class DefaultApplicationServiceImpl<REQUEST, RESPONSE> implements ApplicationService<REQUEST, RESPONSE> {

    private Processor<REQUEST> requestProcessor;
    private Processor<REQUEST> requestValidator;
    private Map<String, TransactionManager<REQUEST, RESPONSE>> transactionManagerMap;
    private TransactionDecisionMaker<REQUEST> transactionDecisionMaker;
    private TransactionCache<REQUEST, RESPONSE> transactionCache;


    @Override
    public RESPONSE serviceRequest(REQUEST request) {

        RESPONSE response = null;

        String transactionManagerId = TransactionDecisionMaker.DEFAULT;

        if (null == request) {
            return null;
        }

        if (CollectionUtils.isEmpty(transactionManagerMap)) {
            return null;
        }

        if (null != requestProcessor) {
            requestProcessor.process(request);
        }

        if (null != requestValidator) {
            requestValidator.process(request);
        }

        if (null != transactionDecisionMaker) {
            transactionManagerId = transactionDecisionMaker.retrieveTransactionManagerId(request);
        }

        if (null == transactionManagerMap.get(transactionManagerId)) {
            return null;
        }

        if (null != transactionCache) {
            response = transactionCache.getDataPoint(request);
        }

        if (null == response) {
            response = transactionManagerMap.get(transactionManagerId).process(request);

            if (null != transactionCache) {
                transactionCache.putDataPoint(request, response);
            }
        }

        return response;
    }


    public void setRequestProcessor(Processor<REQUEST> requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    public void setRequestValidator(Processor<REQUEST> requestValidator) {
        this.requestValidator = requestValidator;
    }

    public void setTransactionManagerMap(Map<String, TransactionManager<REQUEST, RESPONSE>> transactionManagerMap) {
        this.transactionManagerMap = transactionManagerMap;
    }

    public void setTransactionDecisionMaker(TransactionDecisionMaker<REQUEST> transactionDecisionMaker) {
        this.transactionDecisionMaker = transactionDecisionMaker;
    }

    public void setTransactionCache(TransactionCache transactionCache) {
        this.transactionCache = transactionCache;
    }
}
