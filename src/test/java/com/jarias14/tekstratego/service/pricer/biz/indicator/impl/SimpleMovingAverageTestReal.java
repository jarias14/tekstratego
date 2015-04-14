package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import com.jarias14.tekstratego.common.models.*;
import com.jarias14.tekstratego.service.pricer.biz.indicators.impl.SimpleMovingAverageCalculator;
import com.jarias14.tekstratego.service.pricer.biz.processor.model.NewDataPointIndicatorUpdateRequest;
import com.tictactec.ta.lib.CoreAnnotated;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimpleMovingAverageTestReal {

    private SimpleMovingAverageCalculator emaCalculator;
    
    @Before
    public void setUp() {
        emaCalculator = new SimpleMovingAverageCalculator();
        emaCalculator.setTaLib(new CoreAnnotated());
    }
    
    @Test
    public void test() {

        NewDataPointIndicatorUpdateRequest request = new NewDataPointIndicatorUpdateRequest();
        request.setRequestedIndicator(getIndicator());
        request.setData(getTestData());

        Double result = emaCalculator.execute(request);


        Assert.assertEquals(new Double(23.13), result);
    }

    private DataPointTimableDescription getIndicator() {
        DataPointSize dataPointSize = new DataPointSize();
        dataPointSize.setQuantity(1);
        dataPointSize.setUnit(TimeUnit.DAYS);

        DataPointDetails details = new DataPointDetails();
        details.setSize(dataPointSize);
        details.setIndicator(DataPointIndicator.SIMPLE_MOVING_AVERAGE);
        details.setIndicatorParameters(new HashMap<>());
        details.getIndicatorParameters().put(DataPointIndicatorParameter.REQUIRED_PERIODS, 10);

        Stock stock = new Stock("TEST", StockExchange.NYSE);

        DataPointTimableDescription request = new DataPointTimableDescription();
        request.setTime(100);
        request.setDetails(details);
        request.setStock(stock);
        request.setId("123");


        return request;
    }

/*
    private SortedMap<Calendar, BigDecimal> getExpectedEMA() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> emaData = new TreeMap<Calendar, BigDecimal>();
        emaData.put(getCalendar(i++), new BigDecimal(22.2734));
        emaData.put(getCalendar(i++), new BigDecimal(22.1940));
        emaData.put(getCalendar(i++), new BigDecimal(22.0847));
        emaData.put(getCalendar(i++), new BigDecimal(22.1741));
        emaData.put(getCalendar(i++), new BigDecimal(22.1840));
        emaData.put(getCalendar(i++), new BigDecimal(22.1344));
        emaData.put(getCalendar(i++), new BigDecimal(22.2337));
        emaData.put(getCalendar(i++), new BigDecimal(22.4323));
        emaData.put(getCalendar(i++), new BigDecimal(22.2436));
        emaData.put(getCalendar(i++), new BigDecimal(22.2248));
        emaData.put(getCalendar(i++), new BigDecimal(22.2119));
        emaData.put(getCalendar(i++), new BigDecimal(22.2448));
        emaData.put(getCalendar(i++), new BigDecimal(22.2697));
        emaData.put(getCalendar(i++), new BigDecimal(22.3317));
        emaData.put(getCalendar(i++), new BigDecimal(22.5179));
        emaData.put(getCalendar(i++), new BigDecimal(22.7968));
        emaData.put(getCalendar(i++), new BigDecimal(22.9707));
        emaData.put(getCalendar(i++), new BigDecimal(23.1273));
        emaData.put(getCalendar(i++), new BigDecimal(23.2772));
        emaData.put(getCalendar(i++), new BigDecimal(23.3420));
        emaData.put(getCalendar(i++), new BigDecimal(23.4294));
        emaData.put(getCalendar(i++), new BigDecimal(23.5099));
        emaData.put(getCalendar(i++), new BigDecimal(23.5361));
        emaData.put(getCalendar(i++), new BigDecimal(23.4726));
        emaData.put(getCalendar(i++), new BigDecimal(23.4044));
        emaData.put(getCalendar(i++), new BigDecimal(23.3902));
        emaData.put(getCalendar(i++), new BigDecimal(23.2611));
        emaData.put(getCalendar(i++), new BigDecimal(23.2314));
        emaData.put(getCalendar(i++), new BigDecimal(23.0807));
        emaData.put(getCalendar(i++), new BigDecimal(22.9156));
        return emaData;
        
    }*/
    
    private Set<DataPointCollection> getTestData() {

        DataPointSize dataPointSize = new DataPointSize();
        dataPointSize.setQuantity(1);
        dataPointSize.setUnit(TimeUnit.DAYS);

        DataPointDetails details = new DataPointDetails();
        details.setSize(dataPointSize);
        details.setIndicator(DataPointIndicator.RAW_CLOSE);
        details.setIndicatorParameters(new HashMap<>());
        details.getIndicatorParameters().put(DataPointIndicatorParameter.REQUIRED_PERIODS, 10);


        DataPointCollection dataPointCollection = new DataPointCollection();
        dataPointCollection.setDetails(details);
        dataPointCollection.setStock(new Stock("TEST", StockExchange.NYSE));
        dataPointCollection.setId("BLAH");


        int i = 0;
        dataPointCollection.setDataPoints(new ArrayList<>());
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.2734)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1940)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.0847)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1741)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1840)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1344)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.2337)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.4323)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.2436)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.2933)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1542)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.3926)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.3816)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.6109)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.3558)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(24.0519)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.7530)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.8324)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.9516)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.6338)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.8225)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.8722)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.6537)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.1870)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.0976)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.3260)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.6805)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(23.0976)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.4025)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(22.1725)));

        Set dataSet = new HashSet<>();
        dataSet.add(dataPointCollection);

        return dataSet;
    }
    
    private Calendar getCalendar(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, i);
        return cal;
    }

}
