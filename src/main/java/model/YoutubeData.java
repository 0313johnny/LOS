package model;

import com.google.api.services.youtube.model.PlaylistItem;
import youtubeAPI.*;

import java.math.BigInteger;
import java.util.List;

public class YoutubeData {

    private static VoteAPI vote = new VoteAPI();
    public YoutubeData(){

    }

    public String getVideoData(String id){
        Video newVideo = getVideoInfo.getVideoInfo(id);
        return newVideo.toString();
    }
    public String getAllVideoData(){
        List<PlaylistItem> myVideos = GetAllVideos.getAllVideos();
        return myVideos.toString();
    }
    public String getComment(String id){
        return GetComment.getComment(id).toString();
    }

    public String getLiveChatMessage(){
        return GetLiveChatOnce.getLiveChatOnce();
    }
    public String getSCDetail(){
        return GetSCDetails.getSCDetails();
    }
    public String getRelatedVideo(String id){
        return GetRelatedVideo.getRelatedVideo(id);
    }
    public String getVideoHistory(String id,String start,String end){
        return GetVideoHistoryInfo.getVideoHistoryInfo(id,start,end);
    }
    public String getChannelHistory(String start,String end){
        return GetVideoHistoryInfo.getVideoHistoryInfo(start,end);
    }
    public String addLiveChatModerators(String id){
        return AddLiveChatModerators.addLiveChatModerators(id);
    }
    public String banLiveChatUser(String id, BigInteger time){
        return BanLiveChatUser.banLiveChatUser(id,time);
    }
    public String deleteLiveChatMessage(String id){
        return DeleteLiveChatMessage.deleteLiveChatMessage(id);
    }
    public void startVote(VoteData voteData){
        vote.run(voteData);
    }
    public VoteResult getVoteResult(){
        return vote.getVoteResultData();
    }
}
