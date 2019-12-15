package com.cqx.brooklyn;

import com.cqx.brooklyn.model.Request;
import com.cqx.brooklyn.util.AntPathMatcher;
import com.cqx.brooklyn.util.RespUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import static com.cqx.brooklyn.Constant.PATH_PATTERN;

/**
 * 命令分派
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
@Slf4j
@ChannelHandler.Sharable
public class CommandHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private Environment environment = Environment.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request0) throws Exception {
        Request request = Request.wrap(request0);
        HttpResponse response;
        if (request.uriMatch()) {
            String name = request.commandName();
            response = environment.getCommand(name).execute(request);
        } else {
            response = RespUtil.error("请求路径不支持");
        }
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    /**
     * 提取命令名
     *
     * @param uri
     * @return
     */
    private String commandName(String uri) {
        return new AntPathMatcher().extractPathWithinPattern(PATH_PATTERN, uri);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.getMessage(), cause);
    }

}
