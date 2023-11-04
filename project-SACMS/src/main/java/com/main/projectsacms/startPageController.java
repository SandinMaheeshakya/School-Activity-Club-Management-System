package com.main.projectsacms;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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


    @FXML
    private TextField advisorEmail;

    @FXML
    private TextField advisorUsername;

    @FXML
    private TextField advisorFirstName;

    @FXML
    private TextField advisorLastName;

    @FXML
    private TextField advisorTeachingID;

    @FXML
    private TextField advisorPassword;

    @FXML
    private TextField advisorConfirmPassword;

    @FXML
    private DatePicker advisorDOB;




    ScaleTransition loginButtonIncrease;
    ScaleTransition loginButtonDecrease;

    ScaleTransition registerButtonIncrease;
    ScaleTransition registerButtonDecrease;

    ScaleTransition exitButtonIncrease;
    ScaleTransition exitButtonDecrease;

    ScaleTransition minimizeButtonIncrease;
    ScaleTransition minimizeButtonDecrease;

    ScaleTransition studentButtonIncrease;
    ScaleTransition studentButtonDecrease;

    ScaleTransition advisorButtonIncrease;
    ScaleTransition advisorButtonDecrease;

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


        studentButtonIncrease = new ScaleTransition(Duration.millis(200), studentSignUpButton );
        studentButtonIncrease.setToX(1);
        studentButtonIncrease.setToY(1);

        studentButtonDecrease = new ScaleTransition(Duration.millis(200), studentSignUpButton);
        studentButtonDecrease.setToX(0.9);
        studentButtonDecrease.setToY(0.9);

        advisorButtonIncrease = new ScaleTransition(Duration.millis(200), advisorSignUpButton );
        advisorButtonIncrease.setToX(1);
        advisorButtonIncrease.setToY(1);

        advisorButtonDecrease = new ScaleTransition(Duration.millis(200), advisorSignUpButton);
        advisorButtonDecrease.setToX(0.9);
        advisorButtonDecrease.setToY(0.9);

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

    public void onAdvisorButtonClick() {

        FadeTransition advisorDetailsTransition = new FadeTransition(Duration.seconds(3), advisorDetails);
        advisorDetailsTransition.setFromValue(0);
        advisorDetailsTransition.setToValue(1);
        advisorDetailsTransition.play();

        advisorDetails.setDisable(false);

        SecondGroup.setVisible(false);
        SecondGroup.setDisable(true);


    }

    public void onStudentButtonClick() {

        studentDetails.setDisable(false);

        FadeTransition studentDetailsTransition = new FadeTransition(Duration.seconds(3), studentDetails);
        studentDetailsTransition.setFromValue(0);
        studentDetailsTransition.setToValue(1);
        studentDetailsTransition.play();

        SecondGroup.setVisible(false);
        SecondGroup.setDisable(true);
    }

    public void studentButtonMouseEnter() {
        studentButtonDecrease.play();
    }

    public void studentButtonMouseExit() {
        studentButtonIncrease.play();
    }

    public void advisorButtonMouseEnter() {
        advisorButtonDecrease.play();
    }

    public void advisorButtonMouseExit() {
        advisorButtonIncrease.play();
    }



    //User Input Validation (Advisor)

    boolean allValidated = true;

    public void advisorFirstNameValidation() {

        if (advisorFirstName.getText().length() == 0){
            advisorFirstName.setStyle("-fx-border-color: red");
            allValidated = false;

        } else if (!advisorFirstName.getText().matches( "^[A-Za-z]*$")) {
            advisorFirstName.setStyle("-fx-border-color: red");
            allValidated = false;


        } else {
            advisorFirstName.setStyle("");

        }
    }

    public void advisorLastNameValidation() {

        if (advisorLastName.getText().length() == 0){
            advisorLastName.setStyle("-fx-border-color: red");
            allValidated = false;


        } else if (!advisorLastName.getText().matches( "^[A-Za-z]*$")) {
            advisorLastName.setStyle("-fx-border-color: red");
            allValidated = false;


        } else {
            advisorLastName.setStyle("");

        }
    }

    public void advisorUserNameValidation() {

        if (advisorUsername.getText().length() == 0) {
            advisorUsername.setStyle("-fx-border-color: red");

        } else {
            advisorUsername.setStyle("");
            allValidated = true;
        }

    }

    public void advisorEmailValidation() {

        if (advisorEmail.getText().length() == 0){
            advisorEmail.setStyle("-fx-border-color: red");
            allValidated = false;


        } else if (!advisorEmail.getText().matches( "^[A-Za-z]*$")) {
            advisorEmail.setStyle("-fx-border-color: red");
            allValidated = false;


        } else {
            advisorEmail.setStyle("");

        }

    }
}