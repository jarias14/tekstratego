package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 3/29/2015.
 */

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DataPointDetails {

    private DataPointIndicator indicator;
    private DataPointSize size;

    private int necessaryDataPointBars;

    public DataPointDetails(DataPointIndicator indicator, TimeUnit timeUnit, Integer size) {
        this.indicator = indicator;
        this.size = new DataPointSize(timeUnit, size);
    }

}
