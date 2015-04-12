package com.jarias14.tekstratego.service.manager.cache;

import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class ManagedAccountStore extends BlockingCache {

    public ManagedAccountStore(Ehcache cache) throws CacheException {
        super(cache);
    }

    public void store(ManagedAccount managedAccount) {
        put(new Element(managedAccount.getId(), managedAccount));
    }

    public ManagedAccount fetch(String id) {
        return (ManagedAccount) get(id).getObjectValue();
    }
}
