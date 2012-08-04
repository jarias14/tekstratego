package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.jarias14.tekstratego.common.resource.IndicatorResource;
import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.ExponentialMovingAverage;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.Price;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.StochasticOscillatorD;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.StochasticOscillatorK;

/*
 * http://www.oodesign.com/factory-pattern.html
 */
public class IndicatorFactory {
    
    private static final HashMap<String, Class<? extends Indicator>> INDICATOR_MAP = new HashMap<String, Class<? extends Indicator>>();
    
    static {
        INDICATOR_MAP.put("price", (Class<? extends Indicator>) Price.class);
        INDICATOR_MAP.put("simple_moving_average", (Class<? extends Indicator>) SimpleMovingAverage.class);
        INDICATOR_MAP.put("exponential_moving_average", (Class<? extends Indicator>) ExponentialMovingAverage.class);
        INDICATOR_MAP.put("stochastic_oscillator_d", (Class<? extends Indicator>) StochasticOscillatorD.class);
        INDICATOR_MAP.put("stochastic_oscillator_k", (Class<? extends Indicator>) StochasticOscillatorK.class);
    }

    public static Indicator getIndicator(IndicatorResource resource) {
        Indicator indicatorObject = null;

        try {
            // get the class
            Class<? extends Indicator> indicatorClass = (Class<? extends Indicator>)INDICATOR_MAP.get(resource.getType().toLowerCase());
            // get the constructor for the class
            Constructor<? extends Indicator> indicatorConstructor = indicatorClass.getDeclaredConstructor(new Class[] { });
            // get the object through the constructor 
            indicatorObject = (Indicator) indicatorConstructor.newInstance();
            // build object through builder method
            indicatorObject.fromResource(resource);
            
        } catch (Exception e) {

        }
        
        return indicatorObject;
    }
}
