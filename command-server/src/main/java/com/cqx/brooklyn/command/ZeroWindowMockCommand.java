package com.cqx.brooklyn.command;

import com.cqx.brooklyn.model.Request;
import com.cqx.brooklyn.util.RespUtil;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@Sharable
public class ZeroWindowMockCommand implements Command {
    private static final String name = "zero-window-mock";

    @Override
    public String name() {
        return name;
    }

    @Override
    public HttpResponse execute(Request request) {
        try {
            //阻塞一段时间，不处理数据，使tcp 接收窗口填满。
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return RespUtil.success("你好啊，zero window mock");
    }
}
