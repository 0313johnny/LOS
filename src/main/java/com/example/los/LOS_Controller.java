package com.example.los;

import com.example.tcp_connect.NettyClient;
import org.json.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class LOS_Controller {
    @FXML private TextField userInput;
    @FXML private PasswordField passwordInput;

    public void showMainWindow(ActionEvent event) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_main.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        String css = this.getClass().getResource("LOS_main.css").toExternalForm();
        mainScene.getStylesheets().add(css);

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    public void showSettingWindow(ActionEvent event) throws IOException {
        FXMLLoader settingLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_setting.fxml"));
        Scene settingScene = new Scene(settingLoader.load());

        String css = this.getClass().getResource("LOS_main.css").toExternalForm();
        settingScene.getStylesheets().add(css);

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(settingScene);
        window.show();
    }

    public void showDataWindow(ActionEvent event) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_data.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        String css = this.getClass().getResource("LOS_main.css").toExternalForm();
        mainScene.getStylesheets().add(css);

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    public void showConnectWindow(ActionEvent event) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_connect.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        String css = this.getClass().getResource("LOS_main.css").toExternalForm();
        mainScene.getStylesheets().add(css);

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    public void verifyButtonPressed(ActionEvent event) throws IOException {
        // ??????????????????Verify???????????????
        // ??????????????????????????????

        String user = userInput.getText();
        String password = passwordInput.getText();

        //??????body???????????????put???JSONObject???
        JSONObject param = new JSONObject();
        param.put("userName", user);
        param.put("password", password);

        System.out.println(param.toString());

        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("http://127.0.0.1:55304/login"); // Get URL

            httpURLConnection = (HttpURLConnection) url.openConnection(); // create connection entity

            // setting
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true); // ???????????????????????? HTTP ????????????
            httpURLConnection.addRequestProperty("sysId","sysId"); // ???????????????
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json"); // ????????????????????????????????????????????????-??????
            httpURLConnection.connect(); // Connect

            // Writing argument
            OutputStream os = httpURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(param.toString());
            osw.flush();
            osw.close();

            System.out.println(httpURLConnection.toString());

            // ???????????????
            String msg = "";
            int code = httpURLConnection.getResponseCode();
            System.out.println("code = " + code);

            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    msg += line + "\n";
                }
                reader.close();
            }
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("???????????????"+e.getLocalizedMessage()+";"+e.getClass());
        } finally {
            // disconnect
            if (null != httpURLConnection){
                try {
                    httpURLConnection.disconnect();
                    System.out.println("????????????");
                }catch (Exception e){
                    System.out.println("httpURLConnection???????????????"+ e.getLocalizedMessage());
                }
            }
        }
    }

    public void connectButtonPressed(ActionEvent event) throws IOException {
        NettyClient LocalServer = new NettyClient();
        LocalServer.start();
    }
}