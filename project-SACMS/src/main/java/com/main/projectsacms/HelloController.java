package com.main.projectsacms;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.nio.Buffer;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button  registerButton;

    @FXML
    private Button loginButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    FadeTransition fadeTransitionIn;
    FadeTransition fadeTransitionOut;

    public void initialize(){

        // Add a smooth hover effect using FadeTransition
        FadeTransition fadeTransitionIn = new FadeTransition(Duration.millis(300), registerButton);
        fadeTransitionIn.setFromValue(0.0);
        fadeTransitionIn.setToValue(1.0);

        FadeTransition fadeTransitionOut = new FadeTransition(Duration.millis(300), registerButton);
        fadeTransitionOut.setFromValue(1.0);
        fadeTransitionOut.setToValue(0.0);



    }





    @FXML
    public void registerButtonMouseEnter(){
        fadeTransitionIn.play();

    }
    @FXML
    public void registerButtonMouseExit() {
        fadeTransitionOut.play();

    }
    @FXML
    public void loginButtonMouseEnter(MouseEvent mouseEvent) {
    }

    @FXML
    public void loginButtonMouseExit(MouseEvent mouseEvent) {
    }
}