package com.jarias14.tekstratego.common.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jarias14 on 4/4/2015.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataPointDescription extends Identifiable {

    private Stock stock;
    private DataPointDetails details;

}
