package com.jarias14.tekstratego.service.pricer.biz.indicator;

import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

public abstract class IndicatorBase implements Indicator {
    
    private static final long serialVersionUID = -3455826973698151284L;
    
    private String id;
    private SizeOfBarsEnum sizeOfBars;
    private PriceOfBarsEnum priceOfBars;

    private String type;
    
    public IndicatorBase() {
        
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public SizeOfBarsEnum getSizeOfBars() {
        return sizeOfBars;
    }
    
    public void setSizeOfBars(SizeOfBarsEnum sizeOfBars) {
        this.sizeOfBars = sizeOfBars;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public PriceOfBarsEnum getPriceOfBars() {
        return priceOfBars;
    }

    public void setPriceOfBars(PriceOfBarsEnum priceOfBars) {
        this.priceOfBars = priceOfBars;
    }

    @Override
    public IndicatorResource toResource() {
        
        IndicatorResource resource = new IndicatorResource();
        
        resource.setId(this.getId());
        resource.setType(this.type);
        resource.setSizeOfBars(this.getSizeOfBars().name());
        resource.setPriceOfBars(this.priceOfBars.name());
        
        return resource;
    }
    
    public void fromResource(IndicatorResource resource) {
        
        this.id = resource.getId();
        this.type = resource.getType();
        this.sizeOfBars = SizeOfBarsEnum.valueOf(resource.getSizeOfBars().toUpperCase());
        this.priceOfBars = PriceOfBarsEnum.valueOf(resource.getPriceOfBars().toUpperCase());
    }
    
}
