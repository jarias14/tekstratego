package com.jarias14.tekstratego.service.listener.dao.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jarias14 on 3/15/2015.
 */
@Data
public class YahooQuery {


    @JsonProperty("results")
    private YahooResults results;
}
