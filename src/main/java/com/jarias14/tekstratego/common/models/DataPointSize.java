package com.jarias14.tekstratego.common.models;

import lombok.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by jarias14 on 3/22/2015.
 */

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPointSize {

    private TimeUnit unit;
    private Integer quantity;
}
