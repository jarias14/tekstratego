package com.jarias14.tekstratego.common.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;

import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;

public class RdsConnectorTest {

    @Test
    public void test() throws ParseException {
        RdsConnector rds = new RdsConnector();
        
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
        
        rds.close();
    }

}
