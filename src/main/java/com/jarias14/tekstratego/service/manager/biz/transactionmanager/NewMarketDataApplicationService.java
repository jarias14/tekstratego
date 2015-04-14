package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.common.models.MarketDataNotification;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class NewMarketDataApplicationService implements TransactionManager<MarketDataNotification, Boolean> {

    private ManagedAccountStore managedAccountStore;
    private Processor<ManagedAccount> managedAccountProcessor;

    @Override
    public Boolean process(MarketDataNotification marketDataNotification) {

        List<ManagedAccount> managedAccounts = managedAccountStore.getManagedAccounts(marketDataNotification.getStock());

        managedAccounts.stream().forEach(managedAccountProcessor::process);

        return true;
    }

    @Required
    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }

    @Required
    public void setManagedAccountProcessor(Processor<ManagedAccount> managedAccountProcessor) {
        this.managedAccountProcessor = managedAccountProcessor;
    }
}
