package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

public class StochasticOscillatorKTest {
/*
    private StochasticOscillatorK sma;
    
    @Before
    public void setUp() {
        sma = new StochasticOscillatorK();
    }
    
    @Test
    public void test() {
        
        BigDecimal test = new BigDecimal(123.345532);
        test = test.setScale(2, RoundingMode.HALF_EVEN);
        
        
        sma.setPeriod(14);
        Object[] actualValues = sma.calculate(getLows(), getHighs(), getCloses(), Calendar.getInstance(), Calendar.getInstance()).values().toArray();
        Object[] expectedValues = getExpectedResults().values().toArray();
        
        for (int i = 0; i < actualValues.length; i++) {

            BigDecimal expected = ((BigDecimal)expectedValues[i]).setScale(4, RoundingMode.HALF_EVEN);
            BigDecimal actual = ((BigDecimal)actualValues[i]).setScale(4, RoundingMode.HALF_EVEN);
            
            Assert.assertTrue(expected.equals(actual));
        }
        
        Assert.assertEquals(expectedValues.length, actualValues.length);
    }
    

    private SortedMap<Calendar, BigDecimal> getExpectedResults() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> smaData = new TreeMap<Calendar, BigDecimal>();
        smaData.put(getCalendar(i++), new BigDecimal(70.4382202470155));
        smaData.put(getCalendar(i++), new BigDecimal(67.6089091003048));
        smaData.put(getCalendar(i++), new BigDecimal(89.2021084181696));
        smaData.put(getCalendar(i++), new BigDecimal(65.8105524262313));
        smaData.put(getCalendar(i++), new BigDecimal(81.7477132964705));
        smaData.put(getCalendar(i++), new BigDecimal(64.5237972197819));
        smaData.put(getCalendar(i++), new BigDecimal(74.5297763406087));
        smaData.put(getCalendar(i++), new BigDecimal(98.5814423191156));
        smaData.put(getCalendar(i++), new BigDecimal(70.1045325659149));
        smaData.put(getCalendar(i++), new BigDecimal(73.0560907339419));
        smaData.put(getCalendar(i++), new BigDecimal(73.4177905412773));
        smaData.put(getCalendar(i++), new BigDecimal(61.2312902873376));
        smaData.put(getCalendar(i++), new BigDecimal(60.9562710235466));
        smaData.put(getCalendar(i++), new BigDecimal(40.3861022518606));
        smaData.put(getCalendar(i++), new BigDecimal(40.3861022518606));
        smaData.put(getCalendar(i++), new BigDecimal(66.8285493379727));
        smaData.put(getCalendar(i++), new BigDecimal(56.7314197351892));
        return smaData;
        
    }
    
    private SortedMap<Calendar, BigDecimal> getHighs() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> testData = new TreeMap<Calendar, BigDecimal>();
        testData.put(getCalendar(i++), new BigDecimal(127.0090));
        testData.put(getCalendar(i++), new BigDecimal(127.6159));
        testData.put(getCalendar(i++), new BigDecimal(126.5911));
        testData.put(getCalendar(i++), new BigDecimal(127.3472));
        testData.put(getCalendar(i++), new BigDecimal(128.1730));
        testData.put(getCalendar(i++), new BigDecimal(128.4317));
        testData.put(getCalendar(i++), new BigDecimal(127.3671));
        testData.put(getCalendar(i++), new BigDecimal(126.4220));
        testData.put(getCalendar(i++), new BigDecimal(126.8995));
        testData.put(getCalendar(i++), new BigDecimal(126.8498));
        testData.put(getCalendar(i++), new BigDecimal(125.6460));
        testData.put(getCalendar(i++), new BigDecimal(125.7156));
        testData.put(getCalendar(i++), new BigDecimal(127.1582));
        testData.put(getCalendar(i++), new BigDecimal(127.7154));
        testData.put(getCalendar(i++), new BigDecimal(127.6855));
        testData.put(getCalendar(i++), new BigDecimal(128.2228));
        testData.put(getCalendar(i++), new BigDecimal(128.2725));
        testData.put(getCalendar(i++), new BigDecimal(128.0934));
        testData.put(getCalendar(i++), new BigDecimal(128.2725));
        testData.put(getCalendar(i++), new BigDecimal(127.7353));
        testData.put(getCalendar(i++), new BigDecimal(128.7700));
        testData.put(getCalendar(i++), new BigDecimal(129.2873));
        testData.put(getCalendar(i++), new BigDecimal(130.0633));
        testData.put(getCalendar(i++), new BigDecimal(129.1182));
        testData.put(getCalendar(i++), new BigDecimal(129.2873));
        testData.put(getCalendar(i++), new BigDecimal(128.4715));
        testData.put(getCalendar(i++), new BigDecimal(128.0934));
        testData.put(getCalendar(i++), new BigDecimal(128.6506));
        testData.put(getCalendar(i++), new BigDecimal(129.1381));
        testData.put(getCalendar(i++), new BigDecimal(128.6406));
        return testData;
    }
    
    private SortedMap<Calendar, BigDecimal> getLows() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> testData = new TreeMap<Calendar, BigDecimal>();
        testData.put(getCalendar(i++), new BigDecimal(125.3574));
        testData.put(getCalendar(i++), new BigDecimal(126.1633));
        testData.put(getCalendar(i++), new BigDecimal(124.9296));
        testData.put(getCalendar(i++), new BigDecimal(126.0937));
        testData.put(getCalendar(i++), new BigDecimal(126.8199));
        testData.put(getCalendar(i++), new BigDecimal(126.4817));
        testData.put(getCalendar(i++), new BigDecimal(126.0340));
        testData.put(getCalendar(i++), new BigDecimal(124.8301));
        testData.put(getCalendar(i++), new BigDecimal(126.3921));
        testData.put(getCalendar(i++), new BigDecimal(125.7156));
        testData.put(getCalendar(i++), new BigDecimal(124.5615));
        testData.put(getCalendar(i++), new BigDecimal(124.5715));
        testData.put(getCalendar(i++), new BigDecimal(125.0689));
        testData.put(getCalendar(i++), new BigDecimal(126.8597));
        testData.put(getCalendar(i++), new BigDecimal(126.6309));
        testData.put(getCalendar(i++), new BigDecimal(126.8001));
        testData.put(getCalendar(i++), new BigDecimal(126.7105));
        testData.put(getCalendar(i++), new BigDecimal(126.8001));
        testData.put(getCalendar(i++), new BigDecimal(126.1335));
        testData.put(getCalendar(i++), new BigDecimal(125.9245));
        testData.put(getCalendar(i++), new BigDecimal(126.9891));
        testData.put(getCalendar(i++), new BigDecimal(127.8148));
        testData.put(getCalendar(i++), new BigDecimal(128.4715));
        testData.put(getCalendar(i++), new BigDecimal(128.0641));
        testData.put(getCalendar(i++), new BigDecimal(127.6059));
        testData.put(getCalendar(i++), new BigDecimal(127.5960));
        testData.put(getCalendar(i++), new BigDecimal(126.9990));
        testData.put(getCalendar(i++), new BigDecimal(126.8995));
        testData.put(getCalendar(i++), new BigDecimal(127.4865));
        testData.put(getCalendar(i++), new BigDecimal(127.3970));
        return testData;
    }
    
    private SortedMap<Calendar, BigDecimal> getCloses() {
        int i = 0;
        SortedMap<Calendar, BigDecimal> testData = new TreeMap<Calendar, BigDecimal>();
        testData.put(getCalendar(i++), new BigDecimal(127.2876));
        testData.put(getCalendar(i++), new BigDecimal(127.1781));
        testData.put(getCalendar(i++), new BigDecimal(128.0138));
        testData.put(getCalendar(i++), new BigDecimal(127.1085));
        testData.put(getCalendar(i++), new BigDecimal(127.7253));
        testData.put(getCalendar(i++), new BigDecimal(127.0587));
        testData.put(getCalendar(i++), new BigDecimal(127.3273));
        testData.put(getCalendar(i++), new BigDecimal(128.7103));
        testData.put(getCalendar(i++), new BigDecimal(127.8745));
        testData.put(getCalendar(i++), new BigDecimal(128.5809));
        testData.put(getCalendar(i++), new BigDecimal(128.6008));
        testData.put(getCalendar(i++), new BigDecimal(127.9342));
        testData.put(getCalendar(i++), new BigDecimal(128.1133));
        testData.put(getCalendar(i++), new BigDecimal(127.5960));
        testData.put(getCalendar(i++), new BigDecimal(127.5960));
        testData.put(getCalendar(i++), new BigDecimal(128.6904));
        testData.put(getCalendar(i++), new BigDecimal(128.2725));
        return testData;
    }
    
    private Calendar getCalendar(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, i);
        return cal;
    }
*/
}
