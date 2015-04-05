package com.jarias14.tekstratego.service.listener.dao.resource;

import lombok.Data;

/**
 * Created by jarias14 on 3/28/2015.
 */
@Data
public class SubscriptionRequest {
    private String stock;
    private Integer dataPointSize;
    private String dataPointSizeUnit;
    private String indicator;

}
