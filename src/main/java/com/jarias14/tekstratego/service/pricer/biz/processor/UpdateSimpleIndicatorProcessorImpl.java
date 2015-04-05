package com.jarias14.tekstratego.service.pricer.biz.processor;

import com.jarias14.tekstratego.common.models.DataPointCollection;
import com.jarias14.tekstratego.common.models.DataPointIndicator;
import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.common.skeleton.Processor;

import java.util.Map;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class UpdateSimpleIndicatorProcessorImpl implements Processor<UpdateSimpleIndicatorRequest> {



    @Override
    public void process(UpdateSimpleIndicatorRequest updateSimpleIndicatorRequest) {

        long time = updateSimpleIndicatorRequest.getRequestedIndicator().getTime();
        Stock stock = updateSimpleIndicatorRequest.getRequestedIndicator().getStock();
        DataPointIndicator indicator = updateSimpleIndicatorRequest.getRequestedIndicator().getDetails().getIndicator();
        Map<DataPointIndicator, DataPointCollection> rawDataPoints = updateSimpleIndicatorRequest.getData();

        


        return;
    }
}
