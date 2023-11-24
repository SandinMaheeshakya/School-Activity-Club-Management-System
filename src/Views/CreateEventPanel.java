package Views;

import javafx.collections.FXCollections;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Event;

import java.time.LocalDate;

public class CreateEventPanel {

    private VBox panel;
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
    private Button clearButton;

    public CreateEventPanel() {

        panel = new VBox(10);
        panel.setPadding(new Insets(20));

        Label eventNameLabel = new Label("Event Name:");
        Label eventDescriptionLabel = new Label("Event Description:");
        eventNameLabel.setStyle("-fx-text-fill: white;");
        eventDescriptionLabel.setStyle("-fx-text-fill: white;");

        Label modeLabel = new Label("Mode:");
        modeLabel.setStyle("-fx-text-fill: white;");
        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");

        Label dateLabel = new Label("Date:");
        dateLabel.setStyle("-fx-text-fill: white;");
        Label durationLabel = new Label("Duration:");
        durationLabel.setStyle("-fx-text-fill: white;");
        Label locationLabel = new Label("Location:");
        locationLabel.setStyle("-fx-text-fill: white;");

        Label meetingDetailsLabel = new Label("MEETING DETAILS");
        meetingDetailsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: true;");

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

        eventNameField = new TextField();
        eventNameField.setPromptText("Event Name");

        eventDescriptionArea = new TextArea();
        eventDescriptionArea.setPromptText("Event Description");
        eventDescriptionArea.setWrapText(true);

        eventModeCombo = new ComboBox<>();
        eventModeCombo.setItems(FXCollections.observableArrayList("Online", "Physical"));
        eventModeCombo.setValue("Physical");

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

        meetingLinkField = new TextField();
        meetingLinkField.setPromptText("Meeting Link");

        meetingIdField = new TextField();
        meetingIdField.setPromptText("Meeting ID");

        meetingPasswordField = new TextField();
        meetingPasswordField.setPromptText("Meeting Password");

        saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveEvent());

        clearButton = new Button("Clear");
        clearButton.setOnAction(event -> clearForm());

        String gradientStyle = "-fx-background-color: " +
                "linear-gradient(to bottom right, #0062DD, #FF0084, #000000);";
        saveButton.setStyle(gradientStyle + "-fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        clearButton.setStyle(gradientStyle + "-fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px;");
        saveButton.setPrefWidth(100);
        clearButton.setPrefWidth(100);
        saveButton.setMaxWidth(Double.MAX_VALUE);
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
        gridPane.add(eventNameField, 0, 1); // Column 0, Row 1
        gridPane.add(eventDescriptionLabel, 1, 0); // Column 1, Row 0
        gridPane.add(eventDescriptionArea, 1, 1); // Column 1, Row 1
        GridPane.setColumnSpan(eventDescriptionArea, 2); // Span 2 columns

        gridPane.add(modeLabel, 0, 3); // Column 0, Row 0
        gridPane.add(eventModeCombo, 0, 4); // Column 0, Row 1
        gridPane.add(typeLabel, 1, 3); // Column 1, Row 0
        gridPane.add(eventTypeCombo, 1, 4); // Column 1, Row 1

        gridPane.add(dateLabel, 0, 5); // Column 0, Row 0
        gridPane.add(eventDate, 0, 6);

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
        gridPane.add(clearButton, 20, 10);

        panel.getChildren().addAll(gridPane);
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
        String eventName = eventNameField.getText();
        String description = eventDescriptionArea.getText();
        String eventType = eventTypeCombo.getValue();
        String eventDate = "-";
        String startTime = startTimeHourCombo.getValue() + ":" + startTimeMinuteCombo.getValue() +" "+ startTimeAmPmCombo.getValue();
        String duration = durationField.getText();
        String endTime = endTimeHourCombo.getValue() + ":" + endTimeMinuteCombo.getValue() +" "+ endTimeAmPmCombo.getValue();
        String mode = eventModeCombo.getValue();
        String physicalLocation = locationField.getText();
        String onlineMeetingDetails = meetingLinkField.getText() + meetingIdField.getText() + meetingPasswordField.getText();

        Event event = new Event( eventName,  description,  eventType,  eventDate,  startTime,  duration,  endTime,   mode,  physicalLocation,  onlineMeetingDetails);

    }
    private void clearForm() {
    }
    public VBox getPanel() {
        return panel;
    }
}
