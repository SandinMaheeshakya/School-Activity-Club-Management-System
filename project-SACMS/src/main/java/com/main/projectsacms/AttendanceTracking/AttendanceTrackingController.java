package com.main.projectsacms.AttendanceTracking;

import com.main.projectsacms.Database.Attendance.RetrieveData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceTrackingController implements PredefinedObjects {

    @FXML
    private AnchorPane EventsDisplayPageFour;

    @FXML
    private AnchorPane EventsDisplayPageOne;

    @FXML
    private AnchorPane EventsDisplayPageThree;

    @FXML
    private AnchorPane EventsDisplayPageTwo;

    @FXML
    private Pane PageFourSubEventPaneOne;

    @FXML
    private Pane PageFourSubEventPaneThree;

    @FXML
    private Pane PageFourSubEventPaneTwo;

    @FXML
    private Pane PageOneSubEventPaneOne;

    @FXML
    private Pane PageOneSubEventPaneThree;

    @FXML
    private Pane PageOneSubEventPaneTwo;

    @FXML
    private Pane PageThreeSubEventPaneOne;

    @FXML
    private Pane PageThreeSubEventPaneThree;

    @FXML
    private Pane PageThreeSubEventPaneTwo;

    @FXML
    private Pane PageTwoSubEventPaneOne;

    @FXML
    private Pane PageTwoSubEventPaneThree;

    @FXML
    private Pane PageTwoSubEventPaneTwo;

    @FXML
    private Pane StudentDetailsPaneFive;

    @FXML
    private Pane StudentDetailsPaneFour;

    @FXML
    private Pane StudentDetailsPaneOne;

    @FXML
    private Pane StudentDetailsPaneSix;

    @FXML
    private Pane StudentDetailsPaneThree;

    @FXML
    private Pane StudentDetailsPaneTwo;

    @FXML
    private Pane StudentDetailsPaneSeven;

    @FXML
    private Pane StudentDetailsPaneEight;

    @FXML
    private Pane StudentDetailsPaneNine;

    @FXML
    private Pane StudentDetailsPaneTen;

    @FXML
    private Pane StudentDetailsPaneEleven;

    @FXML
    private Pane StudentDetailsPaneTwelve;


    @FXML
    private Button changePageToLeftButton;

    @FXML
    private Button changePageToRightButton;

    @FXML
    private Button switchToPageFourButton;

    @FXML
    private Button switchToPageOneButton;

    @FXML
    private Button switchToPageThreeButton;

    @FXML
    private Button switchToPageTwoButton;


    @FXML
    private Group eventsGroup;

    @FXML
    private Group studentsGroup;

    @FXML
    private Label eventTypeLabel;

    private final HashMap<String, Pane> PaneMap = new HashMap<>();

    private final HashMap<String, Pane> studentPaneMap = new HashMap<>();

    private final HashMap<String, AnchorPane> Pages = new HashMap<>();

    private ArrayList<Map<String, String>> events = new ArrayList<>();

    // Getters and Setters to Pane Details
    public HashMap<String, Pane> getPaneMap() {
        return PaneMap;
    }

    public HashMap<String, AnchorPane> getPages() {
        return Pages;
    }

    public ArrayList<Map<String,String>> attendedStudents = new ArrayList<>();

    public void initialize() throws SQLException {

        //Adding the page elements to a Hashmap
        HashMapEventsPages();
        HashMapEventsPane();
        HashMapStudentsPane();

        events = RetrieveData.getEventsData();

        displayEvents();


    }

    public void HashMapEventsPages() {
        Pages.put("EventsDisplayPageOne", EventsDisplayPageOne);
        Pages.put("EventsDisplayPageTwo", EventsDisplayPageTwo);
        Pages.put("EventsDisplayPageThree", EventsDisplayPageThree);
        Pages.put("EventsDisplayPageFour", EventsDisplayPageFour);

    }

    public void HashMapEventsPane() {
        PaneMap.put("PageOneSubEventPaneOne", PageOneSubEventPaneOne);
        PaneMap.put("PageOneSubEventPaneTwo", PageOneSubEventPaneTwo);
        PaneMap.put("PageOneSubEventPaneThree", PageOneSubEventPaneThree);
        PaneMap.put("PageTwoSubEventPaneOne", PageTwoSubEventPaneOne);
        PaneMap.put("PageTwoSubEventPaneTwo", PageTwoSubEventPaneTwo);
        PaneMap.put("PageTwoSubEventPaneThree", PageTwoSubEventPaneThree);
        PaneMap.put("PageThreeSubEventPaneOne", PageThreeSubEventPaneOne);
        PaneMap.put("PageThreeSubEventPaneTwo", PageThreeSubEventPaneTwo);
        PaneMap.put("PageThreeSubEventPaneThree", PageThreeSubEventPaneThree);
        PaneMap.put("PageFourSubEventPaneOne", PageFourSubEventPaneOne);
        PaneMap.put("PageFourSubEventPaneTwo", PageFourSubEventPaneTwo);
        PaneMap.put("PageFourSubEventPaneThree", PageFourSubEventPaneThree);
    }

    public void HashMapStudentsPane(){
        studentPaneMap.put("StudentDetailsPaneOne", StudentDetailsPaneOne);
        studentPaneMap.put("StudentDetailsPaneTwo", StudentDetailsPaneTwo);
        studentPaneMap.put("StudentDetailsPaneThree", StudentDetailsPaneThree);
        studentPaneMap.put("StudentDetailsPaneFour", StudentDetailsPaneFour);
        studentPaneMap.put("StudentDetailsPaneFive", StudentDetailsPaneFive);
        studentPaneMap.put("StudentDetailsPaneSix", StudentDetailsPaneSix);

    }

    public ArrayList<String> getEventPages(String[] numbers) {
        ArrayList<String> panes = new ArrayList<>();

        for (String number : numbers) {
            int count = 0;
            while (count < 3) {
                panes.add("Page" + number + "SubEventPane" + numbers[count]);
                count++;
            }
        }
        return panes;
    }

    public ArrayList<String> getStudentPanes(String[] numbers) {
        ArrayList<String> StudentDetailsPanes = new ArrayList<>();

        for (String number : numbers) {
            StudentDetailsPanes.add("StudentDetailsPane" + number);
        }

        System.out.println(StudentDetailsPanes);
        return StudentDetailsPanes;
    }



    //Events Data Add
    public void putClubImage(Pane currentPane, String ImageURL) {
        Image ClubImage = new Image(ImageURL);

        // Create an ImageView with the loaded ClubImage
        ImageView imageView = new ImageView(ClubImage);

        // Set position and size for the ImageView (optional)
        imageView.setLayoutX(14);
        imageView.setLayoutY(8);
        imageView.setFitWidth(83);
        imageView.setFitHeight(83);

        // Add the ImageView to the Pane
        currentPane.getChildren().add(imageView);

    }

    public void putEventName(Pane currentPane, String eventName) {
        Label eventNameLabel = new Label();
        eventNameLabel.setText(eventName);
        eventNameLabel.setFont(new Font("Calibri", 18));
        eventNameLabel.setTextFill(Color.WHITE);
        eventNameLabel.setLayoutX(133);
        eventNameLabel.setLayoutY(6);

        currentPane.getChildren().add(eventNameLabel);

    }

    public void putEventDate(Pane currentPane, String eventTime) {
        Label eventTimeLabel = new Label();
        eventTimeLabel.setText(eventTime);
        eventTimeLabel.setFont(new Font("Calibri", 18));
        eventTimeLabel.setTextFill(Color.WHITE);
        eventTimeLabel.setLayoutX(729);
        eventTimeLabel.setLayoutY(6);

        currentPane.getChildren().add(eventTimeLabel);

    }

    public void putEventDetails(Pane currentPane, String eventDescription) {
        Label eventDescriptionLabel = new Label();
        eventDescriptionLabel.setText(eventDescription);
        eventDescriptionLabel.setFont(new Font("Calibri", 18));
        eventDescriptionLabel.setTextFill(Color.WHITE);
        eventDescriptionLabel.setLayoutX(201);
        eventDescriptionLabel.setLayoutY(50);

        currentPane.getChildren().add(eventDescriptionLabel);

    }

    public void putClubName(Pane currentPane, String clubName) {
        Label clubNameLabel = new Label();
        clubNameLabel.setText(clubName);
        clubNameLabel.setFont(new Font("Calibri", 18));
        clubNameLabel.setTextFill(Color.WHITE);
        clubNameLabel.setLayoutX(470);
        clubNameLabel.setLayoutY(6);

        currentPane.getChildren().add(clubNameLabel);

    }

    public void clearPanes(){
        StudentDetailsPaneOne.getChildren().clear();
        StudentDetailsPaneTwo.getChildren().clear();
        StudentDetailsPaneThree.getChildren().clear();
        StudentDetailsPaneFour.getChildren().clear();
        StudentDetailsPaneFive.getChildren().clear();
        StudentDetailsPaneSix.getChildren().clear();

    }


    public void displayEvents() throws SQLException {
        int count = 0;
        for (Map<String, String> event : events) {

            Pane currentPane = PaneMap.get(getEventPages(eventPageNumbers).get(count));

            //Setting Attributes
            putEventName(currentPane, event.get("eventName"));
            putEventDetails(currentPane,event.get("eventsDescription"));
            putClubName(currentPane,event.get("clubName"));
            putEventDate(currentPane,event.get("eventDate"));

            count++;
        }
    }

    // Put Student Details

    public void putStudentId(Pane currentPane,String studentID){
        Label studentIDLabel = new Label();
        studentIDLabel.setText(studentID);
        studentIDLabel.setFont(new Font("Calibri", 18));
        studentIDLabel.setTextFill(Color.BLACK);
        studentIDLabel.setLayoutX(68);
        studentIDLabel.setLayoutY(21);
        currentPane.getChildren().add(studentIDLabel);

    }
    public void putStudentName(Pane currentPane,String studentName){
        Label studentNameLabel = new Label();
        studentNameLabel.setText(studentName);
        studentNameLabel.setFont(new Font("Calibri", 18));
        studentNameLabel.setTextFill(Color.BLACK);
        studentNameLabel.setLayoutX(183);
        studentNameLabel.setLayoutY(17);
        currentPane.getChildren().add(studentNameLabel);

    }

    public void putStudentGrade(Pane currentPane,String studentGrade){
        Label studentGradeLabel = new Label();
        studentGradeLabel.setText(studentGrade);
        studentGradeLabel.setFont(new Font("Calibri", 18));
        studentGradeLabel.setTextFill(Color.BLACK);
        studentGradeLabel.setLayoutX(458);
        studentGradeLabel.setLayoutY(17);
        currentPane.getChildren().add(studentGradeLabel);

    }

    public void putStudentRegistrationStatus(Pane currentPane,String studentRegistered){
        Label studentRegisterLabel = new Label();
        studentRegisterLabel.setText(studentRegistered);
        studentRegisterLabel.setFont(new Font("Calibri", 18));
        studentRegisterLabel.setTextFill(Color.BLACK);
        studentRegisterLabel.setLayoutX(623);
        studentRegisterLabel.setLayoutY(17);
        currentPane.getChildren().add(studentRegisterLabel);

    }

    ArrayList<ArrayList<ChoiceBox<String>>> groupedChoicesBoxList = new ArrayList<>();
    ArrayList<ChoiceBox<String>> choiceBoxArrayList = new ArrayList<>();
    public ChoiceBox<String> putStudentAttendance(Pane currentPane) {

        ChoiceBox<String> studentAttendanceCheck = new ChoiceBox<>();
        studentAttendanceCheck.setValue("Absent");

        ObservableList<String> choices = FXCollections.observableArrayList(
                "Present",
                "Absent"
        );
        studentAttendanceCheck.setItems(choices);
        studentAttendanceCheck.setLayoutX(693);
        studentAttendanceCheck.setLayoutY(17);
        currentPane.getChildren().add(studentAttendanceCheck);

        return studentAttendanceCheck;

    }


    public void saveButtonOnClick() throws SQLException {

        OnlineAttendance.saveAttendance(OnlineAttendance.trackAttendance(choiceBoxArrayList,studentIDMapList,currentEventID));
    }

    String currentEventID;
    String eventType;

    ArrayList<HashMap<String,String>> studentIDMapList =  new ArrayList<>();

    public void displayStudents(int groupNumber) throws SQLException {

        attendedStudents = new ArrayList<>();
        //Need to take DB data as an ArrayList of HashMaps
        int studentNumber = 0;
        System.out.println(RetrieveData.getStudentsCount());
        choiceBoxArrayList = new ArrayList<>();


        while (studentNumber < 6) {

            HashMap<String, String> studentIDMap;
            try {


                studentIDMap = new HashMap<>();

                OnlineAttendance attendance = new OnlineAttendance(groupNumber, studentNumber, true, "23:10");

                //Creating the object
                Pane currentPane = studentPaneMap.get(getStudentPanes(studentPaneNumbers).get(studentNumber));

                //Attendance Check
                Map<String, String> attendedStudentsDetails = new HashMap<>();

                //Adding the Details
                putStudentId(currentPane, attendance.getStudentID());
                putStudentName(currentPane, attendance.getStudentName());
                putStudentGrade(currentPane, attendance.getStudentGrade());


                //Getting the data from Attendance Class
                String attendanceStatus = Attendance.retrieveAttendanceData(currentEventID,attendance.getStudentID());

                ChoiceBox<String> choice = putStudentAttendance(currentPane);

                choice.setValue(attendanceStatus);

                attendedStudents.add(attendedStudentsDetails);
                choiceBoxArrayList.add(choice);

                choice.setValue(choiceBoxArrayList.get(studentNumber).getValue());
                studentIDMap.put("studentID", attendance.getStudentID());

                studentNumber++;

            } catch (IndexOutOfBoundsException e) {
                break;
            }
            studentIDMapList.add(studentIDMap);

        }

    }


    public void switchToPageOne() throws SQLException {
        studentIDMapList.clear();


        if (eventsGroup.isVisible()) {
            EventsDisplayPageOne.setVisible(true);
            EventsDisplayPageTwo.setVisible(false);
            EventsDisplayPageThree.setVisible(false);
            EventsDisplayPageFour.setVisible(false);

        } else {
            clearPanes();
            displayStudents(0);

        }
    }

        public void switchToPageTwo(ActionEvent actionEvent) throws SQLException {
            studentIDMapList.clear();
            if (eventsGroup.isVisible()) {
                EventsDisplayPageOne.setVisible(false);
                EventsDisplayPageTwo.setVisible(true);
                EventsDisplayPageThree.setVisible(false);
                EventsDisplayPageFour.setVisible(false);

            }else {
                clearPanes();
                displayStudents(1);

            }
        }

    public void switchToPageThree() throws SQLException {

        if (eventsGroup.isVisible()) {
            EventsDisplayPageOne.setVisible(false);
            EventsDisplayPageTwo.setVisible(false);
            EventsDisplayPageThree.setVisible(true);
            EventsDisplayPageFour.setVisible(false);
        } else {
            clearPanes();
            displayStudents(2);
        }
    }

    public void switchToPageFour() throws SQLException {
        if (eventsGroup.isVisible()) {
            EventsDisplayPageOne.setVisible(false);
            EventsDisplayPageTwo.setVisible(false);
            EventsDisplayPageThree.setVisible(false);
            EventsDisplayPageFour.setVisible(true);
        }else {
            clearPanes();
            displayStudents(3);
        }
    }

    public void switchToPageFive() throws SQLException {
        if (eventsGroup.isVisible()) {
            EventsDisplayPageOne.setVisible(false);
            EventsDisplayPageTwo.setVisible(false);
            EventsDisplayPageThree.setVisible(false);
            EventsDisplayPageFour.setVisible(true);
        }else {
            clearPanes();
            displayStudents(4);
        }
    }

    // Process is to Check First the Event Type and choose the correct pane type. After that Display Student Details in that fill
    // Database Method to Retrieve data and Save in ArrayList.

    //Attendance Object to Access elements in Attendance Class

    public void eventOneOnClick() throws SQLException {
        eventsGroup.setVisible(false);

        currentEventID = Attendance.checkEventID(0);

        //Store data
        Attendance.setCurrentEventNumber(0);

        eventType = events.get(0).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventTwoOnClick() throws SQLException {

        currentEventID = Attendance.checkEventID(1);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(1);

        eventType = events.get(1).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventThreeOnClick() throws SQLException {
        eventsGroup.setVisible(false);

        currentEventID = Attendance.checkEventID(2);

        //Store data
        Attendance.setCurrentEventNumber(2);

        eventType = events.get(2).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventFourOnClick() throws SQLException {

        currentEventID = Attendance.checkEventID(3);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(3);

        eventType = events.get(3).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventFiveOnClick() throws SQLException {

        currentEventID = Attendance.checkEventID(4);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(4);

        eventType = events.get(4).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);


    }

    public void eventSixOnClick() throws SQLException {

        currentEventID = Attendance.checkEventID(5);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(5);

        eventType = events.get(5).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Events");
        }else {
            eventTypeLabel.setText("Physical Events");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);



    }

    public void eventSevenOnClick() throws SQLException {

        currentEventID = Attendance.checkEventID(6);

    }

    public void eventEightOnClick() throws SQLException {
        currentEventID = Attendance.checkEventID(7);

    }

    public void eventNineOnClick() throws SQLException {
        currentEventID = Attendance.checkEventID(8);

    }

    public void eventTenOnClick() throws SQLException {
        currentEventID = Attendance.checkEventID(9);

    }

    public void eventElevenOnClick() throws SQLException {
        currentEventID = Attendance.checkEventID(10);

    }

    public void eventTwelveOnClick() throws SQLException {
        currentEventID = Attendance.checkEventID(11);

    }
}

