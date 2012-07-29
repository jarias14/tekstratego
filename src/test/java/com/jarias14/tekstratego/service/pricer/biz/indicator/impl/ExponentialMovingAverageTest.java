package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExponentialMovingAverageTest {

    private SortedMap<Calendar, BigDecimal> testData;
    private ExponentialMovingAverage ema;
    
    @Before
    public void setUp() {
        testData = getTestData();
        ema = new ExponentialMovingAverage();
    }
    
    @Test
    public void test() {
        
        BigDecimal test = new BigDecimal(123.345532);
        test = test.setScale(2, RoundingMode.HALF_EVEN);
        
        
        ema.setPeriod(10);
        Object[] actualValues = ema.calculate(testData, Calendar.getInstance(), Calendar.getInstance()).values().toArray();
        Object[] expectedValues = getExpectedEMA().values().toArray();
        
        for (int i = 0; i < actualValues.length; i++) {
            System.out.println((BigDecimal)expectedValues[i] + " " + (BigDecimal)actualValues[i]);
            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            System.out.println(expected + " " + actual);
            Assert.assertTrue(expected.compareTo(actual) < .01 && expected.compareTo(actual) > -.01);
        }
    }
    
    private SortedMap<Calendar, BigDecimal> getExpectedEMA() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> emaData = new TreeMap<Calendar, BigDecimal>();
        emaData.put(getCalendar(i++), new BigDecimal(22.27));
        emaData.put(getCalendar(i++), new BigDecimal(22.19));
        emaData.put(getCalendar(i++), new BigDecimal(22.08));
        emaData.put(getCalendar(i++), new BigDecimal(22.17));
        emaData.put(getCalendar(i++), new BigDecimal(22.18));
        emaData.put(getCalendar(i++), new BigDecimal(22.13));
        emaData.put(getCalendar(i++), new BigDecimal(22.23));
        emaData.put(getCalendar(i++), new BigDecimal(22.43));
        emaData.put(getCalendar(i++), new BigDecimal(22.24));
        emaData.put(getCalendar(i++), new BigDecimal(22.22));
        emaData.put(getCalendar(i++), new BigDecimal(22.21));
        emaData.put(getCalendar(i++), new BigDecimal(22.24));
        emaData.put(getCalendar(i++), new BigDecimal(22.27));
        emaData.put(getCalendar(i++), new BigDecimal(22.33));
        emaData.put(getCalendar(i++), new BigDecimal(22.52));
        emaData.put(getCalendar(i++), new BigDecimal(22.80));
        emaData.put(getCalendar(i++), new BigDecimal(22.97));
        emaData.put(getCalendar(i++), new BigDecimal(23.13));
        emaData.put(getCalendar(i++), new BigDecimal(23.28));
        emaData.put(getCalendar(i++), new BigDecimal(23.34));
        emaData.put(getCalendar(i++), new BigDecimal(23.43));
        emaData.put(getCalendar(i++), new BigDecimal(23.51));
        emaData.put(getCalendar(i++), new BigDecimal(23.54));
        emaData.put(getCalendar(i++), new BigDecimal(23.47));
        emaData.put(getCalendar(i++), new BigDecimal(23.40));
        emaData.put(getCalendar(i++), new BigDecimal(23.39));
        emaData.put(getCalendar(i++), new BigDecimal(23.26));
        emaData.put(getCalendar(i++), new BigDecimal(23.23));
        emaData.put(getCalendar(i++), new BigDecimal(23.08));
        emaData.put(getCalendar(i++), new BigDecimal(22.92));
        return emaData;
        
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
