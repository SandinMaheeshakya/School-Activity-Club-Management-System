package com.main.Views;

import com.main.Controllers.EventController;
import com.mysql.cj.protocol.Message;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import com.main.models.Event;

import java.time.LocalDate;

public class CreateEventPanel {

    private VBox panel;

    private TextField eventIDField;
    private TextField eventNameField;
    private TextArea eventDescriptionArea;
    private ComboBox<String> eventModeCombo;
    private ComboBox<String> eventTypeCombo;
    private ComboBox<String> startTimeHourCombo;
    private ComboBox<String> startTimeMinuteCombo;
    private ComboBox<String> endTimeHourCombo;
    private ComboBox<String> endTimeMinuteCombo;
    private ComboBox<String> startTimeAmPmCombo;
    private ComboBox<String> endTimeAmPmCombo;
    private DatePicker eventDate;
    private TextField durationField;
    private TextField locationField;
    private TextField meetingLinkField;
    private TextField meetingIdField;
    private TextField meetingPasswordField;
    private Button saveButton;
    private Button updateButton;
    private Button clearButton;

    public CreateEventPanel() {

        panel = new VBox(10);
        panel.setPadding(new Insets(20));

        eventModeCombo = new ComboBox<>();
        eventModeCombo.getItems().addAll("Physical", "Online"); //Added newly

        meetingLinkField = new TextField();
        meetingIdField = new TextField();
        meetingPasswordField = new TextField();

        meetingLinkField.setPromptText("Meeting Link");
        meetingIdField.setPromptText("Meeting ID");
        meetingPasswordField.setPromptText("Meeting Password");

        Label locationLabel = new Label("Location:");
        locationLabel.setVisible(false);
//        locationField.setVisible(false);

        Label meetingDetailsLabel = new Label("MEETING DETAILS");
        meetingDetailsLabel.setVisible(false);

//        meetingLinkField.setVisible(false);
//        meetingIdField.setVisible(false);
//        meetingPasswordField.setVisible(false);




        // Set a listener on the mode combo box
        eventModeCombo.valueProperty().addListener((obs, oldMode, newMode) -> {


            if ("Online".equals(newMode)) {
                // Show online meeting details
                meetingDetailsLabel.setVisible(true);
                meetingLinkField.setVisible(true);
                meetingIdField.setVisible(true);
                meetingDetailsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: true;");
                meetingPasswordField.setVisible(true);
                locationLabel.setVisible(false);

                // Hide physical location
                locationField.setVisible(false);
            } else if ("Physical".equals(newMode)) {
                // Hide online meeting details
                meetingLinkField.setVisible(false);
                meetingIdField.setVisible(false);
                meetingPasswordField.setVisible(false);
                locationLabel.setStyle("-fx-text-fill: white;");
                locationLabel.setVisible(true);
                meetingDetailsLabel.setVisible(false);

                // Show physical location
                locationField.setVisible(true);
            }
        });



        Label eventNameLabel = new Label("Event Name:");
        Label eventDescriptionLabel = new Label("Event Description:");
        eventNameLabel.setStyle("-fx-text-fill: white;");
        eventDescriptionLabel.setStyle("-fx-text-fill: white;");

        Label eventIDLabel = new Label("Event ID:");
        eventIDLabel.setStyle("-fx-text-fill: white;");


        Label modeLabel = new Label("Mode:");
        modeLabel.setStyle("-fx-text-fill: white;");
        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");

        Label dateLabel = new Label("Date:");
        dateLabel.setStyle("-fx-text-fill: white;");
        Label durationLabel = new Label("Duration:");
        durationLabel.setStyle("-fx-text-fill: white;");
//        Label locationLabel = new Label("Location:");
//        locationLabel.setStyle("-fx-text-fill: white;");

//        Label meetingDetailsLabel = new Label("MEETING DETAILS");
//        meetingDetailsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: true;");

        Label timeDetailsLabel = new Label("TIME DETAILS");
        timeDetailsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: true;");

        Label startHourLabel = new Label("Start Time Hour:");
        startHourLabel.setStyle("-fx-text-fill: white;");

        Label startMinuteLabel = new Label("Start Time Minute:");
        startMinuteLabel.setStyle("-fx-text-fill: white;");

        Label startTimeAmPmLabel = new Label("Start Time AM/PM:");
        startTimeAmPmLabel.setStyle("-fx-text-fill: white;");

        Label endHourLabel = new Label("End Time Hour:");
        endHourLabel.setStyle("-fx-text-fill: white;");

        Label endMinuteLabel = new Label("End Time Minute:");
        endMinuteLabel.setStyle("-fx-text-fill: white;");

        Label endTimeAmPmLabel = new Label("End Time AM/PM:");
        endTimeAmPmLabel.setStyle("-fx-text-fill: white;");

        eventIDField = new TextField();
        eventIDField.setPromptText("Event ID");

        eventNameField = new TextField();
        eventNameField.setPromptText("Event Name");

        eventDescriptionArea = new TextArea();
        eventDescriptionArea.setPromptText("Event Description");
        eventDescriptionArea.setWrapText(true);

//        eventModeCombo = new ComboBox<>();
//        eventModeCombo.setItems(FXCollections.observableArrayList("Online", "Physical"));
//        eventModeCombo.setValue("Physical");

        eventTypeCombo = new ComboBox<>();
        eventTypeCombo.setItems(FXCollections.observableArrayList("Events", "Meetings", "Activities"));
        eventTypeCombo.setValue("Events");

        eventDate = new DatePicker();
        eventDate.setPromptText("Event Date");

        durationField = new TextField();
        durationField.setEditable(false);
        durationField.setPromptText("Duration (in minutes)");

        locationField = new TextField();
        locationField.setPromptText("Location");
//
//        meetingLinkField = new TextField();
//        meetingLinkField.setPromptText("Meeting Link");
//
//        meetingIdField = new TextField();
//        meetingIdField.setPromptText("Meeting ID");
//
//        meetingPasswordField = new TextField();
//        meetingPasswordField.setPromptText("Meeting Password");

        saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveEvent());

