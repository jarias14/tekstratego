package com.jarias14.tekstratego.service.pricer.rest.resource;

import java.util.Date;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlRootElement;

import com.jarias14.tekstratego.service.thinker.rest.resource.BaseResource;

@XmlRootElement(name = "indicatorValuesResource")
@XmlSeeAlso(BaseResource.class)
public class IndicatorValuesResource extends BaseResource {
    
    private static final long serialVersionUID = -5221140640190751254L;
    
    private SortedMap<Date, Double> values;
    
    public IndicatorValuesResource() {
        super();
    }

    public IndicatorValuesResource(SortedMap<Date, Double> values) {
        this();
        this.values = values;
    }

    public SortedMap<Date, Double> getValues() {
        return values;
    }

    public void setValues(SortedMap<Date, Double> values) {
        this.values = values;
    }

}
