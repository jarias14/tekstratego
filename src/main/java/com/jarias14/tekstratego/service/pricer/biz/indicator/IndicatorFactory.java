package com.jarias14.tekstratego.service.pricer.biz.indicator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.jarias14.tekstratego.service.pricer.biz.indicator.Indicator;
import com.jarias14.tekstratego.service.pricer.biz.indicator.impl.SimpleMovingAverage;

/*
 * http://www.oodesign.com/factory-pattern.html
 */
@SuppressWarnings("unchecked")
public class IndicatorFactory {
    
    private static final HashMap<String, Class<? extends Indicator>> INDICATOR_MAP = new HashMap<String, Class<? extends Indicator>>();
    
    static {
        INDICATOR_MAP.put("simpleMovingAverage", (Class<? extends Indicator>) SimpleMovingAverage.class);
    }

    public static IndicatorBase getIndicator(String indicatorType, HashMap<String, Object> fields) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class<? extends Indicator> indicatorClass = (Class<? extends Indicator>)INDICATOR_MAP.get(indicatorType);
        Constructor<? extends Indicator> indicatorConstructor = indicatorClass.getDeclaredConstructor(new Class[] { HashMap.class });
        IndicatorBase indicatorObject = (IndicatorBase) indicatorConstructor.newInstance(fields);
        return indicatorObject;
    }
}
