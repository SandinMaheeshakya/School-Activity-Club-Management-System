package com.main.projectsacms.Database.Attendance;
import com.main.projectsacms.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RetrieveData extends Connection {


    // Retrieving Student Data
    public static ArrayList<Map<String, String>> getEventsData() throws SQLException {

        ArrayList<Map<String, String>> events = new ArrayList<>();

        try {
            //Establishing the connection
            establishConnection();

            // Creating a statement
            Statement statement = connection.createStatement();

            //SQL query
            String query = "SELECT Event_Name, Event_Description, Event_ID, Event_Date, Club_Name FROM events";

            //Results
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String eventName = resultSet.getString("Event_Name");
                String eventsDescription = resultSet.getString("Event_Description");
                String eventID = resultSet.getString("Event_ID");
                String eventDate = resultSet.getString("Event_Date");
                String clubName = resultSet.getString("Club_Name");

                Map<String, String> eventDetails = new HashMap<>();
                eventDetails.put("eventID", eventID);
                eventDetails.put("eventName", eventName);
                eventDetails.put("eventsDescription", eventsDescription);
                eventDetails.put("eventDate", eventDate);
                eventDetails.put("clubName", clubName);

                events.add(eventDetails);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Closing the connection
            if (connection != null) {
                closeConnection();
            }
        }

        return events;
    }
}



