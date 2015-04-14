package com.jarias14.tekstratego.common.cache;

import com.jarias14.tekstratego.common.models.DataPointDetails;
import com.jarias14.tekstratego.common.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jarias14 on 4/4/2015.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class DataStoreKey {
    private Stock stock;
    private long time;
    private DataPointDetails dataPointDetails;
}
