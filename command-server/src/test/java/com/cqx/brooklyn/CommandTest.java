package com.cqx.brooklyn;

import org.junit.Test;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/15
 */
public class CommandTest {

    @Test
    public void echo() {
        new Thread(() -> this.start()).start();

    }


    private void start() {
        HttpServer server = new HttpServer();
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
