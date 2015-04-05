package com.jarias14.tekstratego.service.listener.models;

import lombok.Data;

/**
 * Created by jarias14 on 4/4/2015.
 */
@Data
public class ListenerRequest {

    private String symbol;
    private RequestType requestType;

    private String startDate;
    private String endDate;
    private String frequencyInSeconds; // one day every X seconds

    public static enum RequestType {
        SUBSCRIBE, UNSUBSCRIBE;
    }

}
