package model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import youtubeAPI.VoteAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class OBSWebsocket {
    private static File pathToExecutable = new File( "OBSCommand\\OBSCommand.exe" );

    private static VoteAPI vote;
    public OBSWebsocket(){

    }
    public OBSWebsocket(File path){
        this.pathToExecutable = path;
    }
    public static void startrecording(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/startrecording");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void stoprecording(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/stoprecording");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void startstream(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/startstream");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void stopstream(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/stopstream");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void switchScene(String scene){
        try {
            String cmd = "/scene="+scene;
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),cmd);
            System.out.println(cmd);
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getSceneList(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/command=GetSceneList");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("big5")));
            String line;
            String text="";
            while ((line = reader.readLine()) != null) {
                text+=line;
            }//取得cmd執行結果
            JSONObject jsonObject = JSONObject.parseObject(text);//將執行結果轉為物件
            JSONArray array = jsonObject.getJSONArray("scenes");
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
            ArrayList<String> scenesList = new ArrayList();
            for(int i = 0;i < array.size();i++){
                JSONObject temp = array.getJSONObject(i);
                if(temp.getString("name") != null)
                    scenesList.add(temp.getString("name"));
            }
            return JSON.toJSONString(scenesList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    public static void getCurrentProfile(){
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable.getAbsolutePath(),"/command=GetCurrentProfile");
            builder.directory(new File( "OBSCommand" ).getAbsoluteFile());
            builder.redirectErrorStream(true);
            Process process =  builder.start();
            int exitValue = process.waitFor();
            System.out.println("exitValue: " + exitValue);
            // 銷毀process物件
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println(getSceneList());
    }
}
