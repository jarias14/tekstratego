package com.jarias14.tekstratego.service.pricer.dao;

import com.jarias14.tekstratego.common.models.DataPointDescription;
import com.jarias14.tekstratego.common.models.Stock;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.BlockingCache;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class IndicatorCatalogDaoImpl extends BlockingCache implements IndicatorCatalogStore {

    public IndicatorCatalogDaoImpl(Ehcache cache) throws CacheException {
        super(cache);
    }

    @Override
    public Set<DataPointDescription> getIndicators() {

        return getDataPointDescriptions(getCache().createQuery().execute());
    }

    @Override
    public Set<DataPointDescription> getIndicatorsByStock(Stock stock) {

        Query query = getCache().createQuery();

        Attribute<String> symbolAttribute = getCache().getSearchAttribute("symbol");
        query.addCriteria(symbolAttribute.eq(stock.getSymbol()));

        return getDataPointDescriptions(query.execute());
    }

    private Set<DataPointDescription> getDataPointDescriptions(Results results) {
        return results.all().stream()
                .map(Result::getValue)
                .map(t -> (DataPointDescription)t)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean removeIndicator(String id) {
        return getCache().remove(id);
    }
}
