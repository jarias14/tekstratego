package com.jarias14.tekstratego.service.listener.dao.resource;

import com.jarias14.tekstratego.common.models.DataPointSize;
import com.jarias14.tekstratego.common.models.Stock;
import lombok.Data;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class RawDataRequest {
    private Stock stock;
    private DataPointSize dataPointSize;
    private long startTime;
    private long endTime;

}
