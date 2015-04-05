package com.jarias14.tekstratego.service.listener.dao.resource;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jarias14 on 3/15/2015.
 */
@Data
public class YahooQuote {
    @JsonProperty("Symbol")
    private String symbol;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Open")
    private double open;
    @JsonProperty("High")
    private double high;
    @JsonProperty("Low")
    private double low;
    @JsonProperty("Close")
    private double close;
    @JsonProperty("Adj_Close")
    private double adjClose;
    @JsonProperty("Volume")
    private double volume;

}
