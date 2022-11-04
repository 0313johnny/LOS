package model;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import youtubeApi.*;

import java.io.IOException;
import java.math.BigInteger;

public class YoutubeData {

    private static VoteAPI vote = new VoteAPI();
    public YoutubeData(){

    }

    public DataExchange getVideoData(String str) throws IOException {
        String[] dataString = str.split("&");
        String ytAccount = dataString[0];
        String id = dataString[1];
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setVideoData(mapper.writeValueAsString(getVideoInfo.getVideoInfo(id)));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("videoData",data.getVideoData()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getAllVideoData(String ytAccount) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setAllVideoData(mapper.writeValueAsString(GetAllVideos.getAllVideos()));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("allVideoData",data.getAllVideoData()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getComment(String str) throws IOException {
        String[] dataString = str.split("&");
        String ytAccount = dataString[0];
        String id = dataString[1];
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setCommentData(mapper.writeValueAsString(GetComment.getComment(id)));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("commentData",data.getCommentData()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }

    public DataExchange getLiveChatMessage(String ytAccount) throws IOException {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        ObjectMapper mapper = new ObjectMapper();
        data.setStreamingChat(mapper.writeValueAsString(GetLiveChatOnce.getLiveChatOnce()));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("streamingChat",data.getStreamingChat()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getSCDetail(String ytAccount){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setSCDetail(GetSCDetails.getSCDetails());
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("SCDetail",data.getSCDetail()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getRelatedVideo(String str) throws IOException {
        String[] dataString = str.split("&");
        String ytAccount = dataString[0];
        String id = dataString[1];
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setRelatedVideo(mapper.writeValueAsString(GetRelatedVideo.getRelatedVideo(id)));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("relatedVideo",data.getRelatedVideo()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getVideoHistory(String str) throws IOException {
        String[] dataString = str.split("&");
        String ytAccount = dataString[0];
        String id = dataString[1];
        String start = dataString[2];
        String end = dataString[3];
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setRelatedVideo(mapper.writeValueAsString(GetRelatedVideo.getRelatedVideo(id)));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("SCDetail",data.getRelatedVideo()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
        //return GetVideoHistoryInfo.getVideoHistoryInfo(id,start,end);
    }
    public String getChannelHistory(String start,String end){
        return GetVideoHistoryInfo.getVideoHistoryInfo(start,end);
    }
    public String addLiveChatModerators(String id){
        return AddLiveChatModerators.addLiveChatModerators(id);
    }
    public String banLiveChatUser(String str){
        String[] dataString = str.split("&");
        String id = dataString[0];
        BigInteger time = new BigInteger(dataString[1]);

        return BanLiveChatUser.banLiveChatUser(id,time);
    }
    public String deleteLiveChatMessage(String id){

        return DeleteLiveChatMessage.deleteLiveChatMessage(id);
    }
    public void startVote(VoteData voteData){
        vote.setVoteData(voteData);
        vote.start();
    }
    public DataExchange getVoteResult(String ytAccount) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setVoteResult(mapper.writeValueAsString(vote.getVoteResultData()));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("voteResult",data.getVoteResult()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
    public DataExchange getChannelData(String ytAccount) throws IOException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        ObjectMapper mapper = new ObjectMapper();
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setChannelData(mapper.writeValueAsString(GetChannelInfo.getChannelInfo()));
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("channelData",data.getChannelData()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
        }
    public String getStreamingVideo(){
        return getStreamingVideo.getVideoId();
    }

    public static DataExchange getStringURL(String ytAccount){

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        DataExchange data = new DataExchange();
        data.setTeamName(ytAccount);
        data.setYtKey(getStreamingVideo.getVideoId());
        data.setSaveFlag(true);

        Document document = new Document ("teamName",ytAccount).
                append ("ytKey",data.getYtKey()).
                append ("saveFlag",true);
        Bson eq = Filters.eq("teamName", ytAccount);

        String connectionString = "mongodb://YTYTdev:SHBX7NlIFxXZ4xfH@ac-semp1jk-shard-00-00.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-01.sq5s7rs.mongodb.net:27017,ac-semp1jk-shard-00-02.sq5s7rs.mongodb.net:27017/?ssl=true&replicaSet=atlas-84q8to-shard-0&authSource=admin&retryWrites=true&w=majority";

        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);

        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase( "YTYTdb" );
        MongoCollection<Document> collection = mongoDatabase.getCollection("DataExchange");

        FindIterable<Document> findIterable = collection.find(eq);
        MongoCursor < Document > mongoCursor = findIterable.iterator ();

        if(!mongoCursor.hasNext()){ //沒有檔案
            System.out.println("!!< null");
            collection.insertOne(document);
        }
        else{
            System.out.println(findIterable + "!!< find");
            collection.updateOne(eq, new Document ("$set",document));
        }

        //collection.insertOne(document);
        System.out.println ( "文檔插入成功" );

        return data;
    }
}
