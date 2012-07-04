package com.jarias14.tekstratego.common.utilities;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MySqlConnector {
    
    DriverManagerDataSource client;

    public void save(String key, Object value, int timeToLiveInSeconds) {
        
        //client.set(key, timeToLiveInSeconds, value);
    }
    
    public Object read(String key){

        return null;//client.get(key);
    }
    
    public DriverManagerDataSource getClient() {
        return client;
    }

    public void setClient(DriverManagerDataSource client) {
        this.client = client;
    }

}
