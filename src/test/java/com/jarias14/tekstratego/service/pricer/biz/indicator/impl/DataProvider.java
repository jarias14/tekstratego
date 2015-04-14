package com.jarias14.tekstratego.service.pricer.biz.indicator.impl;

import com.jarias14.tekstratego.common.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 4/11/2015.
 */
public class DataProvider {


    public static DataPointCollection getDataPointCollectionWithoutPrices(DataPointIndicator dataPointIndicator) {

        DataPointSize dataPointSize = new DataPointSize();
        dataPointSize.setQuantity(1);
        dataPointSize.setUnit(TimeUnit.DAYS);

        DataPointDetails details = new DataPointDetails();
        details.setSize(dataPointSize);
        details.setIndicator(dataPointIndicator);


        DataPointCollection dataPointCollection = new DataPointCollection();
        dataPointCollection.setDetails(details);
        dataPointCollection.setStock(new Stock("TEST", StockExchange.NYSE));
        dataPointCollection.setId("BLAH");

        return dataPointCollection;
    }


    public static DataPointTimableDescription getIndicator(DataPointIndicator dataPointIndicator) {
        DataPointSize dataPointSize = new DataPointSize();
        dataPointSize.setQuantity(1);
        dataPointSize.setUnit(TimeUnit.DAYS);

        DataPointDetails details = new DataPointDetails();
        details.setSize(dataPointSize);
        details.setIndicator(dataPointIndicator);
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


    public static DataPointCollection getHighs() {

        DataPointCollection dataPointCollection = getDataPointCollectionWithoutPrices(DataPointIndicator.RAW_HIGH);
        dataPointCollection.setDataPoints(new ArrayList<>());

        int i = 0;
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.0090));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.6159));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.5911));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.3472));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.1730));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.4317));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.3671));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.4220));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8995));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8498));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.6460));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.7156));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.1582));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.7154));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.6855));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.2228));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.2725));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.0934));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.2725));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.7353));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.7700));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 129.2873));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 130.0633));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 129.1182));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 129.2873));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.4715));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.0934));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.6506));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 129.1381));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.6406));

        return dataPointCollection;
    }

    public static DataPointCollection getLows() {

        DataPointCollection dataPointCollection = getDataPointCollectionWithoutPrices(DataPointIndicator.RAW_LOW);
        dataPointCollection.setDataPoints(new ArrayList<>());

        int i = 0;
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.3574));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.1633));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 124.9296));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.0937));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8199));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.4817));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.0340));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 124.8301));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.3921));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.7156));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 124.5615));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 124.5715));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.0689));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8597));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.6309));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8001));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.7105));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8001));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.1335));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 125.9245));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.9891));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.8148));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.4715));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.0641));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.6059));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.5960));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.9990));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 126.8995));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.4865));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.3970));

        return dataPointCollection;
    }

    public static DataPointCollection getCloses() {

        DataPointCollection dataPointCollection = getDataPointCollectionWithoutPrices(DataPointIndicator.RAW_CLOSE);
        dataPointCollection.setDataPoints(new ArrayList<>());

        int i = 0;
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, new Double(0)));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.2876));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.1781));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.0138));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.1085));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.7253));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.0587));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.3273));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.7103));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.8745));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.5809));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.6008));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.9342));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.1133));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.5960));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 127.5960));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.6904));
        dataPointCollection.getDataPoints().add(new DataPoint(i++, 128.2725));

        return dataPointCollection;
    }

}
