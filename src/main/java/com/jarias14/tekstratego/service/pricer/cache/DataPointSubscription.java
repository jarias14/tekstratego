package com.jarias14.tekstratego.service.pricer.cache;

import com.jarias14.tekstratego.common.models.DataPoint;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * Created by jarias14 on 3/29/2015.
 */
public class DataPointSubscription implements CacheEventListener {

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {

    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {

        DataPoint dataPoint = ((DataPoint)element.getObjectValue());

       // dataPoint.getDetails().getIndicator();


    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {

    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {

    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {

    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {

    }

    @Override
    public void dispose() {

    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
