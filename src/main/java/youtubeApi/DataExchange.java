package youtubeApi;


public class DataExchange {
    private String teamName = "";
    private String ytKey = "";
    private boolean saveFlag = false;
    private String StreamingChat = "";
    private String SCDetail = "";
    private String videoData = "";
    private String allVideoData = "";
    private String relatedVideo = "";
    private String commentData = "";
    private String voteResult = "";
    private String channelData = "";

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getYtKey() {
        return ytKey;
    }

    public void setYtKey(String ytKey) {
        this.ytKey = ytKey;
    }

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getStreamingChat() {
        return StreamingChat;
    }

    public void setStreamingChat(String streamingChat) {
        StreamingChat = streamingChat;
    }

    public String getSCDetail() {
        return SCDetail;
    }

    public void setSCDetail(String SCDetail) {
        this.SCDetail = SCDetail;
    }

    public String getRelatedVideo() {
        return relatedVideo;
    }

    public void setRelatedVideo(String relatedVideo) {
        this.relatedVideo = relatedVideo;
    }

    public String getVideoData() {
        return videoData;
    }

    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }

    public String getAllVideoData() {
        return allVideoData;
    }

    public void setAllVideoData(String addVideoData) {
        this.allVideoData = addVideoData;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }

    public String getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(String voteResult) {
        this.voteResult = voteResult;
    }

    public String getChannelData() {
        return channelData;
    }

    public void setChannelData(String channelData) {
        this.channelData = channelData;
    }
}
