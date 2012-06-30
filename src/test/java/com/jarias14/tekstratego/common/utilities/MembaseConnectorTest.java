package com.jarias14.tekstratego.common.utilities;

import org.junit.Test;

public class MembaseConnectorTest {

    @Test
    public void test() {
        MembaseConnector mc = new MembaseConnector();
        mc.save("a", "b");
    }

}
