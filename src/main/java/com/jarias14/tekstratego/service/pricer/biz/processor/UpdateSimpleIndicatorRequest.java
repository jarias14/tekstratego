package com.jarias14.tekstratego.service.pricer.biz.processor;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.DataPointTimableDescription;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by jarias14 on 4/5/2015.
 */
@Data
@AllArgsConstructor
public class UpdateSimpleIndicatorRequest {
    private DataPointTimableDescription requestedIndicator;
    private Map<DataPointIndicator, DataPointCollection> data;
}
