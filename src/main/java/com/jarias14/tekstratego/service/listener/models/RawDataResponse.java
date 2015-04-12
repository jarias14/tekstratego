package com.jarias14.tekstratego.service.listener.models;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import lombok.Data;

import java.util.Set;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class RawDataResponse {
    private RawDataRequest rawDataRequest;
    private boolean processable;
    private Set<DataPointCollection> dataPointCollectionSet;
}
