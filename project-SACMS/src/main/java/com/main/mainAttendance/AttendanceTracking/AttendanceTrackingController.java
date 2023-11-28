package com.main.mainAttendance.AttendanceTracking;

import com.main.Database.Attendance.AttendanceReportDatabase;
import com.main.Database.Attendance.RetrieveData;
import com.main.registrationProcess.SACMS;
import com.main.registrationProcess.startPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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

    @FXML
    private Label meetingTimeOrVenueLabel;

    @FXML
    private DatePicker filterEventDateFrom;

    @FXML
    private DatePicker filterEventDateTo;

    @FXML
    private ChoiceBox<String> clubChoosingDropBox;

    private final HashMap<String, Pane> PaneMap = new HashMap<>();

    private final HashMap<String, Pane> studentPaneMap = new HashMap<>();

    private final HashMap<String, AnchorPane> Pages = new HashMap<>();

    private static ArrayList<Map<String, String>> events = new ArrayList<>();

    public static ArrayList<Map<String, String>> getEvents() {
        return events;
    }

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


        for (Map<String,String> event : events){
            String clubName = event.get("clubName");
            if (!clubChoosingDropBox.getItems().contains(clubName)) {
                clubChoosingDropBox.getItems().add(event.get("clubName"));
            }
        }

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

    public void clearPanesStudents(){
        StudentDetailsPaneOne.getChildren().clear();
        StudentDetailsPaneTwo.getChildren().clear();
        StudentDetailsPaneThree.getChildren().clear();
        StudentDetailsPaneFour.getChildren().clear();
        StudentDetailsPaneFive.getChildren().clear();
        StudentDetailsPaneSix.getChildren().clear();

    }

    public void clearPanesEvents(){
        PageOneSubEventPaneOne.getChildren().clear();
        PageOneSubEventPaneTwo.getChildren().clear();
        PageOneSubEventPaneThree.getChildren().clear();
        PageTwoSubEventPaneOne.getChildren().clear();
        PageTwoSubEventPaneTwo.getChildren().clear();
        PageTwoSubEventPaneThree.getChildren().clear();
        PageThreeSubEventPaneOne.getChildren().clear();
        PageThreeSubEventPaneTwo.getChildren().clear();
        PageThreeSubEventPaneThree.getChildren().clear();
        PageFourSubEventPaneOne.getChildren().clear();
        PageFourSubEventPaneTwo.getChildren().clear();
        PageFourSubEventPaneThree.getChildren().clear();


    }

    public void onResetButtonClick() throws SQLException {
        clubChoosingDropBox.setValue(null);
        filterEventDateTo.setValue(null);
        filterEventDateFrom.setValue(null);

        clearPanesEvents();
        displayEvents();
    }


    public void onFilterButtonClick() throws SQLException, IOException {

        clearPanesEvents();
        displayEvents();

    }

    public void displayEvents() throws SQLException {

        events = RetrieveData.getEventsData();

        int count = 0;
        int eventCount = 0;
        while (eventCount < events.size()) {

            Pane currentPane = PaneMap.get(getEventPages(eventPageNumbers).get(count));


            //Filtering Process Begun
            if (clubChoosingDropBox.getValue() != null) {

                if (filterEventDateFrom.getValue() != null && filterEventDateTo.getValue() != null && clubChoosingDropBox.getValue().equals(events.get(eventCount).get("clubName"))) {

                    LocalDate eventBeforeDate = filterEventDateFrom.getValue();
                    LocalDate eventAfterDate = filterEventDateTo.getValue();
                    LocalDate currentEventDate = LocalDate.parse(events.get(eventCount).get("eventDate"));

                    if (!currentEventDate.isBefore(eventBeforeDate) && !currentEventDate.isAfter(eventAfterDate)){

                        //Setting Attributes
                        putEventName(currentPane, events.get(eventCount).get("eventName"));
                        putEventDetails(currentPane, events.get(eventCount).get("eventsDescription"));
                        putClubName(currentPane, events.get(eventCount).get("clubName"));
                        putEventDate(currentPane, events.get(eventCount).get("eventDate"));

                        //Put Images
                        if (Attendance.checkEventType(eventCount).equals("Online")){
                            putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\online logo.png");
                        }else {
                            putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\physical event logo.png");

                        }
                        count++;
                        eventCount++;

                    }else {
                    events.remove(eventCount);
                }

                }
                else if (clubChoosingDropBox.getValue().equals(events.get(eventCount).get("clubName"))) {

                    //Setting Attributes
                    putEventName(currentPane, events.get(eventCount).get("eventName"));
                    putEventDetails(currentPane, events.get(eventCount).get("eventsDescription"));
                    putClubName(currentPane, events.get(eventCount).get("clubName"));
                    putEventDate(currentPane, events.get(eventCount).get("eventDate"));


                    //Put Images
                    if (Attendance.checkEventType(eventCount).equals("Online")){
                        putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\online logo.png");
                    }else {
                        putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\physical event logo.png");

                    }

                    count++;
                    eventCount++;

                }else {
                    events.remove(eventCount);
                }

            } else if (filterEventDateFrom.getValue() != null && filterEventDateTo.getValue() != null) {
                LocalDate eventBeforeDate = filterEventDateFrom.getValue();
                LocalDate eventAfterDate  = filterEventDateTo.getValue();
                LocalDate currentEventDate = LocalDate.parse(events.get(eventCount).get("eventDate"));

                if (!currentEventDate.isBefore(eventBeforeDate) && !currentEventDate.isAfter(eventAfterDate)){

                    //Setting Attributes
                    putEventName(currentPane, events.get(eventCount).get("eventName"));
                    putEventDetails(currentPane, events.get(eventCount).get("eventsDescription"));
                    putClubName(currentPane, events.get(eventCount).get("clubName"));
                    putEventDate(currentPane, events.get(eventCount).get("eventDate"));

                    //Put Images
                    if (Attendance.checkEventType(eventCount).equals("Online")){
                        putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\online logo.png");
                    }else {
                        putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\projectsacms\\Icons\\Functional\\physical event logo.png");

                    }

                    count++;
                    eventCount++;

                }else {
                    events.remove(eventCount);
                }
                
            } else {

                //Setting Attributes
                putEventName(currentPane, events.get(eventCount).get("eventName"));
                putEventDetails(currentPane, events.get(eventCount).get("eventsDescription"));
                putClubName(currentPane, events.get(eventCount).get("clubName"));
                putEventDate(currentPane, events.get(eventCount).get("eventDate"));


                //Put Images
                if (Attendance.checkEventType(eventCount).equals("Online")){
                    putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\mainAttendance\\Icons\\Functional\\online logo.png");
                }else {
                    putClubImage(currentPane,"D:\\2nd Year\\Object Oriented Development\\CW\\CW Git\\School-Activity-Club-Management-System\\project-SACMS\\src\\main\\resources\\com\\main\\mainAttendance\\Icons\\Functional\\physical event logo.png");

                }

                count++;
                eventCount++;

            }
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

        // Generate and display the report
        List<Map<String,String>> allAttendanceData = AttendanceReportDatabase.getAttendanceData();
        AttendanceReport generatedReport = new AttendanceReport();
        generatedReport.generateAttendanceReport(allAttendanceData);
    }

    public void resetButtonInStudentsTableOnClick() throws SQLException{
        Attendance.clearAttendanceTable();
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
            Attendance attendance;
            try {

                studentIDMap = new HashMap<>();
                if (eventType.equals("Online")){

                     attendance = new OnlineAttendance(groupNumber, studentNumber, true, "23:10");
                     meetingTimeOrVenueLabel.setText("Meeting Time");

                }else {

                     attendance = new PhysicalEventsAttendance(groupNumber,studentNumber,"Maharagama",true);
                    meetingTimeOrVenueLabel.setText("Venue");
                    meetingTimeOrVenueLabel.setLayoutX(799);

                }

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

                choice.setValue(Objects.requireNonNullElse(attendanceStatus, "Not marked"));

                if (choice.getValue().equals("Not marked")){
                    choice.setLayoutX(680);

                }

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
            clearPanesStudents();
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
                clearPanesStudents();
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
            clearPanesStudents();
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
            clearPanesStudents();
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
            clearPanesStudents();
            displayStudents(4);
        }
    }

    // Process is to Check First the Event Type and choose the correct pane type. After that Display Student Details in that fill
    // Database Method to Retrieve data and Save in ArrayList.

    //Attendance Object to Access elements in Attendance Class

    public void eventOneOnClick() throws SQLException {
        eventsGroup.setVisible(false);

        currentEventID = Attendance.checkEventId(0);

        //Store data
        Attendance.setCurrentEventNumber(0); // this is the piece I need to change

        eventType = events.get(0).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventTwoOnClick() throws SQLException {

        currentEventID = Attendance.checkEventId(1);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(1);

        eventType = events.get(1).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventThreeOnClick() throws SQLException {
        eventsGroup.setVisible(false);

        currentEventID = Attendance.checkEventId(2);

        //Store data
        Attendance.setCurrentEventNumber(2);

        eventType = events.get(2).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventFourOnClick() throws SQLException {

        currentEventID = Attendance.checkEventId(3);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(3);

        eventType = events.get(3).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);

    }

    public void eventFiveOnClick() throws SQLException {

        currentEventID = Attendance.checkEventId(4);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(4);

        eventType = events.get(4).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);


    }

    public void eventSixOnClick() throws SQLException {

        currentEventID = Attendance.checkEventId(5);

        eventsGroup.setVisible(false);

        //Store data
        Attendance.setCurrentEventNumber(5);

        eventType = events.get(5).get("eventType");

        if (eventType.equals("Online")) {
            eventTypeLabel.setText("Online Event");
        }else {
            eventTypeLabel.setText("Physical Event");
        }
        studentsGroup.setVisible(true);
        displayStudents(0);



    }

    public void eventSevenOnClick() throws SQLException {

        currentEventID = Attendance.checkEventId(6);

    }

    public void eventEightOnClick() throws SQLException {
        currentEventID = Attendance.checkEventId(7);

    }

    public void eventNineOnClick() throws SQLException {
        currentEventID = Attendance.checkEventId(8);

    }

    public void eventTenOnClick() throws SQLException {
        currentEventID = Attendance.checkEventId(9);

    }

    public void eventElevenOnClick() throws SQLException {
        currentEventID = Attendance.checkEventId(10);

    }

    public void eventTwelveOnClick() throws SQLException {
        currentEventID = Attendance.checkEventId(11);

    }

    public void onBackButtonClick() throws IOException {
        SACMS sacms = new SACMS();
        Stage stage = new Stage();
        startPageController.backStatus = true;

        sacms.start(stage);



    }
}

