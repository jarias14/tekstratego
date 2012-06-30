package com.jarias14.tekstratego.common.utilities;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:membase.xml")
public class MembaseConnectorTest {

    @Resource(name="membaseConnector")
    MembaseConnector mc;
    
    @Test
    public void test() {
        
        String key = "a";
        String value = "b";
        int ttl = 10;
        
        mc.save(key, value, ttl);
        Assert.assertEquals(value, mc.read(key));
    }

}
