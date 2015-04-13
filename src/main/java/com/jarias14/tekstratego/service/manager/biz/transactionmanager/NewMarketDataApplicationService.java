package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.ApplicationService;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.rest.MarketDataNotification;

import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class NewMarketDataApplicationService implements ApplicationService<MarketDataNotification, Boolean> {

    private ManagedAccountStore managedAccountStore;
    private Processor<ManagedAccount> managedAccountProcessor;

    @Override
    public Boolean serviceRequest(MarketDataNotification marketDataNotification) {

        List<ManagedAccount> managedAccounts = managedAccountStore.getManagedAccounts(marketDataNotification.getStock());

        managedAccounts.stream().forEach(managedAccountProcessor::process);

        return true;
    }

    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }
}
