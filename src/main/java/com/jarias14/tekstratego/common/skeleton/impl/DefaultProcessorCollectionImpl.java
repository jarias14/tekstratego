package com.jarias14.tekstratego.common.skeleton.impl;

import com.jarias14.tekstratego.common.skeleton.Processor;

import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class DefaultProcessorCollectionImpl<REQUEST> implements Processor<REQUEST> {

    private List<Processor<REQUEST>> processors;

    @Override
    public void process(REQUEST request) {
        processors.stream().forEach(processor -> processor.process(request));
    }

    public void setProcessors(List<Processor<REQUEST>> processors) {
        this.processors = processors;
    }
}
