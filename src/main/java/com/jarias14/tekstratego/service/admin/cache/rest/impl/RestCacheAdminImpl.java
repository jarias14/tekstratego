package com.jarias14.tekstratego.service.admin.cache.rest.impl;

import com.jarias14.tekstratego.service.admin.cache.rest.CacheEntryResource;
import com.jarias14.tekstratego.service.admin.cache.rest.CacheResource;
import com.jarias14.tekstratego.service.admin.cache.rest.RestCacheAdmin;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jarias14 on 4/13/2015.
 */
public class RestCacheAdminImpl implements RestCacheAdmin {

    private List<BlockingCache> cacheList;

    @Override
    public Set<CacheResource> getAllCacheElements() {

        Set<CacheResource> cacheResources = new HashSet<CacheResource>();

        cacheList.stream().forEach(c -> {
            CacheResource cacheResource = new CacheResource();
            cacheResource.setEntries((Set<CacheEntryResource>) c.getKeys().stream().map(c::get).map(element -> new CacheEntryResource(((Element)element).getObjectKey(), ((Element)element).getObjectValue())).collect(Collectors.toSet()));
            cacheResource.setName(c.getName());
            cacheResources.add(cacheResource);
        });

        return cacheResources;
    }

    @Required
    public void setCacheList(List<BlockingCache> cacheList) {
        this.cacheList = cacheList;
    }
}
