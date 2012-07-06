package com.jarias14.tekstratego.common.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RdsConnectorTest {
    
    @Resource(name="tekRdsConnector")
    private RdsConnector rds;

    @Test
    public void testGetPrices() throws ParseException {
        
        String exchange = "NYSE";
        String symbol = "MSFT";
        SizeOfBarsEnum sizeOfBar = SizeOfBarsEnum.ONE_DAY;
        PriceOfBarsEnum priceOfBar = PriceOfBarsEnum.CLOSE;
        Date startDate = DateUtility.math(new Date(), TimeUnit.DAYS, 1, -730);
        int numberOfBars = 20;
        
        
        SortedMap<Date, Double> prices = rds.getPrices(exchange, symbol, sizeOfBar, priceOfBar, startDate, numberOfBars);
        
        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_FORMAT);
        
        Date date = formatter.parse("2010-07-09");
        
        Assert.assertEquals(24.27, prices.get(date));
        
    }
    
    @Test
    public void testGetMarketDates() throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_FORMAT);
        Date date = formatter.parse("2010-07-09");
        SizeOfBarsEnum sizeOfBars = SizeOfBarsEnum.ONE_DAY;
        int numberOfBars = 1;
        
        SortedSet<Date> actualDates = rds.getMarketDates(date, numberOfBars, sizeOfBars);
        
        Assert.assertEquals(2, actualDates.size());
        Assert.assertEquals(formatter.parse("2010-07-12"), actualDates.last());
    }

}
