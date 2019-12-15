package com.cqx.brooklyn.command;

import com.cqx.brooklyn.model.Request;
import io.netty.handler.codec.http.HttpResponse;

/**
 * 命令接口
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
public interface Command<T extends Request> {

    /**
     * 命令的名字
     *
     * @return
     */
    String name();

    HttpResponse execute(T t);

}
