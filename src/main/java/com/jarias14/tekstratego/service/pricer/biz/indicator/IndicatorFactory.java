package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;
import com.jarias14.tekstratego.service.pricer.rest.resource.IndicatorResource;

/*
 * http://www.oodesign.com/factory-pattern.html
 */
public class IndicatorFactory {
    
    private static final HashMap<String, Class<? extends Indicator>> INDICATOR_MAP = new HashMap<String, Class<? extends Indicator>>();
    
    static {
        INDICATOR_MAP.put("simpleMovingAverage", (Class<? extends Indicator>) SimpleMovingAverage.class);
    }

    public static Indicator getIndicator(IndicatorResource resource) throws Exception {
        Class<? extends Indicator> indicatorClass = (Class<? extends Indicator>)INDICATOR_MAP.get(resource.getIndicatorType());
        Constructor<? extends Indicator> indicatorConstructor = indicatorClass.getDeclaredConstructor(new Class[] { IndicatorResource.class });
        Indicator indicatorObject = (Indicator) indicatorConstructor.newInstance(resource);
        return indicatorObject;
    }
}
