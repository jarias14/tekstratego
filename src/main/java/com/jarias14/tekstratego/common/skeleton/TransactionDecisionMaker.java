package com.jarias14.tekstratego.common.skeleton;

/**
 * Created by jarias14 on 4/4/2015.
 */
public interface TransactionDecisionMaker<REQUEST> {

    public static String DEFAULT = "DEFAULT";

    public String retrieveTransactionManagerId(REQUEST request);
}
