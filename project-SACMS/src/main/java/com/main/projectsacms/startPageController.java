package com.main.projectsacms;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class startPageController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button advisorSignUpButton;

    @FXML
    private Button studentSignUpButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView minimizeButton;

    @FXML
    private Text quote1;

    @FXML
    private Text quote2;

    @FXML
    private Text quote3;

    @FXML
    private Group FirstGroup;

    @FXML
    private Group SecondGroup;

    @FXML
    private Group advisorDetails;

    @FXML
    private Group studentDetails;


    ScaleTransition loginButtonIncrease;
    ScaleTransition loginButtonDecrease;

    ScaleTransition registerButtonIncrease;
    ScaleTransition registerButtonDecrease;

    ScaleTransition exitButtonIncrease;
    ScaleTransition exitButtonDecrease;

    ScaleTransition minimizeButtonIncrease;
    ScaleTransition minimizeButtonDecrease;

    ScaleTransition choosingRoleTeacher;
    ScaleTransition choosingRoleStudent;


    public void initialize(){

        FadeTransition quote1Transition = new FadeTransition(Duration.seconds(3), quote1);
        quote1Transition.setFromValue(0);
        quote1Transition.setToValue(1);
        quote1Transition.play();


        FadeTransition quote2Transition = new FadeTransition(Duration.seconds(3), quote2);
        quote2Transition.setFromValue(0);
        quote2Transition.setToValue(1);
        quote2Transition.play();

        FadeTransition quote3Transition = new FadeTransition(Duration.seconds(4), quote3);
        quote3Transition.setFromValue(0);
        quote3Transition.setToValue(1);
        quote3Transition.play();



        FadeTransition registerButtonTransition = new FadeTransition(Duration.seconds(3), registerButton);
        registerButtonTransition.setFromValue(0);
        registerButtonTransition.setToValue(1);

        FadeTransition loginButtonTransition = new FadeTransition(Duration.seconds(3), loginButton);
        loginButtonTransition.setFromValue(0);
        loginButtonTransition.setToValue(1);


        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        SequentialTransition registerButtonDelay = new SequentialTransition(delay, registerButtonTransition);
        SequentialTransition loginButtonDelay = new SequentialTransition(delay, loginButtonTransition);

        registerButtonDelay.play();
        loginButtonDelay.play();


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

        minimizeButtonIncrease = new ScaleTransition(Duration.millis(200), minimizeButton );
        minimizeButtonIncrease.setToX(1.2);
        minimizeButtonIncrease.setToY(1.2);

        minimizeButtonDecrease = new ScaleTransition(Duration.millis(200), minimizeButton);
        minimizeButtonDecrease.setToX(1);
        minimizeButtonDecrease.setToY(1);

    }


    public void loginButtonMouseEnter() {
        loginButtonDecrease.play();
    }
    public void loginButtonMouseExit() {
        loginButtonIncrease.play();
    }

    public void registerButtonMouseEnter() {
        registerButtonDecrease.play();
    }

    public void registerButtonMouseExit() {
        registerButtonIncrease.play();
    }

    public void exitButtonMouseEnter() {
        exitButtonIncrease.play();
    }

    public void exitButtonMouseExit() {
        exitButtonDecrease.play();
    }

    public void exitButtonClick(){
        System.exit(1);
    }

    public void minimizeButtonMouseEnter() {
        minimizeButtonIncrease.play();

    }

    public void minimizeButtonMouseExit() {
        minimizeButtonDecrease.play();

    }

    public void onRegisterButtonClick() {
        FirstGroup.setOpacity(0);
        SecondGroup.setOpacity(100);
        SecondGroup.setDisable(false);

        FadeTransition secondGroupTransition = new FadeTransition(Duration.seconds(3), SecondGroup);
        secondGroupTransition.setFromValue(0);
        secondGroupTransition.setToValue(1);
        secondGroupTransition.play();

    }

    public void onAdvisorButtonClick(ActionEvent actionEvent) {

        SecondGroup.setOpacity(0);
        SecondGroup.setDisable(true);

        advisorDetails.setDisable(false);

        FadeTransition advisorDetailsTransition = new FadeTransition(Duration.seconds(3), advisorDetails);
        advisorDetailsTransition.setFromValue(0);
        advisorDetailsTransition.setToValue(1);
        advisorDetailsTransition.play();

    }

    public void onStudentButtonClick(ActionEvent actionEvent) {
        SecondGroup.setOpacity(0);
        SecondGroup.setDisable(true);

        studentDetails.setDisable(false);

        FadeTransition studentDetailsTransition = new FadeTransition(Duration.seconds(3), studentDetails);
        studentDetailsTransition.setFromValue(0);
        studentDetailsTransition.setToValue(1);
        studentDetailsTransition.play();
    }
}