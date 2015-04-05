package com.jarias14.tekstratego.service.thinker.model;

import lombok.Data;

/**
 * Created by jarias14 on 3/28/2015.
 */
@Data
public class ComparableRange<TYPE extends Comparable> {

    private TYPE start;
    private TYPE end;

}
