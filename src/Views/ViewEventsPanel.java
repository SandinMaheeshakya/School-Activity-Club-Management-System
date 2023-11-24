package Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.Event;

public class ViewEventsPanel {

    private VBox panel;
    private TableView<Event> eventTable;
    private TextField searchField;
    private Button searchButton;
    private Button attendButton;
    private Button deleteButton;

    public ViewEventsPanel() {
        panel = new VBox(10);
        panel.setPadding(new Insets(20));

        eventTable = new TableView<>();
        setupEventTable();


        searchField = new TextField();
        searchField.setPromptText("Search events");

        searchButton = new Button("Search");

        attendButton = new Button("ATTEND");

        deleteButton = new Button("Delete");

        HBox searchBar = new HBox(10);
        searchBar.getChildren().addAll(searchField, searchButton);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(searchBar, 0, 1);
        gridPane.add(attendButton, 5, 1);
        gridPane.add(deleteButton, 4, 1);
        gridPane.add(eventTable, 0, 2);

        panel.getChildren().addAll(gridPane);
    }

    private void setupEventTable() {
        TableColumn<Event, String> eventName = new TableColumn<>("Event Name");
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> eventType = new TableColumn<>("Event Type");
        eventType.setCellValueFactory(new PropertyValueFactory<>("eventType"));

        TableColumn<Event, String> eventDate = new TableColumn<>("Event Date");
        eventDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));

        TableColumn<Event, String> startTime = new TableColumn<>("Start");
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn<Event, String> endTime = new TableColumn<>("End");
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        TableColumn<Event, String> duration = new TableColumn<>("Duration");
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Event, String> mode = new TableColumn<>("Mode");
        mode.setCellValueFactory(new PropertyValueFactory<>("mode"));

        TableColumn<Event, String> physicalLocation = new TableColumn<>("Venue");
        physicalLocation.setCellValueFactory(new PropertyValueFactory<>("physicalLocation"));

        TableColumn<Event, String> onlineMeetingDetails = new TableColumn<>("Online Meeting Link");
        onlineMeetingDetails.setCellValueFactory(new PropertyValueFactory<>("onlineMeetingDetails"));

        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (TableColumn<Event, ?> column : eventTable.getColumns()) {
            column.setMaxWidth(1f * Integer.MAX_VALUE * 50); // 50% width for each column
        }

        eventTable.getColumns().addAll(eventName, eventType,eventDate,startTime,endTime,duration,mode,physicalLocation,onlineMeetingDetails);
    }

    public VBox getPanel() {
        return panel;
    }
}
