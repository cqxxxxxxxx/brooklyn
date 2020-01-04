package com.cqx.brooklyn.command;

import com.cqx.brooklyn.model.Request;
import com.cqx.brooklyn.util.RespUtil;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/17
 */
public class MockCommand implements Command {
    @Override
    public String name() {
        return "mock";
    }

    @Override
    public HttpResponse execute(Request request) {
        String json = "{\"code\":\"040005\",\"msg\":\"处理失败\",\"data\":{}}";
        return RespUtil.success(json);
    }
}
