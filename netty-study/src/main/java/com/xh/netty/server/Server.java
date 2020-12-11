package com.xh.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import static com.xh.netty.common.Constants.PORT;

@Slf4j
public class Server {
    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        NioEventLoopGroup subGroup = new NioEventLoopGroup();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .group(mainGroup, subGroup)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new OutboundHandler1());
                        ch.pipeline().addLast(new OutboundHandler2());
                        // 注册两个InboundHandler，执行顺序为注册顺序，所以应该是InboundHandler1 InboundHandler2
                        ch.pipeline().addLast(new InboundHandler1());
                        ch.pipeline().addLast(new InboundHandler2());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture f = serverBootstrap.bind(PORT).sync();
            log.info("server start");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}

@Slf4j
class InboundHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    // 读取Client发送的信息，并打印出来
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InboundHandler2.channelRead: ctx :" + ctx);
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println("Client said:" + resultStr);
        result.release();

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("InboundHandler2.channelReadComplete");
        ctx.flush();
    }
}


@Slf4j
class InboundHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InboundHandler1.channelRead: ctx :" + ctx);
        // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("InboundHandler1.channelReadComplete");
        ctx.flush();
    }
}

@Slf4j
class OutboundHandler1 extends ChannelOutboundHandlerAdapter {

    @Override
    // 向client发送消息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("OutboundHandler1.write");
        String response = "I am ok!";
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}


@Slf4j
class OutboundHandler2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("OutboundHandler2.write");
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}



