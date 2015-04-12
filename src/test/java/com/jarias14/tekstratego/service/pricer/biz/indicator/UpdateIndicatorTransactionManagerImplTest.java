package com.jarias14.tekstratego.service.pricer.biz.indicator;

import com.jarias14.tekstratego.common.cache.DataStore;
import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.DataProvider;
import com.jarias14.tekstratego.service.pricer.biz.processor.impl.NewDataPointIndicatorUpdateProcessorImpl;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.jarias14.tekstratego.service.pricer.biz.transactionmanagers.NewDataPointTransactionManagerImpl;
import com.jarias14.tekstratego.service.pricer.dao.IndicatorCatalogStore;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class UpdateIndicatorTransactionManagerImplTest {

    private NewDataPointTransactionManagerImpl tm;
    private NewDataPointIndicatorUpdateProcessorImpl updateSimpleIndicatorProcessor;
    private IndicatorCatalogStore indicatorCatalogDao;
    private DataStore rawDataStore;

    @Before
    public void setUp() throws Exception {
        rawDataStore = EasyMock.createMock(DataStore.class);
        indicatorCatalogDao = EasyMock.createMock(IndicatorCatalogStore.class);
        updateSimpleIndicatorProcessor = EasyMock.createMock(NewDataPointIndicatorUpdateProcessorImpl.class);

        tm = new NewDataPointTransactionManagerImpl();
        tm.setIndicatorCatalogDao(indicatorCatalogDao);
        tm.setRawDataStore(rawDataStore);
        tm.setUpdateSimpleIndicatorProcessor(updateSimpleIndicatorProcessor);
    }

    @Test
    public void test() {
        Stock stock = new Stock("DIS", StockExchange.NYSE);


        Set<DataPointDescription> dataPointDescriptionSet = new HashSet<>();

        DataPointDescription dataPointDescription1 = new DataPointDescription();
        dataPointDescription1.setStock(stock);
        dataPointDescription1.setDetails(new DataPointDetails(DataPointIndicator.EXPONENTIAL_MOVING_AVERAGE, TimeUnit.DAYS, 1));
        dataPointDescription1.getDetails().setNecessaryDataPointBars(99);
        dataPointDescriptionSet.add(dataPointDescription1);

        DataPointDescription dataPointDescription2 = new DataPointDescription();
        dataPointDescription2.setStock(stock);
        dataPointDescription2.setDetails(new DataPointDetails(DataPointIndicator.OPEN, TimeUnit.DAYS, 1));
        dataPointDescription2.getDetails().setNecessaryDataPointBars(-1);
        dataPointDescriptionSet.add(dataPointDescription2);

        DataPointDescription dataPointDescription3 = new DataPointDescription();
        dataPointDescription3.setStock(stock);
        dataPointDescription3.setDetails(new DataPointDetails(DataPointIndicator.CLOSE, TimeUnit.DAYS, 1));
        dataPointDescription3.getDetails().setNecessaryDataPointBars(100);
        dataPointDescriptionSet.add(dataPointDescription3);

        DataPointDescription dataPointDescription4 = new DataPointDescription();
        dataPointDescription4.setStock(stock);
        dataPointDescription4.setDetails(new DataPointDetails(DataPointIndicator.STOCHASTIC_OSCILLATOR_K, TimeUnit.DAYS, 1));
        dataPointDescription4.getDetails().setNecessaryDataPointBars(1);
        dataPointDescriptionSet.add(dataPointDescription4);

        DataPointDescription dataPointDescription5 = new DataPointDescription();
        dataPointDescription5.setStock(stock);
        dataPointDescription5.setDetails(new DataPointDetails(DataPointIndicator.STOCHASTIC_OSCILLATOR_D, TimeUnit.DAYS, 1));
        dataPointDescription4.getDetails().setNecessaryDataPointBars(10);
        dataPointDescriptionSet.add(dataPointDescription5);

        EasyMock.expect(indicatorCatalogDao.getIndicatorsByStock(stock)).andReturn(dataPointDescriptionSet);


        EasyMock.expect(rawDataStore.getDataPoints(stock, DataPointIndicator.RAW_CLOSE, 1000, 100)).andReturn(DataProvider.getCloses());
        EasyMock.expect(rawDataStore.getDataPoints(stock, DataPointIndicator.RAW_HIGH, 1000, 100)).andReturn(DataProvider.getHighs());
        EasyMock.expect(rawDataStore.getDataPoints(stock, DataPointIndicator.RAW_LOW, 1000, 100)).andReturn(DataProvider.getLows());
        EasyMock.expect(rawDataStore.getDataPoints(stock, DataPointIndicator.RAW_OPEN, 1000, 100)).andReturn(DataProvider.getLows());

        updateSimpleIndicatorProcessor.process(EasyMock.anyObject(NewDataPointIndicatorUpdateRequest.class));
        EasyMock.expectLastCall().times(5);


        DataPointTimableDescription request = new DataPointTimableDescription();
        request.setTime(1000);
        request.setStock(stock);//new Stock("DIS", StockExchange.NYSE));
        request.setDetails(new DataPointDetails(DataPointIndicator.RAW_CLOSE, TimeUnit.DAYS, 1));

        EasyMock.replay(rawDataStore, indicatorCatalogDao, updateSimpleIndicatorProcessor);
        tm.process(request);
        EasyMock.verify(rawDataStore, indicatorCatalogDao, updateSimpleIndicatorProcessor);




    }



}
