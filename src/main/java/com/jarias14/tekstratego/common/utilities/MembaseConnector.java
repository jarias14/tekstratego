package com.jarias14.tekstratego.common.utilities;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MembaseConnector {
    
    List<String> hosts;
    MemcachedClient client;
    
    public MembaseConnector() {
        hosts = new ArrayList<String>();
        hosts.add("localhost:11211");
        
        List<InetSocketAddress> address = AddrUtil.getAddresses(hosts);
        
        try {
            client = new MemcachedClient(address);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void save(String key, Object value) {
        
        Object spoon = client.get("spoon");
        if (spoon == null) {
            System.out.println("There is no spoon.");
            client.set("spoon", 10, "Hello World!");
        } else {
            System.out.println((String)spoon);
        }
        client.waitForQueues(1, TimeUnit.MINUTES);
        System.exit(0);
    }
    
    public void read(String key) {
        
    }

}
