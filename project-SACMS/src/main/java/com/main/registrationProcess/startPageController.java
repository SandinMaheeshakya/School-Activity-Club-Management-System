package com.main.registrationProcess;

import com.main.Database.UserLogin.AdvisorReportDatabase;
import com.main.Database.UserLogin.DatabaseConnection;
import com.main.Database.UserLogin.StudentReportDatabase;
import com.main.EventCreation.Views.ViewEventsPanel;
import com.main.clubcreation.DisplayClubs;
import com.main.mainAttendance.AttendancePage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.main.Database.UserLogin.DatabaseConnection.addClubStudentDataToDatabase;
import static com.main.Database.UserLogin.DatabaseConnection.joinClub;

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
    private Group ThirdGroup;
    @FXML
    private Group advisorLogin;
    @FXML
    private Group StudentLogin;
    @FXML
    private Group advisorDetails;
    @FXML
    private Group joinClub;
    @FXML
    private Group DashboardPage;

    @FXML
    private Group homeGroup;
    @FXML
    private Group clubGroup;
    @FXML
    private Group eventGroup;
    @FXML
    private Group attendenceGroup;
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
    private ChoiceBox<String> advisorDepartment;
    @FXML
    private DatePicker advisorDOB;
    @FXML
    private Group studentDetails;
    @FXML
    private Button advisorRegisterButton;
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
    private TextField studentPassword;

    @FXML
    private TextField studentConfirmPassword;

    @FXML
    private ChoiceBox<String> studentGrade;

    @FXML
    private DatePicker studentDOB;
    @FXML
    private TextField advisorUsernameLogin;
    @FXML
    private TextField advisorPasswordLogin;
    @FXML
    private TextField studentUsernameLogin;
    @FXML
    private TextField studentPasswordLogin;

    //JoinTable details
    @FXML
    private TableView<CreateClub> Tablejoin;
    @FXML
    private TableColumn<CreateClub, String> clubIdJoin;

    @FXML
    private TableColumn<CreateClub, String> clubNameJoin;

    @FXML
    private TableColumn<CreateClub, String> clubDescriptionJoin;

    @FXML
    private TableColumn<CreateClub, String> clubCategoryJoin;

    @FXML
    private TableColumn<CreateClub, String> clubAdvisorJoin;

    @FXML
    private TableColumn<CreateClub, String> clubEmailJoin;

    @FXML
    private TableColumn<CreateClub, Integer> clubContactJoin;

    @FXML
    private AnchorPane clubDisplayPage;

    @FXML
    private AnchorPane eventsDisplayPage;

    @FXML
    private TabPane eventsDisplayTab;

    @FXML
    private Button clubJoin;

    @FXML
    private Group advisorReportGroup;

    @FXML
    private Group studentReportGroup;

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


    public TextField getAdvisorEmail() {
        return advisorEmail;
    }

    public TextField getAdvisorUsername() {
        return advisorUsername;
    }

    public TextField getAdvisorFirstName() {
        return advisorFirstName;
    }

    public TextField getAdvisorLastName() {
        return advisorLastName;
    }

    public TextField getAdvisorTeachingID() {
        return advisorTeachingID;
    }

    public TextField getAdvisorPassword() {
        return advisorPassword;
    }

    public TextField getAdvisorUsernameLogin() {
        return advisorUsernameLogin;
    }

    public TextField getAdvisorPasswordLogin() {
        return advisorPasswordLogin;
    }

    String currentMember;
    ArrayList<Student> studentDetail = new ArrayList<>();

    public startPageController() {
    }

    public static boolean backStatus;
    public void initialize(){

        if (backStatus){
            DashboardPage.setVisible(true);
        }

        gradeSetValue();
        departmentSetValue();

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

        // Set up the columns
        clubIdJoin.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameJoin.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescriptionJoin.setCellValueFactory(new PropertyValueFactory<>("description"));
        clubCategoryJoin.setCellValueFactory(new PropertyValueFactory<>("clubCategory"));
        clubAdvisorJoin.setCellValueFactory(new PropertyValueFactory<>("clubAdvisor"));
        clubEmailJoin.setCellValueFactory(new PropertyValueFactory<>("email"));
        clubContactJoin.setCellValueFactory(new PropertyValueFactory<>("contact"));

        // Load club data into the table
        loadClubData();

        // Set up the event handler for joining a club
        clubJoin.setOnAction(event -> onClickJoinClub());

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

    public void studentButtonMouseEnter(MouseEvent mouseEvent) {
        studentButtonDecrease.play();
    }

    public void studentButtonMouseExit(MouseEvent mouseEvent) {
        studentButtonIncrease.play();
    }

    public void advisorButtonMouseEnter(MouseEvent mouseEvent) {
        advisorButtonDecrease.play();
    }

    public void advisorButtonMouseExit(MouseEvent mouseEvent) {
        advisorButtonIncrease.play();
    }

    //User Input Validation (Advisor)

    boolean allValidatedAdvisor = true;
    public void departmentSetValue() {
        // Other initialization code...

        // Add values to the advisorDepartment ChoiceBox
        ObservableList<String> departmentOptions = FXCollections.observableArrayList(
                "IT",
                "Agriculture",
                "Accounting",
                "Science"
        );
        advisorDepartment.setItems(departmentOptions);

    }
    public void gradeSetValue(){
        ObservableList<String> gradeOptions = FXCollections.observableArrayList(
                "Grade 6",
                "Grade 7",
                "Grade 8",
                "Grade 9",
                "Grade 10",
                "Grade 11",
                "Grade 12",
                "Grade 13"
        );
        studentGrade.setItems(gradeOptions);
    }

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
    public void advisorDepartmentValidation() {
        if (advisorDepartment.getValue() == null || advisorDepartment.getValue().isEmpty()) {
            advisorDepartment.setStyle("-fx-border-color: red");
            allValidatedAdvisor = false;
        } else {
            advisorDepartment.setStyle("");
            allValidatedAdvisor = true;
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
    public void studentGradeValidation() {
        if (studentGrade.getValue() == null || studentGrade.getValue().isEmpty()) {
            studentGrade.setStyle("-fx-border-color: red");
            allValidatedStudent = false;
        } else {
            studentGrade.setStyle("");
            allValidatedStudent = true;
        }
    }
    @FXML
    public void OnClickAdvisorRegister(ActionEvent actionEvent) throws SQLException {
        // Call validation methods
        advisorTeachingIDValidation();
        advisorFirstNameValidation();
        advisorLastNameValidation();
        advisorEmailValidation();
        advisorUserNameValidation();
        advisorPasswordValidation();
        advisorDepartmentValidation();

        // Check if all validations passed
        if (allValidatedAdvisor) {
            // If all validations passed, proceed with registration
            String AID = advisorTeachingID.getText();
            String FirstnameA = advisorFirstName.getText();
            String LastnameA = advisorLastName.getText();
            String UsernameA = advisorUsername.getText();
            String BirthA = String.valueOf(advisorDOB.getValue());
            String EmailA = advisorEmail.getText();
            String Departments = String.valueOf(advisorDepartment.getValue());
            String Pass1A = advisorPassword.getText();
            Advisor advisor = new Advisor(AID, FirstnameA, LastnameA, UsernameA, BirthA, EmailA, Departments, Pass1A);
            DatabaseConnection.InsertAdvisor(advisor.getAdvisorId(), advisor.getFirstName(), advisor.getLastName(), advisor.getUserName(), advisor.getDob(), advisor.getEmail(), advisor.getDepartment(), advisor.getPassword());
        } else {
            // If any validation failed, display an error message or take appropriate action
            // For example: show an alert or update a status message on the UI
            System.out.println("Student registration failed due to validation errors.");
        }
    }
    @FXML
    public void OnClickStudentRegister(ActionEvent actionEvent) throws SQLException {
        studentFirstNameValidation();
        studentLastNameValidation();
        studentEmailValidation();
        studentUserNameValidation();
        studentPasswordValidation();
        studentIDValidation();
        studentGradeValidation();

        if (allValidatedStudent) {
            String SID = studentID.getText();
            String Firstname = studentFirstName.getText();
            String LastName = studentLastName.getText();
            String UserName = studentUsername.getText();
            String Birth =  studentEmailAddress.getText();
            String Email = String.valueOf(studentDOB.getValue());
            int Grades = Integer.parseInt(studentGrade.getValue());
            String Pass1 = studentPassword.getText();
            Student student = new Student(SID, Firstname, LastName, UserName, Birth, Email,  Grades, Pass1 );
            DatabaseConnection.insertStudent(student.getStudentId(), student.getFirstName(), student.getLastName(), student.getUserName(), student.getDob(), student.getEmail(), student.getGrade(), student.getPassword());
        } else {
            // If any validation failed, display an error message or take appropriate action
            // For example: show an alert or update a status message on the UI
            System.out.println("Advisor registration failed due to validation errors.");
        }
    }
    @FXML
    public void OnloginButtonClick(ActionEvent actionEvent) {
        FirstGroup.setOpacity(0);
        ThirdGroup.setOpacity(100);
        ThirdGroup.setDisable(false);

        FadeTransition thirdGroupTransition = new FadeTransition(Duration.seconds(3), ThirdGroup);
        thirdGroupTransition.setFromValue(0);
        thirdGroupTransition.setToValue(1);
        thirdGroupTransition.play();


    }

    public void onAdvisorLoginButtonClick(ActionEvent actionEvent) {
        FadeTransition advisorLoginTransition = new FadeTransition(Duration.seconds(3), advisorLogin);
        advisorLoginTransition.setFromValue(0);
        advisorLoginTransition.setToValue(1);
        advisorLoginTransition.play();

        advisorLogin.setDisable(false);

        ThirdGroup.setVisible(false);
        ThirdGroup.setDisable(true);

        currentMember = "Advisor";

    }

    public void onStudentLoginButtonClick(ActionEvent actionEvent) {
        FadeTransition studentLoginTransition = new FadeTransition(Duration.seconds(3), StudentLogin);
        studentLoginTransition.setFromValue(0);
        studentLoginTransition.setToValue(1);
        studentLoginTransition.play();

        StudentLogin.setDisable(false);

        ThirdGroup.setVisible(false);
        ThirdGroup.setDisable(true);


        advisorReportGroup.setVisible(false);
        studentReportGroup.setVisible(false);

        currentMember = "Student";

    }

    public void onclickStudentConfirmLogin(ActionEvent actionEvent) {
            String username = studentUsernameLogin.getText();
            String password = studentPasswordLogin.getText();

            if (DatabaseConnection.validateStudentLogin(username, password)) {
                // Successful login
                // Add your logic to navigate to the student's home/dashboard
                System.out.println("Student login successful!");

                StudentLogin.setOpacity(0);
                FirstGroup.setOpacity(0);
                DashboardPage.setVisible(true);
                attendenceGroup.setVisible(false);

                eventGroup.setLayoutX(426);
                eventGroup.setLayoutY(340);

                clubGroup.setLayoutX(717);
                clubGroup.setLayoutY(340);


                DashboardPage.setDisable(false);

                FadeTransition DashboardTransition = new FadeTransition(Duration.seconds(3), DashboardPage);
                DashboardTransition.setFromValue(0);
                DashboardTransition.setToValue(1);
                DashboardTransition.play();

            } else {
                // Failed login
                // Display an error message or take appropriate action
                System.out.println("Student login failed. Invalid credentials.");

                studentUsernameLogin.setStyle("-fx-border-color: red");
                studentPasswordLogin.setStyle("-fx-border-color: red");
            }

        }

    public void onclickAdvisorConfirmLogin(ActionEvent actionEvent) throws SQLException {
        String username = advisorUsernameLogin.getText();
        String password = advisorPasswordLogin.getText();

        if (DatabaseConnection.validateAdvisorLogin(username, password)) {
            // Successful login
            // Add your logic to navigate to the advisor's home/dashboard
            System.out.println("Advisor login successful!");

            advisorLogin.setOpacity(0);
            FirstGroup.setOpacity(0);
            DashboardPage.setVisible(true);

            DashboardPage.setDisable(false);

            FadeTransition DashboardTransition = new FadeTransition(Duration.seconds(3), DashboardPage);
            DashboardTransition.setFromValue(0);
            DashboardTransition.setToValue(1);
            DashboardTransition.play();
        } else {
            // Failed login
            // Display an error message or take appropriate action
            System.out.println("Advisor login failed. Invalid credentials.");

            advisorUsernameLogin.setStyle("-fx-border-color: red");
            advisorPasswordLogin.setStyle("-fx-border-color: red");

        }
    }

    private void onClickJoinClub() {
        addClubStudentDataToDatabase(Tablejoin);
    }

    private void loadClubData() {
        // Fetch club data from the database
        List<CreateClub> clubs = DatabaseConnection.getAllClubs();

        // Populate the table with club data
        Tablejoin.getItems().addAll(clubs);
    }

    public void onClickJoinClub(ActionEvent actionEvent) {
        CreateClub selectedClub = Tablejoin.getSelectionModel().getSelectedItem();
        if (selectedClub != null) {
            // Get student ID from your authentication system (replace "123" with actual student ID)
            String studentId = "123";
            String clubId = selectedClub.getClubID();
            String clubName = selectedClub.getClubName();


            // Call the method to join the club
            joinClub(studentId, clubId);

            // You can add further logic here, e.g., display a confirmation message
            System.out.println("Joined the club: " + clubName);
        }

    }

    public void onclickViewJoinTables() throws IOException {

        if ("Student".equals(currentMember)) {

            DashboardPage.setVisible(false);
            clubDisplayPage.setVisible(true);
            clubDisplayPage.setDisable(false);
            FadeTransition clubDisplayPageTransition = new FadeTransition(Duration.seconds(3), clubDisplayPage);
            clubDisplayPageTransition.setFromValue(0);
            clubDisplayPageTransition.setToValue(1);
            clubDisplayPageTransition.play();




        }else {
            DisplayClubs displayClubs = new DisplayClubs();
            Stage stage = new Stage();
            displayClubs.start(stage);
        }

    }

    public void onAttendanceButtonClick(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        AttendancePage attendancePage = new AttendancePage();
        attendancePage.start(stage);
    }

    public void onClickBackButtonInStudentChoosingClubs(){
        DashboardPage.setVisible(true);
        clubDisplayPage.setVisible(false);
    }

    public void onEventButtonClick(){
        eventsDisplayPage.setVisible(true);

        Tab viewEventsTab = new Tab("View Events");
        ViewEventsPanel viewEventPanel = new ViewEventsPanel();
        viewEventsTab.setContent(viewEventPanel.getPanel());
        viewEventsTab.setClosable(false);

        eventsDisplayTab.getTabs().add(viewEventsTab);
    }

    public void onBackButtonClickInEvents() throws IOException {
        backStatus = true;

        SACMS sacms = new SACMS();
        Stage stage = new Stage();
        sacms.start(stage);
    }

    public void onGenerateAdvisorReportClick(){

        // Generate and display the report
        List<Map<String,String>> allAdvisorData = AdvisorReportDatabase.getAdvisorDetails();
        AdvisorReport generatedAdvisorDetailsReport = new AdvisorReport();
        generatedAdvisorDetailsReport.generateAdvisorReport(allAdvisorData);

    }

    public void onGenerateStudentReportClick(){

        // Generate and display the report
        List<Map<String,String>> allStudentData = StudentReportDatabase.getStudentDetails();
        StudentReport generatedStudentDetailsReport = new StudentReport();
        generatedStudentDetailsReport.generateStudentReport(allStudentData);

    }
}
