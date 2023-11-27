package com.main;

import com.main.Views.CreateEventPanel;
import com.main.Views.ViewEventsPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Create New Event Tab
        Tab createNewEventTab = new Tab("Create New Event");
        CreateEventPanel createEventPanel = new CreateEventPanel();
        createNewEventTab.setContent(createEventPanel.getPanel());
        createNewEventTab.setClosable(false); // This makes the tab not closable

        // View Events Tab
        Tab viewEventsTab = new Tab("View Events");
        ViewEventsPanel viewEventPanel = new ViewEventsPanel();
        viewEventsTab.setContent(viewEventPanel.getPanel());
        viewEventsTab.setClosable(false); // This makes the tab not closable

        // Add tabs to the tab pane
        tabPane.getTabs().addAll(createNewEventTab, viewEventsTab); // Add tabs together

        // Set the gradient background
        String bgColour = "black";
        tabPane.setStyle("-fx-background-color: " + bgColour + ";");

        Scene scene = new Scene(tabPane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // This will make the window full screen
        primaryStage.setTitle("Event Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
