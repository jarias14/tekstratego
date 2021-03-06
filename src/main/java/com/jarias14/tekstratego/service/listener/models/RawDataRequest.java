package com.jarias14.tekstratego.service.listener.models;

import com.jarias14.tekstratego.common.models.DataPointSize;
import com.jarias14.tekstratego.common.models.Identifiable;
import com.jarias14.tekstratego.common.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/12/2015.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class RawDataRequest extends Identifiable {
    private Stock stock;
    private DataPointSize dataPointSize;
    private long startTime;
    private long endTime;

}
