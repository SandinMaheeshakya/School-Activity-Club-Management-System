package com.main.projectsacms;

import com.main.projectsacms.Database.Attendance.RetrieveData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
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

public class AttendanceTrackingController {

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

    private final HashMap<String, Pane> PaneMap = new HashMap<>();
    private final HashMap<String, AnchorPane> Pages = new HashMap<>();

    private ArrayList<Map<String, String>> events = new ArrayList<>();
    // Getters and Setters to Pane Details

    public HashMap<String, Pane> getPaneMap() {
        return PaneMap;
    }

    public HashMap<String, AnchorPane> getPages() {
        return Pages;
    }

    public void initialize() throws SQLException {

        //Adding the page elements to a Hashmap
        HashMapPages();
        HashMapPane();

        events = RetrieveData.getEventsData();

        DisplayEvents();

    }

    public void HashMapPages() {
        Pages.put("EventsDisplayPageOne", EventsDisplayPageOne);
        Pages.put("EventsDisplayPageTwo", EventsDisplayPageTwo);
        Pages.put("EventsDisplayPageThree", EventsDisplayPageThree);
        Pages.put("EventsDisplayPageFour", EventsDisplayPageFour);

    }

    public void HashMapPane() {
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

    public ArrayList<String> getPanes() {
        String[] numbers = {"One", "Two", "Three", "Four"};
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


    public void DisplayEvents() {
        int count = 0;

        for (Map<String, String> event : events) {

            Pane currentPane = PaneMap.get(getPanes().get(count));

            //Setting Attributes
            putEventName(currentPane, event.get("eventName"));
            putEventDetails(currentPane,event.get("eventsDescription"));
            putClubName(currentPane,event.get("clubName"));
            putEventDate(currentPane,event.get("eventDate"));

            count++;
        }
    }

    public void switchToPageOne() {
        EventsDisplayPageOne.setVisible(true);
        EventsDisplayPageTwo.setVisible(false);
        EventsDisplayPageThree.setVisible(false);
        EventsDisplayPageFour.setVisible(false);
    }

    public void switchToPageTwo() {
        EventsDisplayPageOne.setVisible(false);
        EventsDisplayPageTwo.setVisible(true);
        EventsDisplayPageThree.setVisible(false);
        EventsDisplayPageFour.setVisible(false);
    }

    public void switchToPageThree() {
        EventsDisplayPageOne.setVisible(false);
        EventsDisplayPageTwo.setVisible(false);
        EventsDisplayPageThree.setVisible(true);
        EventsDisplayPageFour.setVisible(false);
    }

    public void switchToPageFour() {
        EventsDisplayPageOne.setVisible(false);
        EventsDisplayPageTwo.setVisible(false);
        EventsDisplayPageThree.setVisible(false);
        EventsDisplayPageFour.setVisible(true);
    }
}