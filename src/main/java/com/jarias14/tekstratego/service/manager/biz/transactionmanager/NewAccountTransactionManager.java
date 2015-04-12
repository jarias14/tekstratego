package com.jarias14.tekstratego.service.manager.biz.transactionmanager;

import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.manager.cache.ManagedAccountStore;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class NewAccountTransactionManager implements TransactionManager<ManagedAccount, ManagedAccount> {

    private ManagedAccountStore managedAccountStore;

    @Override
    public ManagedAccount process(ManagedAccount managedAccount) {

        managedAccount.setId(UUID.randomUUID().toString());
        managedAccountStore.store(managedAccount);
        return managedAccountStore.fetch(managedAccount.getId());
    }

    @Required
    public void setManagedAccountStore(ManagedAccountStore managedAccountStore) {
        this.managedAccountStore = managedAccountStore;
    }
}
