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
            

            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            
            Assert.assertTrue(expected.equals(actual));
            
            /*
            System.out.println((BigDecimal)expectedValues[i] + " " + (BigDecimal)actualValues[i]);
            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            System.out.println(expected + " " + actual);
            System.out.println(expected.compareTo(actual));
            Assert.assertTrue(expected.doubleValue() - actual.doubleValue() <= .011 && expected.doubleValue() - actual.doubleValue() >= -.011);
            */
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
        smaData.put(getCalendar(i++), new BigDecimal(22.2248));
        smaData.put(getCalendar(i++), new BigDecimal(22.2128));
        smaData.put(getCalendar(i++), new BigDecimal(22.2327));
        smaData.put(getCalendar(i++), new BigDecimal(22.2624));
        smaData.put(getCalendar(i++), new BigDecimal(22.3061));
        smaData.put(getCalendar(i++), new BigDecimal(22.4232));
        smaData.put(getCalendar(i++), new BigDecimal(22.6150));
        smaData.put(getCalendar(i++), new BigDecimal(22.7669));
        smaData.put(getCalendar(i++), new BigDecimal(22.9069));
        smaData.put(getCalendar(i++), new BigDecimal(23.0777));
        smaData.put(getCalendar(i++), new BigDecimal(23.2118));
        smaData.put(getCalendar(i++), new BigDecimal(23.3786));
        smaData.put(getCalendar(i++), new BigDecimal(23.5266));
        smaData.put(getCalendar(i++), new BigDecimal(23.6538));
        smaData.put(getCalendar(i++), new BigDecimal(23.7114));
        smaData.put(getCalendar(i++), new BigDecimal(23.6856));
        smaData.put(getCalendar(i++), new BigDecimal(23.6130));
        smaData.put(getCalendar(i++), new BigDecimal(23.5057));
        smaData.put(getCalendar(i++), new BigDecimal(23.4323));
        smaData.put(getCalendar(i++), new BigDecimal(23.2773));
        smaData.put(getCalendar(i++), new BigDecimal(23.1312));
        return smaData;
        
    }
    
    private SortedMap<Calendar, BigDecimal> getTestData() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> testData = new TreeMap<Calendar, BigDecimal>();
        testData.put(getCalendar(i++), new BigDecimal(22.2734));
        testData.put(getCalendar(i++), new BigDecimal(22.1940));
        testData.put(getCalendar(i++), new BigDecimal(22.0847));
        testData.put(getCalendar(i++), new BigDecimal(22.1741));
        testData.put(getCalendar(i++), new BigDecimal(22.1840));
        testData.put(getCalendar(i++), new BigDecimal(22.1344));
        testData.put(getCalendar(i++), new BigDecimal(22.2337));
        testData.put(getCalendar(i++), new BigDecimal(22.4323));
        testData.put(getCalendar(i++), new BigDecimal(22.2436));
        testData.put(getCalendar(i++), new BigDecimal(22.2933));
        testData.put(getCalendar(i++), new BigDecimal(22.1542));
        testData.put(getCalendar(i++), new BigDecimal(22.3926));
        testData.put(getCalendar(i++), new BigDecimal(22.3816));
        testData.put(getCalendar(i++), new BigDecimal(22.6109));
        testData.put(getCalendar(i++), new BigDecimal(23.3558));
        testData.put(getCalendar(i++), new BigDecimal(24.0519));
        testData.put(getCalendar(i++), new BigDecimal(23.7530));
        testData.put(getCalendar(i++), new BigDecimal(23.8324));
        testData.put(getCalendar(i++), new BigDecimal(23.9516));
        testData.put(getCalendar(i++), new BigDecimal(23.6338));
        testData.put(getCalendar(i++), new BigDecimal(23.8225));
        testData.put(getCalendar(i++), new BigDecimal(23.8722));
        testData.put(getCalendar(i++), new BigDecimal(23.6537));
        testData.put(getCalendar(i++), new BigDecimal(23.1870));
        testData.put(getCalendar(i++), new BigDecimal(23.0976));
        testData.put(getCalendar(i++), new BigDecimal(23.3260));
        testData.put(getCalendar(i++), new BigDecimal(22.6805));
        testData.put(getCalendar(i++), new BigDecimal(23.0976));
        testData.put(getCalendar(i++), new BigDecimal(22.4025));
        testData.put(getCalendar(i++), new BigDecimal(22.1725));
        return testData;
    }
    
    private Calendar getCalendar(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, i);
        return cal;
    }

}
