package com.main.projectsacms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SACMS extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SACMS.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720 );
        stage.setTitle("StudentSync");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}