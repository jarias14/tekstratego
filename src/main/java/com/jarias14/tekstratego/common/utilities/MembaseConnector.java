package com.jarias14.tekstratego.common.utilities;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MembaseConnector {
    
    MemcachedClient client;
    
    public void init() {
        if (client == null) {
            try {
                client = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.err.println(e.getMessage());
            }
        }
    }
    
    public void shutdown() {
        client.shutdown();
    }
    
    public void save(String key, Object value, int timeToLiveInSeconds) {
        
        client.set(key, timeToLiveInSeconds, value);
    }
    
    public Object read(String key){

        return client.get(key);
    }
    /*
    public MemcachedClient getClient() {
        return client;
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
    }*/
    

}
