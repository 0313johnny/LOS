package los;

import org.json.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
        // 當登入畫面的Verify按鈕按下後
        // 要進行帳號登入的驗證

        String user = userInput.getText();
        String password = passwordInput.getText();

        //设置body内的参数，put到JSONObject中
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
            httpURLConnection.setInstanceFollowRedirects(true); // 設定是否自動執行 HTTP 重新定向
            httpURLConnection.addRequestProperty("sysId","sysId"); // 设置请求头
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json"); // 设置使用标准编码格式编码参数的名-值对
            httpURLConnection.connect(); // Connect

            // Writing argument
            OutputStream os = httpURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(param.toString());
            osw.flush();
            osw.close();

            System.out.println(httpURLConnection.toString());

            // 讀取回傳值
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
            System.out.println("錯誤訊息："+e.getLocalizedMessage()+";"+e.getClass());
        } finally {
            // disconnect
            if (null != httpURLConnection){
                try {
                    httpURLConnection.disconnect();
                    System.out.println("關閉連接");
                }catch (Exception e){
                    System.out.println("httpURLConnection關閉異常："+ e.getLocalizedMessage());
                }
            }
        }
    }

    public void connectButtonPressed(ActionEvent event) throws IOException {

    }
}