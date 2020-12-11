package com.xh.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.xh.netty.common.Constants.PORT;

@Slf4j
public class Client {
    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HelloClientIntHandler());
                    ch.pipeline().addLast(new HelloClientIntHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

@Slf4j
class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
    @Override
    // 读取服务端的信息
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("HelloClientIntHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        result.release();
        System.out.println("Server said:" + new String(result1));
    }
    @Override
    // 当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("HelloClientIntHandler.channelActive");
        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();
        ctx.fireChannelRead(msg);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.connect("localhost", PORT);
        } catch (Exception e) {
            log.error("error", e);
            e.printStackTrace();
        }
    }
}
