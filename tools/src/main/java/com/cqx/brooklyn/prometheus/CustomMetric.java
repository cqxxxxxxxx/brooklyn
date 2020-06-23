package com.cqx.brooklyn.prometheus;

import io.prometheus.client.Summary;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2020/1/13
 */
public class CustomMetric {

    public void summary() {
        Summary summary = Summary.build().create();
    }
}
