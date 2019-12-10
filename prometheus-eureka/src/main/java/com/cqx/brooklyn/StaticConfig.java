package com.cqx.brooklyn;

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
public class StaticConfig {
    private List<String> targets;
    private Map<String, String> labels;
}
