package com.example.los;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LOS_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create FXML loader
        FXMLLoader fxmlLoader = new FXMLLoader(LOS_Application.class.getResource("LOS_main.fxml"));

        // Create CSS URL
        String css = this.getClass().getResource("LOS_main.css").toExternalForm();

        // Create scene
        Scene scene = new Scene(fxmlLoader.load(), 1378, 720);

        //Parent root = fxmlLoader.load();
        //stage.setResizable(false);
        stage.setTitle("LOS");
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}