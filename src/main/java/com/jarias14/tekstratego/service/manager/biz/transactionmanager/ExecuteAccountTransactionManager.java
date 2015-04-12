package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class ExecuteAccountTransactionManager implements TransactionManager<String, ManagedAccount> {

    private ManagedAccountStore managedAccountStore;
    private Processor<ManagedAccount> strategyProcessor;

    @Override
    public ManagedAccount process(String managedAccountId) {

        ManagedAccount managedAccount = managedAccountStore.fetch(managedAccountId);
        strategyProcessor.process(managedAccount);

        return managedAccount;
    }

    @Required
    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }

    @Required
    public void setStrategyProcessor(Processor<ManagedAccount> strategyProcessor) {
        this.strategyProcessor = strategyProcessor;
    }
}
