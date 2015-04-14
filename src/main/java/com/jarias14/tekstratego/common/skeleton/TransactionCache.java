package com.jarias14.tekstratego.common.skeleton;

/**
 * Created by jarias14 on 4/14/2015.
 */
public interface TransactionCache<REQUEST, RESPONSE> {

    public RESPONSE getDataPoint(REQUEST request);

    public void putDataPoint(REQUEST request, RESPONSE response);

}
