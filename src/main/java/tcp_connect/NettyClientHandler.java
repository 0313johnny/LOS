package tcp_connect;

//import org.codehaus.jackson.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.OBSWebsocket;

import org.codehaus.jackson.map.ObjectMapper;
import model.YoutubeData;
import youtubeAPI.BanObject;
import youtubeAPI.GetVideoHistoryInfo;
import youtubeAPI.VoteData;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static NettyClient nettyClient = new NettyClient();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客戶端Active .....");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OBSWebsocket controller = new OBSWebsocket();
        YoutubeData youtubeData = new YoutubeData();
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
            case "startVote":
                VoteData v = mapper.readValue(cmd,VoteData.class);
                //System.out.println(v.toString() + "+++++");
                //System.out.println(cmd.toString() + "-----");
                youtubeData.startVote(v);
                break;
            case "getVoteData":
                nettyClient.sendMsg(mapper.writeValueAsString(youtubeData.getVoteResult()));
                break;
            case "getVideoInfo":
                nettyClient.sendMsg(youtubeData.getVideoData(cmd));
                break;
            case "getAllVideo":
                nettyClient.sendMsg(youtubeData.getAllVideoData());
                break;
            case "addLiveChatModerators":
                youtubeData.addLiveChatModerators(cmd);
                break;
            case "banLiveChatUser":
                BanObject b =mapper.readValue(cmd,BanObject.class);
                youtubeData.banLiveChatUser(b.getBannerId(),b.getBanTime());
                break;
            case "deleteLiveChatMessage":
                youtubeData.deleteLiveChatMessage(cmd);
                break;
            case "getRelatedVideo":
                youtubeData.getRelatedVideo(cmd);
                break;
            case "getComment":
                youtubeData.getComment(cmd);
                break;
            case "getLiveChatMessageOnce":
                youtubeData.getLiveChatMessage();
                break;
            case "getSCDetails":
                youtubeData.getSCDetail();
                break;
            case "getVideoHistoryInfo":
                GetVideoHistoryInfo.VideoHistory vh = mapper.readValue(cmd,GetVideoHistoryInfo.VideoHistory.class);
                youtubeData.getVideoHistory(vh.getVideoID(),vh.getStartDate(),vh.getEndDate());
                break;
            case "getChannelHistoryInfo":
                GetVideoHistoryInfo.ChannelHistory ch = mapper.readValue(cmd,GetVideoHistoryInfo.ChannelHistory.class);
                youtubeData.getChannelHistory(ch.getStartChannelDate(),ch.getEndChannelDate());
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
