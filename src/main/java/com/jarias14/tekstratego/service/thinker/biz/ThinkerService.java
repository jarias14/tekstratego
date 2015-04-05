package com.jarias14.tekstratego.service.thinker.biz;

import com.jarias14.tekstratego.service.thinker.model.ComparableRange;

import java.util.Map;

public interface ThinkerService {

    public String createNode(String indicatorName, String type, Map<ComparableRange, String> decisionNodes);

}
