package com.jarias14.tekstratego.service.manager.rest.impl;

import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.models.MarketDataNotification;
import com.jarias14.tekstratego.service.manager.rest.RestManagerService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class RestManagerServiceImpl implements RestManagerService {

    private ApplicationService<ManagedAccount, ManagedAccount> newAccountApplicationService;
    private ApplicationService<String, ManagedAccount> retrieveAccountApplicationService;
    private ApplicationService<String, ManagedAccount> retrieveAccountTradeStatusApplicationService;
    private ApplicationService<MarketDataNotification, Boolean> newMarketDataApplicationService;

    //chrome-extension://lhkmoheomjbkfloacpgllgjcamhihfaj/index.html?id=c1856b9abefcf7556bca72c872d0e348
    @Override
    public ManagedAccount createAccount(ManagedAccount request) {
        return newAccountApplicationService.serviceRequest(request);
    }

    @Override
    public ManagedAccount retrieveAccountTradeHistory(String accountId) {
        return retrieveAccountApplicationService.serviceRequest(accountId);
    }

    @Override
    public ManagedAccount retrieveAccountTradeStatus(String accountId) {
        return retrieveAccountTradeStatusApplicationService.serviceRequest(accountId);
    }

    @Override
    public Boolean processMarketData(MarketDataNotification marketDataNotification) {
        return newMarketDataApplicationService.serviceRequest(marketDataNotification);
    }

    @Required
    public void setRetrieveAccountTradeStatusApplicationService(ApplicationService<String, ManagedAccount> retrieveAccountTradeStatusApplicationService) {
        this.retrieveAccountTradeStatusApplicationService = retrieveAccountTradeStatusApplicationService;
    }

    @Required
    public void setRetrieveAccountApplicationService(ApplicationService<String, ManagedAccount> retrieveAccountApplicationService) {
        this.retrieveAccountApplicationService = retrieveAccountApplicationService;
    }

    @Required
    public void setNewAccountApplicationService(ApplicationService<ManagedAccount, ManagedAccount> newAccountApplicationService) {
        this.newAccountApplicationService = newAccountApplicationService;
    }

    @Required
    public void setNewMarketDataApplicationService(ApplicationService<MarketDataNotification, Boolean> newMarketDataApplicationService) {
        this.newMarketDataApplicationService = newMarketDataApplicationService;
    }
}
