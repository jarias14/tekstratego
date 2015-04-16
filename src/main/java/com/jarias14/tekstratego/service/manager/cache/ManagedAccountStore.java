package com.jarias14.tekstratego.service.manager.cache;

import com.jarias14.tekstratego.common.models.Stock;
import com.jarias14.tekstratego.service.manager.models.ManagedAccount;
import com.jarias14.tekstratego.service.manager.models.ManagedAccountStrategy;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<ManagedAccount> getManagedAccounts(Stock stock) {

        Map<Object, Element> elementMap = getCache().getAll(getCache().getKeys());

        List<ManagedAccount> managedAccounts = elementMap.values().stream()
                .map(Element::getObjectValue)
                .map(v -> (ManagedAccount)v)
                .filter(v -> {
                    for (ManagedAccountStrategy strategy : v.getStrategies()) {
                        if (strategy.getStocks().contains(stock)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return managedAccounts;
    }
}
