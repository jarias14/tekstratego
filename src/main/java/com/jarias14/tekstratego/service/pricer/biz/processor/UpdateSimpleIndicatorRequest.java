package com.jarias14.tekstratego.service.pricer.biz.processor;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointTimableDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by jarias14 on 4/5/2015.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSimpleIndicatorRequest {
    private DataPointTimableDescription requestedIndicator;
    private Set<DataPointCollection> data;


}
