package com.jarias14.tekstratego.common.skeleton;

/**
 * Created by jarias14 on 4/12/2015.
 */
public interface DataAccessObject<REQUEST, RESPONSE> {

    public RESPONSE request(REQUEST request);

}
