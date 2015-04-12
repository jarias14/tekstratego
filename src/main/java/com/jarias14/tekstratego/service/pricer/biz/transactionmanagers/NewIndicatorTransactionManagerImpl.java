package com.jarias14.tekstratego.service.pricer.biz.transactionmanagers;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.skeleton.TransactionManager;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogStore;
import net.sf.ehcache.Element;

import java.util.UUID;

/**
 * Created by jarias14 on 4/5/2015.
 */
public class NewIndicatorTransactionManagerImpl implements TransactionManager<DataPointDescription, DataPointDescription> {

    private IndicatorCatalogStore indicatorDataStore;

    @Override
    public DataPointDescription process(DataPointDescription dataPointDescription) {
        if (dataPointDescription == null) {
            return null;
        }

        dataPointDescription.setId(UUID.randomUUID().toString());
        indicatorDataStore.put(new Element(dataPointDescription.getId(), dataPointDescription));

        return dataPointDescription;
    }

    public void setIndicatorDataStore(IndicatorCatalogStore indicatorDataStore) {
        this.indicatorDataStore = indicatorDataStore;
    }
}
