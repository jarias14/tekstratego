package com.jarias14.tekstratego.service.manager.models;

import com.jarias14.tekstratego.common.models.Identifiable;
import com.jarias14.tekstratego.common.models.Position;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarias14 on 4/12/2015.
 */
@Data
public class ManagedAccount extends Identifiable {
    private List<ManagedAccountStrategy> strategies;
    private List<Trade> executedTrades = new ArrayList<>();
    private List<Trade> abandonedTrades = new ArrayList<>();
    private List<Trade> potentialTrades = new ArrayList<>();
    private List<Position> positions = new ArrayList<>();
    private boolean simulated;
}
