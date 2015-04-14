package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by jarias14 on 3/29/2015.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DataPointDetails {

    private Map<DataPointIndicatorParameter, Integer> parameters;
    private DataPointIndicator indicator;
    private DataPointSize size;

    public DataPointDetails(DataPointIndicator indicator, DataPointSize size) {
        this.indicator = indicator;
        this.size = size;
    }

}
