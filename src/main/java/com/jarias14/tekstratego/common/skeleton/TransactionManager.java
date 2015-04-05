package com.jarias14.tekstratego.common.skeleton;

/**
 * Created by jarias14 on 4/4/2015.
 */
public interface TransactionManager<REQUEST, RESPONSE> {

    public RESPONSE process (REQUEST request);

}
