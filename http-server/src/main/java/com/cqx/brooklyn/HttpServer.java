package com.cqx.brooklyn;

import com.cqx.brooklyn.dispatch.DispatchHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/9
 */
public class HttpServer {

    private static final NioEventLoopGroup boss = new NioEventLoopGroup(1);
    private static final NioEventLoopGroup work = new NioEventLoopGroup(10);

    public void start() throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("log", new LoggingHandler(LogLevel.INFO));
                    }
                })
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("log", new LoggingHandler(LogLevel.INFO))
                                .addLast("decoder", new HttpRequestDecoder())
                                .addLast("encoder", new HttpResponseEncoder())
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast("dispatch", new DispatchHandler());
                    }
                })
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 128);
        ChannelFuture f = b.bind("127.0.0.1", 9000);
        f.sync();
    }

    public static void main(String[] args) throws InterruptedException {
        HttpServer server = new HttpServer();
        server.start();
    }
}
