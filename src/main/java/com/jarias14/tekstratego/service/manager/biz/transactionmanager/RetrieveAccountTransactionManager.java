package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class RetrieveAccountTransactionManager implements TransactionManager<String, ManagedAccount> {

    private ManagedAccountStore managedAccountStore;

    @Override
    public ManagedAccount process(String managedAccountId) {
        return managedAccountStore.fetch(managedAccountId);
    }

    @Required
    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }
}
