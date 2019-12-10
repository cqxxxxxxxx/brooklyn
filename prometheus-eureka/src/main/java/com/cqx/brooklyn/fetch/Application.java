package com.cqx.brooklyn.fetch;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/10
 */
@Data
public class Application {
    private String name;
    private List<Instance> instance;

    @Data
    public static class Instance {
        private String app;
        private String instanceId;
        private String hostName;
        private String ipAddr;
        private String status;
        private Map port;
        private Map securePort;
    }

}
