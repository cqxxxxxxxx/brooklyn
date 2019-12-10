package com.cqx.brooklyn.fetch;

import lombok.Data;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/10
 */
@Data
public class EurekaRoot {
    private String versions__delta;
    private String apps__hashcode;
    private List<Application> application;
}
