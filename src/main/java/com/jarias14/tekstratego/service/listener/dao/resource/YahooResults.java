package com.jarias14.tekstratego.service.listener.dao.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jarias14 on 3/15/2015.
 */
@Data
public class YahooResults {

    @JsonProperty("quote")
    private List<YahooQuote> quote;
}
