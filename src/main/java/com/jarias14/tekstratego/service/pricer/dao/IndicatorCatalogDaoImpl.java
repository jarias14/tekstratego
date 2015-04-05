package com.jarias14.tekstratego.service.pricer.dao;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.Stock;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class IndicatorCatalogDaoImpl extends BlockingCache implements IndicatorCatalogDao {

    public IndicatorCatalogDaoImpl(Ehcache cache) throws CacheException {
        super(cache); 
    }

    @Override
    public Set<DataPointDescription> getIndicators() {
        return null;
    }

    @Override
    public Set<DataPointDescription> getIndicatorsByStock(Stock stock) {
        return null;
    }

    @Override
    public boolean removeIndicator(DataPointDescription dataPointDescription) {
        return false;
    }
}
