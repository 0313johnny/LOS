package com.example.los;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LOS_Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void showSettingWindow(ActionEvent event) throws IOException {
        FXMLLoader settingLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_setting.fxml"));
        Scene settingScene = new Scene(settingLoader.load());

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(settingScene);
        window.show();
    }

    public void showMainWindow(ActionEvent event) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_main.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        // Get window information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }
}