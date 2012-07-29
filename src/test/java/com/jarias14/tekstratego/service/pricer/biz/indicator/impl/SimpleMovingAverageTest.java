package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleMovingAverageTest {

    private SortedMap<Calendar, BigDecimal> testData;
    private SimpleMovingAverage sma;
    
    @Before
    public void setUp() {
        testData = getTestData();
        sma = new SimpleMovingAverage();
    }
    
    @Test
    public void test() {
        
        BigDecimal test = new BigDecimal(123.345532);
        test = test.setScale(2, RoundingMode.HALF_EVEN);
        
        
        sma.setPeriod(10);
        Object[] actualValues = sma.calculate(testData, Calendar.getInstance(), Calendar.getInstance()).values().toArray();
        Object[] expectedValues = getExpectedSMA().values().toArray();
        
        for (int i = 0; i < actualValues.length; i++) {
            System.out.println((BigDecimal)expectedValues[i] + " " + (BigDecimal)actualValues[i]);
            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            System.out.println(expected + " " + actual);
            System.out.println(expected.compareTo(actual));
            Assert.assertTrue(expected.doubleValue() - actual.doubleValue() <= .011 && expected.doubleValue() - actual.doubleValue() >= -.011);
        }
        
        Assert.assertEquals(expectedValues.length, actualValues.length);
    }
    
    private SortedMap<Calendar, BigDecimal> getExpectedSMA() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> smaData = new TreeMap<Calendar, BigDecimal>();
        //smaData.put(getCalendar(i++), new BigDecimal(22.27));
        //smaData.put(getCalendar(i++), new BigDecimal(22.19));
        //smaData.put(getCalendar(i++), new BigDecimal(22.08));
        //smaData.put(getCalendar(i++), new BigDecimal(22.17));
        //smaData.put(getCalendar(i++), new BigDecimal(22.18));
        //smaData.put(getCalendar(i++), new BigDecimal(22.13));
        //smaData.put(getCalendar(i++), new BigDecimal(22.23));
        //smaData.put(getCalendar(i++), new BigDecimal(22.43));
        //smaData.put(getCalendar(i++), new BigDecimal(22.24));
        smaData.put(getCalendar(i++), new BigDecimal(22.22));
        smaData.put(getCalendar(i++), new BigDecimal(22.21));
        smaData.put(getCalendar(i++), new BigDecimal(22.23));
        smaData.put(getCalendar(i++), new BigDecimal(22.26));
        smaData.put(getCalendar(i++), new BigDecimal(22.31));
        smaData.put(getCalendar(i++), new BigDecimal(22.42));
        smaData.put(getCalendar(i++), new BigDecimal(22.61));
        smaData.put(getCalendar(i++), new BigDecimal(22.77));
        smaData.put(getCalendar(i++), new BigDecimal(22.91));
        smaData.put(getCalendar(i++), new BigDecimal(23.08));
        smaData.put(getCalendar(i++), new BigDecimal(23.21));
        smaData.put(getCalendar(i++), new BigDecimal(23.38));
        smaData.put(getCalendar(i++), new BigDecimal(23.53));
        smaData.put(getCalendar(i++), new BigDecimal(23.65));
        smaData.put(getCalendar(i++), new BigDecimal(23.71));
        smaData.put(getCalendar(i++), new BigDecimal(23.69));
        smaData.put(getCalendar(i++), new BigDecimal(23.61));
        smaData.put(getCalendar(i++), new BigDecimal(23.51));
        smaData.put(getCalendar(i++), new BigDecimal(23.43));
        smaData.put(getCalendar(i++), new BigDecimal(23.28));
        smaData.put(getCalendar(i++), new BigDecimal(23.13));
        return smaData;
        
    }
    
    private SortedMap<Calendar, BigDecimal> getTestData() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> testData = new TreeMap<Calendar, BigDecimal>();
        testData.put(getCalendar(i++), new BigDecimal(22.27));
        testData.put(getCalendar(i++), new BigDecimal(22.19));
        testData.put(getCalendar(i++), new BigDecimal(22.08));
        testData.put(getCalendar(i++), new BigDecimal(22.17));
        testData.put(getCalendar(i++), new BigDecimal(22.18));
        testData.put(getCalendar(i++), new BigDecimal(22.13));
        testData.put(getCalendar(i++), new BigDecimal(22.23));
        testData.put(getCalendar(i++), new BigDecimal(22.43));
        testData.put(getCalendar(i++), new BigDecimal(22.24));
        testData.put(getCalendar(i++), new BigDecimal(22.29));
        testData.put(getCalendar(i++), new BigDecimal(22.15));
        testData.put(getCalendar(i++), new BigDecimal(22.39));
        testData.put(getCalendar(i++), new BigDecimal(22.38));
        testData.put(getCalendar(i++), new BigDecimal(22.61));
        testData.put(getCalendar(i++), new BigDecimal(23.36));
        testData.put(getCalendar(i++), new BigDecimal(24.05));
        testData.put(getCalendar(i++), new BigDecimal(23.75));
        testData.put(getCalendar(i++), new BigDecimal(23.83));
        testData.put(getCalendar(i++), new BigDecimal(23.95));
        testData.put(getCalendar(i++), new BigDecimal(23.63));
        testData.put(getCalendar(i++), new BigDecimal(23.82));
        testData.put(getCalendar(i++), new BigDecimal(23.87));
        testData.put(getCalendar(i++), new BigDecimal(23.65));
        testData.put(getCalendar(i++), new BigDecimal(23.19));
        testData.put(getCalendar(i++), new BigDecimal(23.10));
        testData.put(getCalendar(i++), new BigDecimal(23.33));
        testData.put(getCalendar(i++), new BigDecimal(22.68));
        testData.put(getCalendar(i++), new BigDecimal(23.10));
        testData.put(getCalendar(i++), new BigDecimal(22.40));
        testData.put(getCalendar(i++), new BigDecimal(22.17));
        return testData;
    }
    
    private Calendar getCalendar(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, i);
        return cal;
    }

}
