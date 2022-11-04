package youtubeApi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtubeAnalytics.v2.YouTubeAnalytics;
import com.google.api.services.youtubeAnalytics.v2.model.QueryResponse;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class GetChannelInfo {
    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;
    private static ChannelData data;

    public static ChannelData getChannelInfo() {

        // This OAuth 2.0 access scope allows for write access to the authenticated user's account.
        List<String> scopes = Lists.newArrayList(YouTubeScopes.YOUTUBE_FORCE_SSL);

        List<String> scopes2 = Lists.newArrayList("https://www.googleapis.com/auth/yt-analytics.readonly","https://www.googleapis.com/auth/yt-analytics-monetary.readonly","https://www.googleapis.com/auth/youtube.readonly");

        data = new ChannelData();
        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes2, "getChannelInfo");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("youtube-cmdline-getChannelInfo-sample").build();

            // Define and execute the API request
            YouTube.Channels.List request = youtube.channels()
                    .list("statistics");
            ChannelListResponse response = request.setMine(true).execute();

            data.setSubscriberCount(response.getItems().get(0).getStatistics().getSubscriberCount());
            data.setVideoCount(response.getItems().get(0).getStatistics().getVideoCount());
            data.setViewCount(response.getItems().get(0).getStatistics().getViewCount());

            YouTubeAnalytics youtubeAnalyticsService = new YouTubeAnalytics.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("youtube-cmdline-getChannelInfo-sample").build();
            // Define and execute the API request
            YouTubeAnalytics.Reports.Query requestForRevenue = youtubeAnalyticsService.reports()
                    .query();
            QueryResponse responseForRevenue = requestForRevenue.setEndDate("2022-10-22")
                    .setIds("channel==MINE")
                    .setMetrics("estimatedRevenue")
                    .setStartDate("2017-01-01")
                    .execute();


            data.setEstimatedRevenue(responseForRevenue.getRows().get(0).get(0).toString());


        } catch (GoogleJsonResponseException e) {
            System.err
                    .println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                            + e.getDetails().getMessage());
            e.printStackTrace();
            data.setEstimatedRevenue("0");
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        return data;
    }
    public static class ChannelData{
        BigInteger videoCount;
        BigInteger subscriberCount;
        BigInteger viewCount;
        String estimatedRevenue;

        @Override
        public String toString() {
            return "ChannelData{" +
                    "videoCount=" + videoCount +
                    ", subscriberCount=" + subscriberCount +
                    ", viewCount=" + viewCount +
                    ", estimatedRevenue='" + estimatedRevenue + '\'' +
                    '}';
        }

        public BigInteger getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(BigInteger videoCount) {
            this.videoCount = videoCount;
        }

        public BigInteger getSubscriberCount() {
            return subscriberCount;
        }

        public void setSubscriberCount(BigInteger subscriberCount) {
            this.subscriberCount = subscriberCount;
        }

        public BigInteger getViewCount() {
            return viewCount;
        }

        public void setViewCount(BigInteger viewCount) {
            this.viewCount = viewCount;
        }

        public String getEstimatedRevenue() {
            return estimatedRevenue;
        }

        public void setEstimatedRevenue(String estimatedRevenue) {
            this.estimatedRevenue = estimatedRevenue;
        }
    }

    public static void main(String args[]){
        getChannelInfo();
    }
}