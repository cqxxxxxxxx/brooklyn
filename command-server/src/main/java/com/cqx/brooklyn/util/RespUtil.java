package com.cqx.brooklyn.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import lombok.Data;

/**
 * 解耦
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
@Data
public class RespUtil {

    public static HttpResponse error(String msg) {
        return build(Unpooled.wrappedBuffer(msg.getBytes()), HttpResponseStatus.BAD_REQUEST);
    }

    public static HttpResponse success(String msg) {
        return build(Unpooled.wrappedBuffer(msg.getBytes()), HttpResponseStatus.OK);
    }

    public static HttpResponse success(ByteBuf buf) {
        return build(buf, HttpResponseStatus.OK);
    }

    public static HttpResponse error(ByteBuf buf) {
        return build(buf, HttpResponseStatus.BAD_REQUEST);
    }

    private static HttpResponse build(ByteBuf body, HttpResponseStatus status) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, body);
        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

}
