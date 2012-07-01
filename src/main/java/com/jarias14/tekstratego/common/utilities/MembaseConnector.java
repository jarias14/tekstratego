package com.jarias14.tekstratego.common.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MembaseConnector {
    private final static Logger LOGGER = Logger.getLogger(MembaseConnector.class .getName());
    
    MemcachedClient client;
    
    public void save(String key, Object value, int timeToLiveInSeconds) {
        if (client == null) {
            try {
                client = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"));
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getStackTrace().toString());
                System.out.println(e.getMessage());
                System.err.println(e.getMessage());
            }
        }

        client.set(key, timeToLiveInSeconds, value);
        
    }
    
    public Object read(String key){
        if (client == null) {
            try {
                client = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.err.println(e.getMessage());
            }
        }
        
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
