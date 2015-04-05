package com.jarias14.tekstratego.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 3/22/2015.
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataPoint<VALUE_TYPE> implements Comparable<DataPoint> {

    private long time;
    private VALUE_TYPE value;

    @Override
    public int compareTo(DataPoint o) {
        return this.time > o.getTime() ? 1 : this.time < o.getTime() ? -1 : 0;
    }
}
