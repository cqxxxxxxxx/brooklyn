package com.cqx.brooklyn.controller;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/10
 */
public class AppController {

    public String helloWorld() {
        return "hello world" + LocalDateTime.now();
    }

    public String byebye() {
        return "byebye" + LocalDateTime.now();
    }
}
