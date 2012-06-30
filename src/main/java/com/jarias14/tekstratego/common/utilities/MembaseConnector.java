package com.jarias14.tekstratego.common.utilities;

import net.spy.memcached.MemcachedClient;

public class MembaseConnector {
    
    MemcachedClient client;

    public void save(String key, Object value, int timeToLiveInSeconds) {
        client.set(key, timeToLiveInSeconds, value);
    }
    
    public Object read(String key) {
        return client.get(key);
    }
    
    public MemcachedClient getClient() {
        return client;
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
    }

}
