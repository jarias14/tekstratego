package com.jarias14.tekstratego.service.manager.rest.models;

import com.jarias14.tekstratego.common.models.Identifiable;
import com.jarias14.tekstratego.common.models.Stock;
import lombok.Data;

import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class AccountRequestResource extends Identifiable {
    private List<Stock> stocks;
    private List<String> buyStrategyIds;
    private List<String> sellStrategyIds;
}
