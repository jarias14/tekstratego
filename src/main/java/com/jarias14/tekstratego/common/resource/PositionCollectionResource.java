package com.jarias14.tekstratego.common.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jarias14.tekstratego.service.thinker.model.Position;

public class PositionCollectionResource extends BaseResource {

    private static final long serialVersionUID = 1L;
    
    private Map<String, PositionResource> positions;

    public PositionCollectionResource() {
        
    }
    
    public PositionCollectionResource(Map<String, Position> model) {
        
        this.positions = new HashMap<String, PositionResource>();
        
        for (Entry<String,Position> kv : model.entrySet()) {
            positions.put(kv.getKey(), kv.getValue().toResource());
        }
    }

    public Map<String, PositionResource> getPositions() {
        return positions;
    }

    public void setPositions(Map<String, PositionResource> positions) {
        this.positions = positions;
    }

    public Map<String, Position> toModel() {
        
        Map<String, Position> model = new HashMap<String,Position>();
        
        for (Entry<String, PositionResource> kv : positions.entrySet()) {
            
            model.put(kv.getKey(), new Position(kv.getValue()));
        }
        
        return model;
    }

}
