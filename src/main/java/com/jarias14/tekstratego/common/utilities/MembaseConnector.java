package com.jarias14.tekstratego.common.utilities;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;

public class MembaseConnector {
    
    MemcachedClient client;
    
    public MembaseConnector() {
        try {
            client = new MemcachedClient(
                        new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY).build(),
                        AddrUtil.getAddresses("localhost:11211"));
        } catch (Exception e) {
            
        }
    }
    
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
    
    public void destroy() {
        
    }

}
