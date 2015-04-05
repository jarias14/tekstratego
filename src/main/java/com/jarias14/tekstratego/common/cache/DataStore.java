package com.jarias14.tekstratego.common.cache;

import com.jarias14.tekstratego.common.models.*;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;
import net.sf.ehcache.search.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/4/2015.
 */
public class DataStore extends BlockingCache {

    public DataStore(Ehcache cache) throws CacheException {
        super(cache);
    }

    public void putDataPoint(Stock stock, DataPoint dataPoint, DataPointDetails details) {
        
        DataStoreKey dataStoreKey = new DataStoreKey(stock, dataPoint.getTime(), details);
        Element element = new Element(dataStoreKey, dataPoint);
        put(element);
    }


    public DataPointCollection getDataPoints(Stock stock, DataPointIndicator necessaryRawDataPointIndicator, long time, Integer necessaryNumberOfDataPoints) {

        // create the query
        Query query = getCache().createQuery();

        // add stock symbol to query
        populateSymbolAttribute(query, stock);

        // add indicator to query
        populateDataPointIndicatorAttribute(query, necessaryRawDataPointIndicator);

        // add time to query
        Attribute<Long> timeAttribute = getCache().getSearchAttribute("time");
        query.addCriteria(timeAttribute.le(time));

        // add limit of results to query
        query.maxResults(necessaryNumberOfDataPoints);


        // execute query and fetch results
        Results results = query.execute();
        List<DataPoint> dataPoints = results.all().stream()
                .map(Result::getValue).map(this::getDataPoint).collect(Collectors.toList());

        // prepare response
        DataPointCollection dataPointCollection = new DataPointCollection();
        dataPointCollection.setStock(stock);
        dataPointCollection.setDataPoints(dataPoints);
        dataPointCollection.setDetails(new DataPointDetails());
        dataPointCollection.getDetails().setIndicator(necessaryRawDataPointIndicator);
        dataPointCollection.getDetails().setSize(new DataPointSize(TimeUnit.DAYS, 1));

        return dataPointCollection;
    }

    public List<DataPoint> getDataPoints(Stock stock, long startTime, long endTime, DataPointDetails dataPointDetails) {

        Query query = retrieveDataPointsQuery(stock, startTime, endTime, dataPointDetails);
        Results results = query.execute();
        List<DataPoint> dataPoints = results.all().stream()
                .map(Result::getValue).map(this::getDataPoint).collect(Collectors.toList());
        return dataPoints;
    }

    private DataPoint getDataPoint(Object o) {
        return (DataPoint) o;
    }

    private Query retrieveDataPointsQuery(Stock stock, long startTime, long endTime, DataPointDetails dataPointDetails) {

        Attribute<String> symbolAttribute = getCache().getSearchAttribute("symbol");
        Attribute<Long> timeAttribute = getCache().getSearchAttribute("time");
        Attribute<DataPointIndicator> dataPointIndicatorAttribute = getCache().getSearchAttribute("dataPointIndicator");
        Attribute<Integer> dataPointSizeQuantityAttribute = getCache().getSearchAttribute("dataPointSizeQuantity");
        Attribute<TimeUnit> dataPointSizeUnitAttribute = getCache().getSearchAttribute("dataPointSizeUnit");

        Query query = getCache().createQuery();
        query.addCriteria(symbolAttribute.eq(stock.getSymbol()));
        query.addCriteria(timeAttribute.between(startTime, endTime));
        query.addCriteria(dataPointIndicatorAttribute.eq(dataPointDetails.getIndicator()));
        query.addCriteria(dataPointSizeQuantityAttribute.eq(dataPointDetails.getSize().getQuantity()));
        query.addCriteria(dataPointSizeUnitAttribute.eq(dataPointDetails.getSize().getUnit()));
        query.addOrderBy(timeAttribute, Direction.ASCENDING);

        return query;
    }

    private void populateSymbolAttribute(Query query, Stock stock) {
        Attribute<String> symbolAttribute = getCache().getSearchAttribute("symbol");
        query.addCriteria(symbolAttribute.eq(stock.getSymbol()));
    }

    private void populateDataPointIndicatorAttribute(Query query, DataPointDetails dataPointDetails) {

        Attribute<DataPointIndicator> dataPointIndicatorAttribute = getCache().getSearchAttribute("dataPointIndicator");
        Attribute<Integer> dataPointSizeQuantityAttribute = getCache().getSearchAttribute("dataPointSizeQuantity");
        Attribute<TimeUnit> dataPointSizeUnitAttribute = getCache().getSearchAttribute("dataPointSizeUnit");

        query.addCriteria(dataPointIndicatorAttribute.eq(dataPointDetails.getIndicator()));
        query.addCriteria(dataPointSizeQuantityAttribute.eq(dataPointDetails.getSize().getQuantity()));
        query.addCriteria(dataPointSizeUnitAttribute.eq(dataPointDetails.getSize().getUnit()));
    }

    private void populateDataPointIndicatorAttribute(Query query, DataPointIndicator dataPointIndicator) {

        Attribute<DataPointIndicator> dataPointIndicatorAttribute = getCache().getSearchAttribute("dataPointIndicator");

        query.addCriteria(dataPointIndicatorAttribute.eq(dataPointIndicator));
    }


}
