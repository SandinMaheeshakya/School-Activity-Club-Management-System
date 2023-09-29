package com.main.projectsacms;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView exitButton;


    ScaleTransition loginButtonIncrease;
    ScaleTransition loginButtonDecrease;

    ScaleTransition registerButtonIncrease;
    ScaleTransition registerButtonDecrease;

    ScaleTransition exitButtonIncrease;
    ScaleTransition exitButtonDecrease;


    public void initialize(){


        loginButtonIncrease = new ScaleTransition(Duration.millis(200), loginButton );
        loginButtonIncrease.setToX(1);
        loginButtonIncrease.setToY(1);

        loginButtonDecrease = new ScaleTransition(Duration.millis(200), loginButton);
        loginButtonDecrease.setToX(0.9);
        loginButtonDecrease.setToY(0.9);


        registerButtonIncrease = new ScaleTransition(Duration.millis(200), registerButton );
        registerButtonIncrease.setToX(1);
        registerButtonIncrease.setToY(1);

        registerButtonDecrease = new ScaleTransition(Duration.millis(200), registerButton);
        registerButtonDecrease.setToX(0.9);
        registerButtonDecrease.setToY(0.9);

        exitButtonIncrease = new ScaleTransition(Duration.millis(200), exitButton );
        exitButtonIncrease.setToX(1.2);
        exitButtonIncrease.setToY(1.2);

        exitButtonDecrease = new ScaleTransition(Duration.millis(200), exitButton);
        exitButtonDecrease.setToX(1);
        exitButtonDecrease.setToY(1);

    }


    public void loginButtonMouseEnter(MouseEvent mouseEvent) {
        loginButtonDecrease.play();
    }
    public void loginButtonMouseExit(MouseEvent mouseEvent) {
        loginButtonIncrease.play();
    }


    public void registerButtonMouseEnter(MouseEvent mouseEvent) {
        registerButtonDecrease.play();
    }

    public void registerButtonMouseExit(MouseEvent mouseEvent) {
        registerButtonIncrease.play();
    }

    public void exitButtonMouseEnter(MouseEvent mouseEvent) {
        exitButtonIncrease.play();
    }

    public void exitButtonMouseExit(MouseEvent mouseEvent) {
        exitButtonDecrease.play();
    }

    public void exitButtonClick(){
        System.exit(1);
    }
}