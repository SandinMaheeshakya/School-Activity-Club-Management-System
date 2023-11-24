package com.main.projectsacms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SACMS extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SACMS.class.getResource("FXML Files/startPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720 );
        stage.setTitle("StudentSync");
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}