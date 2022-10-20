package com.example.tcp_connect;

import com.example.model.OBSWebsocket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客戶端Active .....");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        OBSWebsocket controller = new OBSWebsocket();
        String temp = msg.toString();
        String type = msg.toString();
        String cmd = "";
        if(temp.indexOf(" ") != -1)
            type = temp.substring(0,temp.indexOf(" "));
        if(temp.indexOf(" ") != -1)
            cmd = temp.substring(type.length()+1,temp.length());
        switch (type){
            case "startrecording":
                controller.startrecording();
                break;
            case "stoprecording":
                controller.stoprecording();
                break;
//            case "GetSceneList":
//                return controller.getSceneList();
            case "startstream":
                controller.startstream();
                break;
            case "stopstream":
                controller.stopstream();
                break;
            case "switchScene":
                controller.switchScene(cmd);
                break;
            default:
                System.out.println("客戶端收到訊息: {"+msg.toString()+"}");
        }
        System.out.println("執行項目: {"+msg.toString()+"}");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
