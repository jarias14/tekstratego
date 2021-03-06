package com.jarias14.tekstratego.common.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class DataPointCollection extends DataPointDescription {

    private List<DataPoint> dataPoints;

    public DataPointCollection(DataPointIndicator indicator, String symbol, String exchange, TimeUnit timeUnit, Integer timeSize) {
        this.setDetails(new DataPointDetails(indicator, new DataPointSize(timeUnit, timeSize)));
        this.setStock(new Stock(symbol, StockExchange.valueOf(exchange.toUpperCase())));
        this.setDataPoints(new ArrayList<>());
    }

    public DataPointCollection(DataPointIndicator indicator, Stock stock, DataPointSize dataPointSize, DataPoint dataPoint) {
        this.setDetails(new DataPointDetails(indicator, dataPointSize));
        this.setStock(stock);
        this.setDataPoints(Arrays.asList(dataPoint));
    }

}