        updateButton = new Button("Update");
        updateButton.setOnAction(event -> updateEvent());

        clearButton = new Button("Clear");
        clearButton.setOnAction(event -> clearForm());

        String gradientStyle = "-fx-background-color: " +
                "linear-gradient(to bottom right, #0062DD, #FF0084, #000000);";
        saveButton.setStyle(gradientStyle + "-fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        updateButton.setStyle(gradientStyle + "-fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        clearButton.setStyle(gradientStyle + "-fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        saveButton.setPrefWidth(100);
        updateButton.setPrefWidth(100);
        clearButton.setPrefWidth(100);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxWidth(Double.MAX_VALUE);

        initializeTimeComboBoxes();
        setupDurationCalculation();
        initializeDatePicker();
        // GridPane for organized layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        GridPane.setValignment(eventNameField, VPos.TOP);
        gridPane.add(eventNameLabel, 0, 0); // Column 0, Row 0
        gridPane.add(eventNameField, 0, 1); // Column 0, Row 2
        GridPane.setValignment(eventIDField, VPos.TOP);
        gridPane.add(eventIDLabel, 0, 2); // Column 0, Row 0
        gridPane.add(eventIDField, 0, 3); // Column 0, Row 1
        gridPane.add(eventDescriptionLabel, 1, 0); // Column 1, Row 0
        gridPane.add(eventDescriptionArea, 1, 1); // Column 1, Row 1
        GridPane.setColumnSpan(eventDescriptionArea, 2); // Span 2 columns

        gridPane.add(modeLabel, 1, 2); // Column 0, Row 0
        gridPane.add(eventModeCombo, 1,3 ); // Column 0, Row 1
        gridPane.add(typeLabel, 2, 2); // Column 1, Row 0
        gridPane.add(eventTypeCombo, 2, 3); // Column 1, Row 1

        gridPane.add(dateLabel, 0, 5); // Column 0, Row 0
//        gridPane.add(eventDate, 0, 6);

        gridPane.add(locationLabel, 0, 8); // Column 0, Row 0
        gridPane.add(locationField, 0, 9);

        gridPane.add(meetingDetailsLabel, 0, 12); // Column 0, Row 0
        gridPane.add(meetingLinkField, 0, 13);
        gridPane.add(meetingIdField, 1, 13);
        gridPane.add(meetingPasswordField, 2, 13);

        gridPane.add(timeDetailsLabel, 0, 16); // Column 0, Row 0
        gridPane.add(startHourLabel, 0, 17);
        gridPane.add(startTimeHourCombo, 0, 18);
        gridPane.add(startMinuteLabel, 1, 17);
        gridPane.add(startTimeMinuteCombo, 1, 18);
        gridPane.add(startTimeAmPmLabel, 2, 17);
        gridPane.add(startTimeAmPmCombo, 2, 18);

        gridPane.add(endHourLabel, 0, 20);
        gridPane.add(endTimeHourCombo, 0, 21);
        gridPane.add(endMinuteLabel, 1, 20);
        gridPane.add(endTimeMinuteCombo, 1, 21);
        gridPane.add(endTimeAmPmLabel, 2, 20);
        gridPane.add(endTimeAmPmCombo, 2, 21);

        gridPane.add(durationLabel, 0, 23); // Column 0, Row 0
        gridPane.add(durationField, 0, 24);

        gridPane.add(saveButton, 20, 9);
        gridPane.add(updateButton, 20, 10);
        gridPane.add(clearButton, 20, 11);

        panel.getChildren().addAll(gridPane);
    }
    private void updateFieldVisibilityBasedOnMode(String mode) {
        if ("Online".equals(mode)) {
            meetingLinkField.setVisible(true);
            meetingIdField.setVisible(true);
            meetingPasswordField.setVisible(true);

            locationField.setVisible(false);
        } else if ("Physical".equals(mode)) {
            meetingLinkField.setVisible(false);
            meetingIdField.setVisible(false);
            meetingPasswordField.setVisible(false);

            locationField.setVisible(true);
        }
    }
    private void setupDurationCalculation() {
        // Add listeners to the start and end time ComboBoxes
        startTimeHourCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
        startTimeMinuteCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
        endTimeHourCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
        endTimeMinuteCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
        startTimeAmPmCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
        endTimeAmPmCombo.valueProperty().addListener((observable, oldValue, newValue) -> calculateDuration());
    }
    private void calculateDuration() {
        if (startTimeHourCombo.getValue() != null && startTimeMinuteCombo.getValue() != null &&
                endTimeHourCombo.getValue() != null && endTimeMinuteCombo.getValue() != null &&
                startTimeAmPmCombo.getValue() != null && endTimeAmPmCombo.getValue() != null) {

            // Parse the hour and minute values from the ComboBoxes
            int startHour = Integer.parseInt(startTimeHourCombo.getValue());
            int startMinute = Integer.parseInt(startTimeMinuteCombo.getValue());
            int endHour = Integer.parseInt(endTimeHourCombo.getValue());
            int endMinute = Integer.parseInt(endTimeMinuteCombo.getValue());

            // Convert to 24-hour format based on AM/PM selection
            if ("PM".equals(startTimeAmPmCombo.getValue()) && startHour != 12) {
                startHour += 12;
            } else if ("AM".equals(startTimeAmPmCombo.getValue()) && startHour == 12) {
                startHour = 0;
            }

            if ("PM".equals(endTimeAmPmCombo.getValue()) && endHour != 12) {
                endHour += 12;
            } else if ("AM".equals(endTimeAmPmCombo.getValue()) && endHour == 12) {
                endHour = 0;
            }

            // Calculate the start and end times in minutes since midnight
            int startTimeInMinutes = startHour * 60 + startMinute;
            int endTimeInMinutes = endHour * 60 + endMinute;

            // Calculate the duration in minutes
            int durationInMinutes = endTimeInMinutes - startTimeInMinutes;

            // If end time is earlier than start time, it implies the event spans past midnight
            if (durationInMinutes < 0) {
                durationInMinutes += 24 * 60; // Add 24 hours worth of minutes
            }

            int hours = durationInMinutes / 60;
            int minutes = durationInMinutes % 60;

            durationField.setText(String.format("%d hours %02d minutes", hours, minutes));
        }
    }
    private void initializeTimeComboBoxes() {
        startTimeHourCombo = new ComboBox<>();
        startTimeMinuteCombo = new ComboBox<>();
        startTimeAmPmCombo = new ComboBox<>(FXCollections.observableArrayList("AM", "PM"));
        endTimeHourCombo = new ComboBox<>();
        endTimeMinuteCombo = new ComboBox<>();
        endTimeAmPmCombo = new ComboBox<>(FXCollections.observableArrayList("AM", "PM"));

        // Populate hours (1-12 for AM/PM format)
        for (int i = 1; i <= 12; i++) {
            String hour = String.format("%02d", i); // Format to two digits
            startTimeHourCombo.getItems().add(hour);
            endTimeHourCombo.getItems().add(hour);
        }

        // Populate minutes (00, 15, 30, 45 for example)
        for (int i = 0; i < 60; i += 15) {
            String minute = String.format("%02d", i); // Format to two digits
            startTimeMinuteCombo.getItems().add(minute);
            endTimeMinuteCombo.getItems().add(minute);
        }

        // Set default values
        startTimeHourCombo.getSelectionModel().select("12");
        startTimeMinuteCombo.getSelectionModel().select("00");
        startTimeAmPmCombo.getSelectionModel().select("AM");
        endTimeHourCombo.getSelectionModel().select("12");
        endTimeMinuteCombo.getSelectionModel().select("00");
        endTimeAmPmCombo.getSelectionModel().select("AM");

        // Add validation listeners
        startTimeHourCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
        startTimeMinuteCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
        startTimeAmPmCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
        endTimeHourCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
        endTimeMinuteCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
        endTimeAmPmCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateTime());
    }
    private void initializeDatePicker() {
        eventDate = new DatePicker();
        eventDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0);
            }
        });

        // Optionally, set the current date as the default value
        eventDate.setValue(LocalDate.now());
    }
    private void validateTime() {
        int startHour = Integer.parseInt(startTimeHourCombo.getValue());
        int startMinute = Integer.parseInt(startTimeMinuteCombo.getValue());
        boolean startIsPm = startTimeAmPmCombo.getValue().equals("PM");

        int endHour = Integer.parseInt(endTimeHourCombo.getValue());
        int endMinute = Integer.parseInt(endTimeMinuteCombo.getValue());
        boolean endIsPm = endTimeAmPmCombo.getValue().equals("PM");

        // Convert 12-hour to 24-hour format
        startHour += (startIsPm && startHour != 12) ? 12 : 0;
        startHour -= (startHour == 12 && !startIsPm) ? 12 : 0;
        endHour += (endIsPm && endHour != 12) ? 12 : 0;
        endHour -= (endHour == 12 && !endIsPm) ? 12 : 0;

        // Calculate times in minutes
        int startTimeInMinutes = startHour * 60 + startMinute;
        int endTimeInMinutes = endHour * 60 + endMinute;

        // Check if the end time is before the start time
        if (endTimeInMinutes < startTimeInMinutes) {
            // Handle invalid end time (e.g., show an error, reset end time, etc.)
        }
    }

    private void saveEvent() {
        //Gather event data
        String eventID = eventIDField.getText();
        String eventName = eventNameField.getText();
        String description = eventDescriptionArea.getText();
        String eventType = eventTypeCombo.getValue();
        String eventDate = LocalDate.now().toString();
        String startTime = startTimeHourCombo.getValue() + ":" + startTimeMinuteCombo.getValue() +" "+ startTimeAmPmCombo.getValue();
        String endTime = endTimeHourCombo.getValue() + ":" + endTimeMinuteCombo.getValue() +" "+ endTimeAmPmCombo.getValue();
        String duration = durationField.getText();
        String mode = eventModeCombo.getValue();
        String physicalLocation = locationField.getText();
        String onlineMeetingLink = meetingLinkField.getText();
        String onlineMeetingID = meetingIdField.getText();
        String onlineMeetingPassword = meetingPasswordField.getText();

        Event event = new Event("0", eventName, description,  eventType,  eventDate,  startTime,  endTime, duration,  mode,  physicalLocation, onlineMeetingLink,onlineMeetingID,onlineMeetingPassword);

        EventController e = new EventController();
        boolean result = e.saveEvent(event);

        if(result == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Event Created");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setContentText("Unable to create event!");
            alert.showAndWait();
        }
        clearForm();
    }
    private void updateEvent() {
        //Gather event data
        String eventID = eventIDField.getText();
        String eventName = eventNameField.getText();
        String description = eventDescriptionArea.getText();
        String eventType = eventTypeCombo.getValue();
        String eventDate = LocalDate.now().toString();
        String startTime = startTimeHourCombo.getValue() + ":" + startTimeMinuteCombo.getValue() +" "+ startTimeAmPmCombo.getValue();
        String endTime = endTimeHourCombo.getValue() + ":" + endTimeMinuteCombo.getValue() +" "+ endTimeAmPmCombo.getValue();
        String duration = durationField.getText();
        String mode = eventModeCombo.getValue();
        String physicalLocation = locationField.getText();
        String onlineMeetingLink = meetingLinkField.getText();
        String onlineMeetingID = meetingIdField.getText();
        String onlineMeetingPassword = meetingPasswordField.getText();

        Event event = new Event(eventID, eventName, description,  eventType,  eventDate,  startTime,  endTime, duration,  mode,  physicalLocation, onlineMeetingLink,onlineMeetingID,onlineMeetingPassword);

        EventController e = new EventController();
        boolean result = e.updateEvent(event);

        if(result == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Success");
            alert.setContentText("Event Update");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setContentText("Unable to update event!");
            alert.showAndWait();
        }
        clearForm();
    }
    private void clearForm() {
        eventIDField.setText("");
        eventNameField.setText("");
        meetingPasswordField.setText("");
        meetingIdField.setText("");
        meetingLinkField.setText("");
        eventDescriptionArea.setText("");
    }
    public VBox getPanel() {
        return panel;
    }
}
