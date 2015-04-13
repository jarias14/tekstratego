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
public class DataPoint<VALUE_TYPE> {

    private long time;
    private VALUE_TYPE value;
}
