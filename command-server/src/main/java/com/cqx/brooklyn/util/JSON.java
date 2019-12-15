package com.cqx.brooklyn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
@Slf4j
public class JSON {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("json 序列化异常{}", o, e);
            throw new RuntimeException("序列化异常");
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化异常{}", json, e);
            throw new RuntimeException("反序列化异常");
        }
    }

}
