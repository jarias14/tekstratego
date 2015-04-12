package com.jarias14.tekstratego.service.thinker.cache;

import com.jarias14.tekstratego.service.thinker.rest.model.DecisionNode;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class DecisionNodeDataStore extends BlockingCache {
    public DecisionNodeDataStore(Ehcache cache) throws CacheException {
        super(cache);
    }

    public void store(DecisionNode decisionNode) {
        put(new Element(decisionNode.getId(), decisionNode));
    }

    public DecisionNode retrieve(String id) {
        Element element = get(id);
        return (DecisionNode) element.getObjectValue();
    }
}
