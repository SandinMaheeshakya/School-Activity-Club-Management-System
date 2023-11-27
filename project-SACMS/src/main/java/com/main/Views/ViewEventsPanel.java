package com.main.Views;

import com.main.Controllers.EventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.main.models.Event;

import java.util.List;

public class ViewEventsPanel {

    private VBox panel;
    private TableView<Event> eventTable;
    private TextField searchField;
    private Button searchButton;
    private Button attendButton;
    private Button deleteButton;
    private Button reloadButton;
    private TextField deleteEventIdField;
    private String event_id;

    public ViewEventsPanel() {
        panel = new VBox(10);
        panel.setPadding(new Insets(20));

        eventTable = new TableView<>();
        setupEventTable("0");

        searchField = new TextField();
        searchField.setPromptText("Search events");

        deleteEventIdField = new TextField();
        deleteEventIdField.setPromptText("Enter event ID");

        searchButton = new Button("Search Event by name");
        searchButton.setOnAction(event -> setupEventTable(searchField.getText()));

        attendButton = new Button("ATTEND");;

        deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> deleteEvent(deleteEventIdField.getText()));

        reloadButton = new Button("Load");
        reloadButton.setOnAction(event -> setupEventTable("0"));

        HBox searchBar = new HBox(10);
        searchBar.getChildren().addAll(searchField, searchButton);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(searchBar, 0, 1);
        gridPane.add(reloadButton, 1, 1);
        gridPane.add(attendButton, 2, 1);
        gridPane.add(deleteEventIdField, 3, 1);
        gridPane.add(deleteButton, 4, 1);
        gridPane.add(eventTable, 0, 2);

        panel.getChildren().addAll(gridPane);
    }

    private void setupEventTable(String searchText) {
        TableColumn<Event, String> EventID = new TableColumn<>("Event ID");
        EventID.setCellValueFactory(new PropertyValueFactory<>("EventID"));

        TableColumn<Event, String> EventName = new TableColumn<>("Event Name");
        EventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));

        TableColumn<Event, String> description = new TableColumn<>("Event Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Event, String> EventType = new TableColumn<>("Event Type");
        EventType.setCellValueFactory(new PropertyValueFactory<>("EventType"));

        TableColumn<Event, String> EventDate = new TableColumn<>("Event Date");
        EventDate.setCellValueFactory(new PropertyValueFactory<>("EventDate"));

        TableColumn<Event, String> StartTime = new TableColumn<>("Start");
        StartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));

        TableColumn<Event, String> EndTime = new TableColumn<>("End");
        EndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));

        TableColumn<Event, String> Duration = new TableColumn<>("Duration");
        Duration.setCellValueFactory(new PropertyValueFactory<>("Duration"));

        TableColumn<Event, String> Mode = new TableColumn<>("Mode");
        Mode.setCellValueFactory(new PropertyValueFactory<>("Mode"));

        TableColumn<Event, String> physicalLocation = new TableColumn<>("Venue");
        physicalLocation.setCellValueFactory(new PropertyValueFactory<>("physicalLocation"));

        TableColumn<Event, String> onlineMeetingLink = new TableColumn<>("Meeting Link");
        onlineMeetingLink.setCellValueFactory(new PropertyValueFactory<>("onlineMeetingLink"));

        TableColumn<Event, String> onlineMeetingID = new TableColumn<>("Meeting ID");
        onlineMeetingID.setCellValueFactory(new PropertyValueFactory<>("onlineMeetingID"));

        TableColumn<Event, String> onlineMeetingPassword = new TableColumn<>("Meeting Password");
        onlineMeetingPassword.setCellValueFactory(new PropertyValueFactory<>("onlineMeetingPassword"));

        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (TableColumn<Event, ?> column : eventTable.getColumns()) {
            column.setMaxWidth(1f * Integer.MAX_VALUE * 50); // 50% width for each column
        }

        eventTable.getColumns().clear();
        eventTable.getColumns().addAll(EventID, EventName,description,EventType, EventDate, StartTime,EndTime,Duration,Mode, physicalLocation,onlineMeetingLink,onlineMeetingID, onlineMeetingPassword );

        if (eventTable.getItems() != null) {
            eventTable.getItems().clear();
        } else {
            eventTable.setItems(FXCollections.observableArrayList()); // Initialize with an empty list if null
        }

        EventController eventController = new EventController();

        List<Event> events;
        if ("0".equals(searchText.trim())) {
            events = eventController. getAllEvents();
        } else {
            if(searchText.isEmpty()){
                events = eventController.getAllEvents();
            }else{
                events = eventController.searchEvents(searchText);
            }
        }

        ObservableList<Event> eventObservableList = FXCollections.observableArrayList(events);
        eventTable.setItems(eventObservableList);
    }

    private void deleteEvent(String id){
        EventController e = new EventController();
        boolean result = e.deleteEvent(id);

        if (result){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deletion Success");
            alert.setContentText("Event Deleted");
            alert.showAndWait();

            setupEventTable("0");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setContentText("Event does not exist!");
            alert.showAndWait();

            setupEventTable("0");
        }
    }
    public VBox getPanel() {
        return panel;
    }
}
