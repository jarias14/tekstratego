package com.jarias14.tekstratego.common.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 4/4/2015.
 */
@Data
@NoArgsConstructor
public class DataPointCollection<TYPE> extends DataPointDescription {

    private List<DataPoint<TYPE>> dataPoints;

    public DataPointCollection(DataPointIndicator indicator, String symbol, String exchange, TimeUnit timeUnit, Integer timeSize) {
        this.setDetails(new DataPointDetails(indicator, timeUnit, timeSize));
        this.setStock(new Stock(symbol, StockExchange.valueOf(exchange.toUpperCase())));
        this.setDataPoints(new ArrayList<>());
    }

    public DataPointCollection(DataPointIndicator indicator, Stock stock, DataPointSize dataPointSize, int necessaryBars, DataPoint dataPoint) {
        this.setDetails(new DataPointDetails(indicator, dataPointSize, necessaryBars));
        this.setStock(stock);
        this.setDataPoints(Arrays.asList(dataPoint));
    }

}
