package com.cqx.brooklyn;

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
    // 默认的线程数
    // DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt( "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    private static final NioEventLoopGroup work = new NioEventLoopGroup();

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
//                                .addLast("aggregator", new HttpObjectAggregator(Integer.MAX_VALUE))
                                .addLast("command", new CommandHandler());
                    }
                })
                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.SO_RCVBUF, 10)
//                .option(ChannelOption.SO_SNDBUF, 10)
                .option(ChannelOption.SO_BACKLOG, 128);
        ChannelFuture f = b.bind(9000);
        f.sync();
    }

    public static void main(String[] args) throws InterruptedException {
        HttpServer server = new HttpServer();
        server.start();
    }
}
