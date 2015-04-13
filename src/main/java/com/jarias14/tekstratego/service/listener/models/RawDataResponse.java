package com.jarias14.tekstratego.service.listener.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jarias14 on 4/12/2015.
 */
@AllArgsConstructor
@Data
public class RawDataResponse {
    private RawDataRequest rawDataRequest;
    private Boolean accepted;
}
