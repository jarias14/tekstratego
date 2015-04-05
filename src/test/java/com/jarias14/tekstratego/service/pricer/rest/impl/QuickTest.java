package com.jarias14.tekstratego.service.pricer.rest.impl;

import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.DataPointSize;
import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class QuickTest {

    @Test
    public void test() {

        DataPointDetails details = new DataPointDetails();

        DataPointIndicator indicator = DataPointIndicator.CLOSE;
        DataPointSize size = new DataPointSize();
        Integer sizeInt = 5;
        TimeUnit unit = TimeUnit.HOURS;
        size.setQuantity(sizeInt);
        size.setUnit(unit);

        details.setIndicator(indicator);
        details.setSize(size);


        DataPointDetails details2 = new DataPointDetails();

        DataPointIndicator indicator2 = DataPointIndicator.CLOSE;
        DataPointSize size2 = new DataPointSize();
        Integer sizeInt2 = 5;
        TimeUnit unit2 = TimeUnit.HOURS;
        size2.setQuantity(sizeInt2);
        size2.setUnit(unit2);

        details2.setIndicator(indicator2);
        details2.setSize(size2);

        Assert.assertTrue(details.equals(details2));




    }
}


