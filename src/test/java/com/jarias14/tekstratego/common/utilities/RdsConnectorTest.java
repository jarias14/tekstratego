package com.jarias14.tekstratego.common.utilities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.SortedMap;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;

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
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 3, 5, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2010, 6, 10, 23, 59, 59);
        
        //this thing goes up to but not including the last one
        SortedMap<Calendar, BigDecimal> prices = rds.getPrices(exchange, symbol, sizeOfBar, priceOfBar, startDate, endDate);
        
        Calendar date = Calendar.getInstance();
        date.set(2010, 6, 9, 0, 0, 0);
        date.set(Calendar.MILLISECOND, 0);

        Assert.assertEquals(68, prices.size());
        Assert.assertEquals(new BigDecimal(24.27), prices.get(date));
        Assert.assertEquals(new BigDecimal(24.27), prices.get(prices.lastKey()));
        
    }
}
