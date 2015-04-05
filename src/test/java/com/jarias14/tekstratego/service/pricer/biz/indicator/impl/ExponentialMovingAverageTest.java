package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

public class ExponentialMovingAverageTest {
/*
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

            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(2, RoundingMode.HALF_EVEN);
            
            Assert.assertTrue(expected.equals(actual));
            
            /*
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
*/
}
