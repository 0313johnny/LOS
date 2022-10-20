package com.example.tcp_connect;

import com.example.model.OBSWebsocket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    private Integer port = 55302;

    private String host = "127.0.0.1";
    //private String host = "140.121.196.20";

    private SocketChannel socketChannel;

    /**
     * 傳送訊息
     */
    public void sendMsg(String msg) {
        socketChannel.writeAndFlush(msg);
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new NettyClientInitializer());
        ChannelFuture future = bootstrap.connect();
        //客戶端斷線重連邏輯
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                System.out.println("連線Netty服務端成功");
                OBSWebsocket obs = new OBSWebsocket();
                sendMsg("OBSServer " + obs.getSceneList());
            } else {
                System.out.println("連線失敗，進行斷線重連");
                future1.channel().eventLoop().schedule(() -> start(), 20, TimeUnit.SECONDS);
            }
        });
        socketChannel = (SocketChannel) future.channel();
    }
}
