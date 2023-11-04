package com.main.projectsacms;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    private PasswordField advisorPassword;

    @FXML
    private PasswordField advisorConfirmPassword;

    @FXML
    private TextField studentEmailAddress;

    @FXML
    private TextField studentUsername;

    @FXML
    private TextField studentFirstName;

    @FXML
    private TextField studentLastName;

    @FXML
    private TextField studentID;

    @FXML
    private PasswordField studentPassword;

    @FXML
    private PasswordField studentConfirmPassword;

    @FXML
    private ChoiceBox<String> advisorDepartment;

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


        // Add choices to the ChoiceBox
        advisorDepartment.getItems().addAll("IT", "Finance", "Sports", "History","Geography","Maths","Science");
        advisorDepartment.setValue("IT");


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

    boolean allValidatedAdvisor = true;

    public void advisorFirstNameValidation() {

        if (advisorFirstName.getText().length() == 0){
            advisorFirstName.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;

        } else if (!advisorFirstName.getText().matches( "^[A-Za-z]*$")) {
            advisorFirstName.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else {
            advisorFirstName.setStyle("");

        }
    }

    public void advisorLastNameValidation() {

        if (advisorLastName.getText().length() == 0){
            advisorLastName.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else if (!advisorLastName.getText().matches( "^[A-Za-z]*$")) {
            advisorLastName.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else {
            advisorLastName.setStyle("");

        }
    }

    public void advisorUserNameValidation() {

        if (advisorUsername.getText().length() == 0) {
            advisorUsername.setStyle("-fx-border-color: red");

        } else {
            advisorUsername.setStyle("");
            allValidatedAdvisor = true;
        }

    }

    public void advisorEmailValidation() {

        if (advisorEmail.getText().length() == 0){
            advisorEmail.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else if (!advisorEmail.getText().matches( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            advisorEmail.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else {
            advisorEmail.setStyle("");

        }

    }

    public void advisorTeachingIDValidation() {

        if (advisorTeachingID.getText().length() == 0){
            advisorTeachingID.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else if (!advisorTeachingID.getText().matches( "^[A-Za-z]{2}[0-9]{5}$")) {
            advisorTeachingID.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;


        } else {
            advisorTeachingID.setStyle("");

        }

    }

    public void advisorPasswordValidation() {


        if (! advisorConfirmPassword.getText().matches(advisorPassword.getText())) {
            advisorConfirmPassword.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;

        } else {
            advisorConfirmPassword.setStyle("");
        }
    }

    //User Input Validation (Student)

    boolean allValidatedStudent = true;

    public void studentFirstNameValidation() {

        if (studentFirstName.getText().length() == 0){
            studentFirstName.setStyle("-fx-border-color: red");
            allValidatedStudent = false;

        } else if (!studentFirstName.getText().matches( "^[A-Za-z]*$")) {
            studentFirstName.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else {
            studentFirstName.setStyle("");

        }
    }

    public void studentLastNameValidation() {

        if (studentLastName.getText().length() == 0){
            studentLastName.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else if (!studentLastName.getText().matches( "^[A-Za-z]*$")) {
            studentLastName.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else {
            studentLastName.setStyle("");

        }
    }

    public void studentUserNameValidation() {

        if (studentUsername.getText().length() == 0) {
            studentUsername.setStyle("-fx-border-color: red");

        } else {
            studentUsername.setStyle("");
            allValidatedStudent = true;
        }

    }

    public void studentEmailValidation() {

        if (studentEmailAddress.getText().length() == 0){
            studentEmailAddress.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else if (!studentEmailAddress.getText().matches( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            studentEmailAddress.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else {
            studentEmailAddress.setStyle("");

        }

    }

    public void studentIDValidation() {

        if (studentID.getText().length() == 0){
            studentID.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else if (!studentID.getText().matches( "^[A-Za-z]{2}[0-9]{5}$")) {
            studentID.setStyle("-fx-border-color: red");
            allValidatedStudent = false;


        } else {
            studentID.setStyle("");

        }

    }

    public void studentPasswordValidation() {


        if (! studentConfirmPassword.getText().matches(studentPassword.getText())) {
            studentConfirmPassword.setStyle("-fx-border-color: red");
            allValidatedStudent = false;

        } else {
            studentConfirmPassword.setStyle("");
        }
    }


}