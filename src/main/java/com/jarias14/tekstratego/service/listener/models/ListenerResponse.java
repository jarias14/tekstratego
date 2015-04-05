package com.jarias14.tekstratego.service.listener.models;

import com.jarias14.tekstratego.common.models.DataPointDetails;
import lombok.Data;

/**
 * Created by jarias14 on 4/4/2015.
 */
@Data
public class ListenerResponse {
    private String stockSymbol;
    private String stockExchange;
    private DataPointDetails subscriptionDetails;
    private Object data;
}
