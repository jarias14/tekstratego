package com.jarias14.tekstratego.service.pricer.biz.indicator;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.DataPointTimableDescription;
import com.jarias14.tekstratego.common.skeleton.Processor;
import com.jarias14.tekstratego.service.pricer.biz.processor.UpdateSimpleIndicatorRequest;
import com.jarias14.tekstratego.service.pricer.biz.transactionmanager.UpdateIndicatorTransactionManagerImpl;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogStore;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;


public class UpdateIndicatorTransactionManagerImplTest {

    private UpdateIndicatorTransactionManagerImpl tm;
    private Processor<UpdateSimpleIndicatorRequest> updateSimpleIndicatorProcessor;
    private IndicatorCatalogStore indicatorCatalogDao;
    private DataStore rawDataStore;

    @Before
    public void setUp() throws Exception {
        rawDataStore = EasyMock.createMock(DataStore.class);
        indicatorCatalogDao = EasyMock.createMock(IndicatorCatalogStore.class);
        updateSimpleIndicatorProcessor = EasyMock.createMock(Processor.class);

        tm = new UpdateIndicatorTransactionManagerImpl();
        tm.setIndicatorCatalogDao(indicatorCatalogDao);
        tm.setRawDataStore(rawDataStore);
        tm.setUpdateSimpleIndicatorProcessor(updateSimpleIndicatorProcessor);
    }

    @Test
    public void test() {
        DataPointTimableDescription request = new DataPointTimableDescription();




    }



}
