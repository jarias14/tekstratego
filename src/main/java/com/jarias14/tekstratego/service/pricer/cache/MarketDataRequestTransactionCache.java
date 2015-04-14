package com.jarias14.tekstratego.service.pricer.cache;

import com.jarias14.tekstratego.common.models.DataPoint;
import com.jarias14.tekstratego.common.models.MarketDataRequest;
import com.jarias14.tekstratego.common.skeleton.TransactionCache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

/**
 * Created by jarias14 on 4/14/2015.
 */
public class MarketDataRequestTransactionCache extends BlockingCache implements TransactionCache<MarketDataRequest, DataPoint> {

    public MarketDataRequestTransactionCache(Ehcache cache) throws CacheException {
        super(cache);
    }

    @Override
    public DataPoint getDataPoint(MarketDataRequest marketDataRequest) {

        DataPoint dataPoint = null;

        Element element = super.get(marketDataRequest);

        if (element != null) {
            dataPoint = (DataPoint) element.getObjectValue();
        }

        return dataPoint;
    }

    @Override
    public void putDataPoint(MarketDataRequest marketDataRequest, DataPoint dataPoint) {
        super.put(new Element(marketDataRequest, dataPoint));
    }
}
