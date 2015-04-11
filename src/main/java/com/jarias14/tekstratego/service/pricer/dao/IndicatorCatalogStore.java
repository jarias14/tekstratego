package com.jarias14.tekstratego.service.pricer.dao;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.Stock;
import net.sf.ehcache.Ehcache;

import java.util.Set;

/**
 * Created by jarias14 on 4/4/2015.
 */
public interface IndicatorCatalogStore extends Ehcache {

    public Set<DataPointDescription> getIndicators();
    public Set<DataPointDescription> getIndicatorsByStock(Stock stock);
    public boolean removeIndicator(String dataPointId);
}
