package com.cqx.brooklyn.model;

import com.cqx.brooklyn.util.AntPathMatcher;
import com.cqx.brooklyn.util.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import static com.cqx.brooklyn.Constant.PATH_PATTERN;

/**
 * 解耦
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
@Data
@AllArgsConstructor(staticName = "wrap")
public class Request {
    private final static AntPathMatcher antPathMatcher = new AntPathMatcher();
    private FullHttpRequest delegate;

    public boolean uriMatch() {
        return antPathMatcher.match(PATH_PATTERN, delegate.uri());
    }

    public String commandName() {
        return antPathMatcher.extractPathWithinPattern(PATH_PATTERN, delegate.uri());
    }

    public boolean methodMatchAny(HttpMethod... method) {
        Objects.requireNonNull(method);
        return Arrays.stream(method).filter(x -> x.compareTo(delegate.method()) == 0).findAny().isPresent();
    }

    public ByteBuf bodyRaw() {
        return delegate.content().copy();
    }

    public String body() {
        byte[] bytes = new byte[delegate.content().readableBytes()];
        delegate.content().readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public <T> T body(Class<T> clazz) {
        String json = body();
        return JSON.parse(json, clazz);
    }
}
