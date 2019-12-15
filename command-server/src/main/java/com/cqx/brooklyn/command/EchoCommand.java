package com.cqx.brooklyn.command;

import com.cqx.brooklyn.model.Request;
import com.cqx.brooklyn.util.RespUtil;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
@Sharable
public class EchoCommand implements Command {
    private static final String name = "echo";

    @Override
    public String name() {
        return name;
    }

    @Override
    public HttpResponse execute(Request request) {
        return RespUtil.success(request.bodyRaw());
    }

}
